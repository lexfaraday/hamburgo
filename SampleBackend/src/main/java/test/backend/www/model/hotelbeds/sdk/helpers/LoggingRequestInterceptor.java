package test.backend.www.model.hotelbeds.sdk.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    public static final String TEST_ERROR_FLAG = "TEST_ERROR_FLAG";

    private static final List<LogRequestType> JSON_PRINT_LIST = Arrays.asList(LogRequestType.BOTH, LogRequestType.JSON);
    private static final List<LogRequestType> XML_PRINT_LIST = Arrays.asList(LogRequestType.BOTH, LogRequestType.XML);

    private final boolean showMethod = true;
    private final boolean showRequest = true;
    private final boolean showResponse = true;
    private final boolean showRequestHeaders = true;
    private final boolean showResponseHeaders = true;
    private final boolean showResponseStatusCode = true;
    private final boolean showUrl = true;
    private final boolean beautify = true;
    private final LogRequestType logRequestType = LogRequestType.BOTH;

    private enum LogRequestType {
        XML,
        JSON,
        BOTH;
    };


    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {

        // BEGIN Code to avoid IOException on 400 response
        final ClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        HttpRequest interceptedRequest = request;
        boolean skipPrintResponseBecauseOfBug = false;
        final String query = request.getURI().getQuery();
        if (StringUtils.isNotBlank(query) && query.indexOf(TEST_ERROR_FLAG) != -1) {
            final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(request.getURI());
            // This is because the request its intercepted twice. I don't know why...
            if (StringUtils.countMatches(query, TEST_ERROR_FLAG) == 3) {
                interceptedRequest =
                    requestFactory.createRequest(uriBuilder.replaceQueryParam(TEST_ERROR_FLAG, TEST_ERROR_FLAG).build().toUri(), request.getMethod());
            } else {
                interceptedRequest = requestFactory.createRequest(uriBuilder.replaceQueryParam(TEST_ERROR_FLAG).build().toUri(), request.getMethod());
            }
            //set the headers lost when create new request
            interceptedRequest.getHeaders().putAll(request.getHeaders());
            skipPrintResponseBecauseOfBug = true;
        }
        // END Code to avoid IOException on 400 response


        final ClientHttpResponse response = execution.execute(interceptedRequest, body);
        final Boolean isJson = isJsonRequest(new String(body, StandardCharsets.UTF_8), request);


        if (log.isDebugEnabled()) {
            logRequest(interceptedRequest, body, response, isJson, skipPrintResponseBecauseOfBug);
        }

        return response;
    }

    private Boolean isJsonRequest(final String string, final HttpRequest request) {
        Boolean result = null;
        if (StringUtils.isBlank(string) && Arrays.asList(HttpMethod.GET, HttpMethod.DELETE).contains(request.getMethod())) {
            if (request.getHeaders().getAccept().contains(MediaType.APPLICATION_JSON)) {
                result = true;
            } else {
                result = false;
            }

        } else {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
            try {
                mapper.readTree(string);
                result = true;
            } catch (final IOException e) {
                result = false;
            }
        }
        return result;
    }

    private void logRequest(final HttpRequest request, final byte[] body, final ClientHttpResponse response, final boolean isJson,
        final boolean skipLogResponseBecauseOfBug) throws IOException {
        final boolean doPrintJson = JSON_PRINT_LIST.contains(logRequestType);
        final boolean doPrintXml = XML_PRINT_LIST.contains(logRequestType);

        if (isJson && doPrintJson || !isJson && doPrintXml) {
            log.debug("-----------------------------------------------------------------------------");
            //do logging
            if (showMethod) {
                log.debug("request.getMethod(): {}", request.getMethod());
            }
            if (showUrl) {
                log.debug("url: {}", request.getURI().toString());
            }
            if (showRequestHeaders) {
                log.debug("request.getHeaders(): {}", request.getHeaders());
            }
            if (showRequest && Arrays.asList(HttpMethod.POST, HttpMethod.PUT).contains(request.getMethod())) {
                doLogging("request body:  ", new String(body, StandardCharsets.UTF_8), isJson);
            }
            if (showResponseStatusCode) {
                log.debug("response.getRawStatusCode(): {}", response.getRawStatusCode());
            }
            if (showResponseHeaders) {
                log.debug("Response headers: ");
                for (final Entry<String, List<String>> responseHeader : response.getHeaders().entrySet()) {
                    log.debug("{}", responseHeader);
                }
                log.debug("End Response headers:");
            }

            if (!skipLogResponseBecauseOfBug) {
                if (response != null && response.getBody() != null && showResponse) {

                    final StringWriter writer = new StringWriter();
                    try (InputStream theIS = response.getBody();
                        InputStreamReader theISR = new InputStreamReader(theIS, StandardCharsets.UTF_8);
                        BufferedReader theBR = new BufferedReader(theISR)) {
                        char[] buffer = new char[4096];
                        int count = -1;
                        while ((count = theBR.read(buffer)) >= 0) {
                            writer.write(buffer, 0, count);

                        }
                    }
                    doLogging("response.getBody(): ", writer.toString(), isJson);
                } else if (response == null || response.getBody() == null) {
                    log.debug("Empty body!!! ");
                }
            }
            log.debug("-----------------------------------------------------------------------------");
        }

    }

    private void doLogging(final String message, final String body, final boolean isJson) throws JsonParseException, JsonMappingException,
        IOException, UnsupportedEncodingException {
        if (log.isDebugEnabled()) {
            if (isJson) {
                log.debug(message + writeJSON(body, beautify));
            } else {
                try {
                    log.debug(message + writeXML(body, beautify));
                } catch (final TransformerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /***********************************************************************
     * * * * * * * * * * * * * * * WRITE * * * * * * * * * * * * * * * *
     ***********************************************************************/

    public static String writeXML(final Object request, final boolean pretty) {
        String result = null;
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(request.getClass());
            final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, pretty);
            final StringWriter writer = new StringWriter();
            jaxbMarshaller.marshal(request, writer);
            result = writer.toString();
        } catch (final JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String writeXML(final String request, final boolean pretty) throws TransformerException {
        final Transformer transformer = TransformerFactory.newInstance().newTransformer();
        if (pretty) {
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        } else {
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
        }
        final StreamResult resultStream = new StreamResult(new StringWriter());
        transformer.transform(new StreamSource(new StringReader(request)), resultStream);
        return resultStream.getWriter().toString();
    }

    public static String writeJSON(final Object object, final boolean pretty) {
        ObjectMapper mapper = null;
        String result = null;
        mapper = new ObjectMapper();
        try {
            if (pretty) {
                result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } else {
                result = mapper.writeValueAsString(object);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String writeJSON(final String jsonString, final boolean pretty) throws JsonParseException, JsonMappingException, IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final Object object = mapper.readValue(jsonString, Object.class);
        return writeJSON(object, pretty);
    }
}
