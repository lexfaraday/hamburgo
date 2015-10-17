package test.backend.www.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import test.backend.www.model.eventbrite.domain.EventBean;

@Data
public class OfferData {

	List<FlightOfferData> flights = new ArrayList<>();

	HotelData hoteldata;

	EventBean event;

}
