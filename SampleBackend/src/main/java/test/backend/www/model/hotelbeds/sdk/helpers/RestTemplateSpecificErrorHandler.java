package test.backend.www.model.hotelbeds.sdk.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateSpecificErrorHandler implements ResponseErrorHandler {

    @Override
    public void handleError(final ClientHttpResponse response) throws IOException {
        final StringWriter writer = new StringWriter();
        try (InputStream theIS = response.getBody();
            InputStreamReader theISR = new InputStreamReader(theIS, StandardCharsets.UTF_8);
            BufferedReader theBR = new BufferedReader(theISR)) {
            char[] buffer = new char[4096];
            int count = theBR.read(buffer);
            while (count >= 0) {
                writer.write(buffer, 0, count);
                count = theBR.read(buffer);
            }
        }
        log.error("Response error: {} {}", response.getStatusText(), writer.toString());
    }

    @Override
    public boolean hasError(final ClientHttpResponse response) throws IOException {
        return isError(response.getStatusCode());
    }

    private boolean isError(final HttpStatus status) {
        final HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
