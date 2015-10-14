package test.backend.www.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import test.backend.www.model.hotelbeds.HotelbedsService;

@Data
@EqualsAndHashCode(callSuper = false)
@Controller
// Ej: http://localhost:8080/hotelbeds/availability/53.562139/9.956317/2015-10-22/2015-10-25/
public class HotelbedsController
{
  @Autowired
  HotelbedsService hotelbedsService;

  @ResponseBody
  @RequestMapping(value = "/hotelbeds/availability/{latitude}/{longitude}/{from}/{to}/", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Object> flights(@PathVariable(value = "latitude") String latitude, @PathVariable(value = "longitude") String longitude,
      @PathVariable(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
      @PathVariable(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
      @RequestParam(value = "limitKm", required = false, defaultValue = "20") long limitKm)
  {
    return new ResponseEntity<>(hotelbedsService.getAvailability(latitude, longitude, from, to, limitKm), HttpStatus.OK);
  }

}
