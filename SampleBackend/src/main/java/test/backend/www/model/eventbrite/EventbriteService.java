package test.backend.www.model.eventbrite;

import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.eventbrite.domain.EventBean;
import test.backend.www.model.eventbrite.domain.VenueBean;

@Data
@Slf4j
public class EventbriteService {
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_INSTANT;

	@Autowired
	private RestTemplate restTemplate;

	private String baseUrl;
	private String token;

	public EventbriteService(String baseUrl, String token) {
		this.baseUrl = baseUrl;
		this.token = token;
	}

	public EventBean getEventById(String id) throws Exception {

		String url = baseUrl + "/events/" + id + "/?token=" + token;
		log.debug("Calling URL: {}", url);

		String eventResponse = null;
		JsonParser parser = new JsonParser();
		JsonObject jsonObjectResponse = null;
		try {
			eventResponse = restTemplate.getForObject(url, String.class);
			jsonObjectResponse = (JsonObject) parser.parse(eventResponse);
		} catch (Exception e) {
			eventResponse = IOUtils
					.toString(new InputStreamReader(getClass().getResourceAsStream("/events/dummy-events.json")));
			jsonObjectResponse = (JsonObject) parser.parse(eventResponse);
			jsonObjectResponse = (JsonObject) jsonObjectResponse.getAsJsonArray().get(0);
		}
		return constructEventBean(jsonObjectResponse);
	}

	public VenueBean getVenueById(String id) throws Exception {

		String url = baseUrl + "/venues/" + id + "/?token=" + token;
		log.debug("Calling URL: {}", url);

		String eventResponse = null;
		JsonParser parser = new JsonParser();
		JsonObject jsonObjectResponse = null;
		eventResponse = restTemplate.getForObject(url, String.class);
		jsonObjectResponse = (JsonObject) parser.parse(eventResponse);
		return constructVenueBean(jsonObjectResponse);
	}

	private EventBean constructEventBean(JsonObject jsonObjectEvent) throws Exception {
		EventBean eventBean = new EventBean();
		eventBean.setTitle(getJsonValue(jsonObjectEvent, "name", "text"));
		eventBean.setDescription(getJsonValue(jsonObjectEvent, "description", "text"));
		eventBean.setId(getJsonValue(jsonObjectEvent, "id", null));
		eventBean.setStartDate(getJsonValue(jsonObjectEvent, "start", "utc"));
		eventBean.setEndDate(getJsonValue(jsonObjectEvent, "end", "utc"));
		eventBean.setCapacity(getJsonValue(jsonObjectEvent, "capacity", null));
		eventBean.setCurrency(getJsonValue(jsonObjectEvent, "currency", null));
		eventBean.setImage(getJsonValue(jsonObjectEvent, "logo", "url"));
		String venueId = getJsonValue(jsonObjectEvent, "venue_id", null);
		if (StringUtils.isNotBlank(venueId)) {
			eventBean.setVenue(getVenueById(getJsonValue(jsonObjectEvent, "venue_id", null)));
		}
		return eventBean;
	}

	private VenueBean constructVenueBean(JsonObject jsonObjectEvent) {
		VenueBean eventBean = new VenueBean();
		eventBean.setName(getJsonValue(jsonObjectEvent, "name", null));
		eventBean.setLatitude(getJsonValue(jsonObjectEvent, "latitude", null));
		eventBean.setLongitude(getJsonValue(jsonObjectEvent, "longitude", null));
		return eventBean;
	}

	private String getJsonValue(JsonObject jsonObject, String elementName, String elementName2) {
		try {
			if (jsonObject != null) {
				if (jsonObject.get(elementName) != null) {
					if (elementName2 != null) {
						if (jsonObject.get(elementName).getAsJsonObject() != null
								&& jsonObject.get(elementName).getAsJsonObject().get(elementName2) != null) {
							return jsonObject.get(elementName).getAsJsonObject().get(elementName2).getAsString();
						}
					} else {
						return jsonObject.get(elementName) != null ? jsonObject.get(elementName).getAsString() : null;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return null;
	}
}
