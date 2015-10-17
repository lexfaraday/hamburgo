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

  public GeoPoint(String latitude, String longitude)
  {
    this(Double.parseDouble(latitude), Double.parseDouble(longitude));
  }

  public Distance distanceTo(GeoPoint anotherPosition)
  {
    // haversine formula (https://en.wikipedia.org/wiki/Haversine_formula)
    int R = 6371000; // metres
    double phi1 = latitude.getDecimalRadians();
    double phi2 = anotherPosition.getLatitude().getDecimalRadians();
    double deltaPhi = Math.toRadians((anotherPosition.getLatitude().getDecimalDegrees() - getLatitude().getDecimalDegrees()));
    double deltaLambda = Math.toRadians((anotherPosition.getLongitude().getDecimalDegrees() - getLongitude().getDecimalDegrees()));

    double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2)
        + Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return new Distance(R * c);
  }
}
