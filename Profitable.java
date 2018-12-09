
 /**
 * <h1>Profitable</h1>
 * <p>
 * This interface represents something that can be used to make a profit. Along
 * with returning total profits it must also be able to provide a report.
 *
 * Creates methods that are used to calculate profit for vehicles
 *
 * @author Sam Vonderahe, Aniruddha Dahad
 *
 * @version 12/9/2018
 *
 */
public interface Profitable {
    double getProfit();

    String report();
}