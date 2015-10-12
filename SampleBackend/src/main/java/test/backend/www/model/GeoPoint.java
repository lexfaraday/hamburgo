package test.backend.www.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeoPoint
{
  private final Coordinate latitude;
  private final Coordinate longitude;

  public GeoPoint(double latitude, double longitude)
  {
    this(Coordinate.latitudeFromDecimalDegrees(latitude), Coordinate.longitudeFromDecimalDegrees(longitude));
  }

  public Distance distanceTo(GeoPoint anotherPosition)
  {
    // haversine formula (https://en.wikipedia.org/wiki/Haversine_formula)
    int R = 6371000; // metres
    double φ1 = latitude.getDecimalRadians();
    double φ2 = anotherPosition.getLatitude().getDecimalRadians();
    double Δφ = Math.toRadians((anotherPosition.getLatitude().getDecimalDegrees() - getLatitude().getDecimalDegrees()));
    double Δλ = Math.toRadians((anotherPosition.getLongitude().getDecimalDegrees() - getLongitude().getDecimalDegrees()));

    double a = Math.sin(Δφ / 2) * Math.sin(Δφ / 2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return new Distance(R * c);
  }
}
