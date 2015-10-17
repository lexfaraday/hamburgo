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

	public List<EventBean> searchEvents() throws Exception {
		List<EventBean> events = new ArrayList<EventBean>();

		String url = appProfile.getEventUrl() + appProfile.getEventToken();
		System.out.println(url);

		String eventResponse = null;
		JsonParser parser = new JsonParser();
		JsonObject jsonObjectResponse = null;
		try {
			eventResponse = restTemplate.getForObject(url, String.class);
			jsonObjectResponse = (JsonObject) parser.parse(eventResponse);
			throw new Exception();
		} catch (Exception e) {
			eventResponse = IOUtils.toString(new InputStreamReader(getClass().getResourceAsStream("/events/dummy-events.json"))); 
			jsonObjectResponse = (JsonObject) parser.parse(eventResponse);
		}

		if (jsonObjectResponse != null) {

			JsonArray jsonArrayEvents = jsonObjectResponse.getAsJsonArray("events");

			for (JsonElement e : jsonArrayEvents) {
				JsonObject jsonObjectEvent = (JsonObject) e;
				EventBean eventBean = new EventBean();
				eventBean.setTitle(jsonObjectEvent.get("name").getAsJsonObject().get("text").getAsString());
				eventBean
						.setDescription(jsonObjectEvent.get("description").getAsJsonObject().get("text").getAsString());
				eventBean.setId(jsonObjectEvent.get("id").getAsString());
				eventBean.setStartDate(jsonObjectEvent.get("start").getAsJsonObject().get("utc").getAsString());
				eventBean.setEndDate(jsonObjectEvent.get("end").getAsJsonObject().get("utc").getAsString());
				eventBean.setCapacity(jsonObjectEvent.get("capacity").getAsString());
				eventBean.setCurrency(jsonObjectEvent.get("currency").getAsString());
				eventBean.setImage(jsonObjectEvent.get("logo").getAsJsonObject().get("url").getAsString());
				events.add(eventBean);
			}
		}
		return events;
	}
}
