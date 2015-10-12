package test.backend.www.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Distance implements Comparable<Distance>
{
  private final int kilometers;
  private final int meters;

  public Distance(double meters)
  {
    kilometers = (int) (meters / 1000);
    this.meters = (int) (meters % 1000);
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    if (kilometers > 0)
    {
      sb.append(kilometers);
      sb.append("km");
      if (meters > 0)
      {
        sb.append(" ");
      }
    }
    if (meters > 0)
    {
      sb.append(meters);
      sb.append("m");
    }
    return sb.toString();
  }

  @Override
  public int compareTo(Distance o)
  {
    return (o.kilometers * 1000 + o.meters) - (kilometers * 1000 + meters);
  }
}
