package com.hotelbeds.travel.api.service.events;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hotelbeds.travel.api.profiles.ApplicationProfile;
import com.hotelbeds.travel.api.service.events.domain.EventBean;

@Service
public class EventBriteProvider {

	@Autowired
	private ApplicationProfile appProfile;

	@Autowired
	private RestTemplate restTemplate;

	public EventBean getEventById(String id) throws Exception {

		String endpoint = "/" + id + "/?token=";
		String url = appProfile.getEventUrl() + endpoint + appProfile.getEventToken();
		System.out.println(url);

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
			jsonObjectResponse = (JsonObject)jsonObjectResponse.getAsJsonArray().get(0);
		}
		return constructEventBean(jsonObjectResponse);
	}

	public List<EventBean> searchEvents() throws Exception {
		List<EventBean> events = new ArrayList<EventBean>();
		String endpoint = "/search/?token=";
		String url = appProfile.getEventUrl() + endpoint + appProfile.getEventToken();
		System.out.println(url);

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
		}

		if (jsonObjectResponse != null) {

			JsonArray jsonArrayEvents = jsonObjectResponse.getAsJsonArray("events");

			for (JsonElement e : jsonArrayEvents) {
				JsonObject jsonObjectEvent = (JsonObject) e;
				EventBean eventBean = constructEventBean(jsonObjectEvent);
				events.add(eventBean);
			}
		}
		return events;
	}

	private EventBean constructEventBean(JsonObject jsonObjectEvent) {
		EventBean eventBean = new EventBean();
		eventBean.setTitle(getJsonValue(jsonObjectEvent, "name", "text"));
		eventBean.setDescription(getJsonValue(jsonObjectEvent, "description", "text"));
		eventBean.setId(getJsonValue(jsonObjectEvent, "id", null));
		eventBean.setStartDate(getJsonValue(jsonObjectEvent, "start", "utc"));
		eventBean.setEndDate(getJsonValue(jsonObjectEvent, "end", "utc"));
		eventBean.setCapacity(getJsonValue(jsonObjectEvent, "capacity", null));
		eventBean.setCurrency(getJsonValue(jsonObjectEvent, "currency", null));
		eventBean.setImage(getJsonValue(jsonObjectEvent, "logo", "url"));
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
