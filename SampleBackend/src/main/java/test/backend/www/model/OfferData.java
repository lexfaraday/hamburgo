package test.backend.www.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import test.backend.www.model.eventbrite.domain.EventBean;

@Data
public class OfferData {

	List<FlightJourneyData> flights = new ArrayList<>();

	List<HotelData> hotelDatas = new ArrayList<>();

	EventBean event;

}
