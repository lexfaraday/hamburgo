package test.backend.www.controllers;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import test.backend.www.model.sabre.SabrePricedItineraries;
import test.backend.www.model.sabre.SabreService;

@Data
@EqualsAndHashCode(callSuper = false)
@Controller
// Ej: http://localhost:8080/sabre/flights/PMI/BCN/2015-10-22/2015-10-25/
public class SabreController
{
  @Autowired
  SabreService sabreService;

  @ResponseBody
  @RequestMapping(value = "/sabre/flights/{origin}/{destination}/{from}/{to}/", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<SabrePricedItineraries> flights(@PathVariable(value = "origin") String origin,
      @PathVariable(value = "destination") String destination,
      @PathVariable(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
      @PathVariable(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to)
  {
    SabrePricedItineraries response = sabreService.getFlights(origin, destination, from, to);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
