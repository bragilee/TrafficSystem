import java.util.Random;

public class Util
{
  private static Random rand = new Random();


  /**
   * Returns a random double in the range [minimum, maximum).
   */
  public static double nextDouble(double minimum, double maximum)
  {
    double result = minimum + (rand.nextDouble() * (maximum - minimum));
    return result;
  }

  /**
   * Used to simulate failure. For a percentage failure, d, calculate
   * whether this should rail randomly.
   */
  public static boolean fail(double d)
  {
    return rand.nextDouble() < d;
  }

  //convert to a percentage and round a double to a specified number
  //of decimal places
  public static double round(double d, int places)
  {
    double r = Math.pow(10D, (double) (places));
    return Math.round((100 * d) * r) / r;
  }

}
