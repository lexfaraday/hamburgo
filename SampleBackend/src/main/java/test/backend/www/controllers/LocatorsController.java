package test.backend.www.controllers;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import test.backend.www.model.Airport;
import test.backend.www.model.AirportLocator;
import test.backend.www.model.GeoPoint;
import test.backend.www.model.RelativeDistance;

@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
@Controller
// Ej: http://localhost:8080/locate/39.577251/2.633764/
public class LocatorsController
{

  @Autowired
  AirportLocator airportLocator;

  @ResponseBody
  @RequestMapping(value = "/locate/{latitude}/{longitude}/", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<RelativeDistance>> locate(@PathVariable(value = "latitude") double latitude,
      @PathVariable(value = "longitude") double longitude, @RequestParam(value = "limitKm", required = false, defaultValue = "300") long limitKm,
      @RequestParam(value = "max", required = false, defaultValue = "5") int max,
      @RequestParam(value = "withIataCode", required = false, defaultValue = "true") boolean withIataCode)
  {
    log.info("Locating airports...");
    return new ResponseEntity<>(airportLocator.getClosestAirports(new GeoPoint(latitude, longitude), max, limitKm, withIataCode), HttpStatus.OK);
  }

  @ResponseBody
  @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Airport>> list(@RequestParam(value = "from", required = false, defaultValue = "0") int from,
      @RequestParam(value = "to", required = false, defaultValue = "25") int to)
  {
    log.info("Listing airports...");
    int realFrom = Math.max(0, from);
    int limit = Math.min(Math.abs(to) - realFrom, 25);
    List<Airport> result = airportLocator.getAirports().values().stream().sorted().skip(realFrom).limit(limit).collect(Collectors.toList());
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
