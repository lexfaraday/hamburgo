package test.backend.www.model;

import lombok.Data;

@Data
public class RelativeDistance implements Comparable<RelativeDistance>
{
  private final GeoPoint base;
  private final Distance distance;
  private final Airport airport;

  public RelativeDistance(final GeoPoint base, final Airport airport)
  {
    this.base = base;
    this.airport = airport;
    this.distance = base.distanceTo(airport.getPosition());
  }

  @Override
  public int compareTo(RelativeDistance other)
  {
    int partial = other.getDistance().compareTo(getDistance());
    if (partial != 0)
    {
      return partial;
    }
    else
    {
      return other.getAirport().getId().compareTo(getAirport().getId());
    }
  }

}
