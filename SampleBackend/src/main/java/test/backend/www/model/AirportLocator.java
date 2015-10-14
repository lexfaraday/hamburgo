package test.backend.www.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class AirportLocator
{
  private final Map<Integer, Airport> airports;

  public List<RelativeDistance> getClosestAirports(GeoPoint position, int maxAirports, long limitKm, boolean withIataCode)
  {
    List<RelativeDistance> result = new ArrayList<>();

    // Find the relative distances from the given position to the airports in the system, ordered
    Set<RelativeDistance> sortedDistances = new TreeSet<>();
    for (Entry<Integer, Airport> airportEntry : airports.entrySet())
    {
      if (!withIataCode || StringUtils.isNotBlank(airportEntry.getValue().getIataFaaCode()))
      {
        sortedDistances.add(new RelativeDistance(position, airportEntry.getValue()));
      }
    }
    // @formatter:off
    result = 
      sortedDistances
        .stream()
        .limit(maxAirports)
        .filter(sortedDistance -> sortedDistance.getDistance().getKilometers() < limitKm)
        .collect(Collectors.toList())
        ;
    // @formatter:on
    return result;
  }
}
