package test.backend.www.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Airport implements Comparable<Airport>
{

  private final Integer id;
  private final String name;
  private final String city;
  private final String country;
  private final String iataFaaCode;
  private final String icaoCode;
  private final GeoPoint position;

  public Airport(String line)
  {
    String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    // Field 0 - Airport ID Unique OpenFlights identifier for this airport.
    // Field 1 - Name Name of airport. May or may not contain the City name.
    // Field 2 - City Main city served by airport. May be spelled differently from Name.
    // Field 3 - Country Country or territory where airport is located.
    // Field 4 - IATA/FAA
    // 3-letter FAA code, for airports located in Country "United States of America".
    // 3-letter IATA code, for all other airports. Blank if not assigned.
    // Field 5 - ICAO 4-letter ICAO code. Blank if not assigned.
    // Field 6 - Latitude Decimal degrees, usually to six significant digits. Negative is South, positive is North.
    // Field 7 - Longitude Decimal degrees, usually to six significant digits. Negative is West, positive is East.
    // Field 8 - Altitude In feet.
    // Field 9 - Timezone Hours offset from UTC. Fractional hours are expressed as decimals, eg. India is 5.5.
    // Field 10 - DST Daylight savings time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand), N (None) or U
    // (Unknown).
    // Field 11 - See also: Help: Time
    // Field 12 - Tz database time zone Timezone in "tz" (Olson) format, eg. "America/Los_Angeles".
    id = Integer.valueOf(fields[0]);
    name = fields[1].replaceAll("\"", "");
    city = fields[2].replaceAll("\"", "");
    country = fields[3].replaceAll("\"", "");
    iataFaaCode = fields[4].replaceAll("\"", "");
    icaoCode = fields[5].replaceAll("\"", "");
    Coordinate latitude = Coordinate.latitudeFromDecimalDegrees(Double.parseDouble(fields[6]));
    Coordinate longitude = Coordinate.longitudeFromDecimalDegrees(Double.parseDouble(fields[7]));
    position = new GeoPoint(latitude, longitude);
  }

  @Override
  public int compareTo(Airport o)
  {
    return id.compareTo(o.getId());
  }

}
