package test.backend.www.model;

import lombok.Data;

@Data
public class Coordinate
{
  public enum Direction
  {
    N, S(-1), E, W(-1);
    private final int modifier;

    private Direction()
    {
      modifier = 1;
    }

    private Direction(int modifier)
    {
      this.modifier = modifier;
    }
  }

  private final int degrees;
  private final int minutes;
  private final int seconds;
  private final Direction direction;
  private final double decimalDegrees;
  private final double decimalRadians;

  public Coordinate(int degrees, int minutes, int seconds, Direction direction)
  {
    this.degrees = degrees;
    this.minutes = minutes;
    this.seconds = seconds;
    this.direction = direction;
    //
    this.decimalDegrees = (degrees + ((double) (minutes * 60) + seconds) / 3600) * direction.modifier;
    this.decimalRadians = Math.toRadians(this.decimalDegrees);
  }

  public Coordinate(String degrees, String minutes, String seconds, String direction)
  {
    this(Integer.parseInt(degrees), Integer.parseInt(minutes), Integer.parseInt(seconds), Direction.valueOf(direction));
  }

  public static Coordinate latitudeFromDecimalRadians(final double decimalRadians)
  {
    final double decimalDegrees = Math.toDegrees(decimalRadians);
    final Direction direction = decimalDegrees > 0 ? Direction.N : Direction.S;
    return fromDecimalDegrees(Math.abs(decimalDegrees), direction);
  }

  public static Coordinate longitudeFromDecimalRadians(final double decimalRadians)
  {
    final double decimalDegrees = Math.toDegrees(decimalRadians);
    final Direction direction = decimalDegrees > 0 ? Direction.E : Direction.W;
    return fromDecimalDegrees(Math.abs(decimalDegrees), direction);
  }

  public static Coordinate latitudeFromDecimalDegrees(final double decimalDegrees)
  {
    final Direction direction = decimalDegrees > 0 ? Direction.N : Direction.S;
    return fromDecimalDegrees(Math.abs(decimalDegrees), direction);
  }

  public static Coordinate longitudeFromDecimalDegrees(final double decimalDegrees)
  {
    final Direction direction = decimalDegrees > 0 ? Direction.E : Direction.W;
    return fromDecimalDegrees(Math.abs(decimalDegrees), direction);
  }

  private static Coordinate fromDecimalDegrees(final double decimalDegrees, final Direction direction)
  {
    final int degrees = (int) (decimalDegrees);
    final int minutes = (int) ((decimalDegrees - degrees) * 60);
    final int seconds = (int) Math.round((decimalDegrees - degrees) * 3600 - (minutes * 60));
    return new Coordinate(degrees, minutes, seconds, direction);
  }
}
