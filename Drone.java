import java.util.ArrayList;

/**
 * <h1>Drone</h1> Represents a Drone
 */

public class Drone extends Vehicle {

    final private double GAS_RATE = 1.33;
    /**
     * Default Contructor 
     */
    public Drone() {
        super();
    }

    /**
     * Constructor
     *
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight that the vehicle can hold
     */
    public Drone(String licensePlate, double maxWeight) {
        super(licensePlate, maxWeight);
    }

    /*
     * =============================================================================
     * | Methods from Profitable Interface
     * =============================================================================
     */
    /**
     * Returns the profits generated by the packages currently in the Truck.
     * <p>
     * &sum;p<sub>price</sub> - (range<sub>max</sub> &times; 1.33)
     * </p>
     */
    @Override
    public double getProfit() {
        //TODO

    }

    /**
     * Generates a String of the Drone report. Drone report includes:
     * <ul>
     * <li>License Plate No.</li>
     * <li>Destination</li>
     * <li>Current Weight/Maximum Weight</li>
     * <li>Net Profit</li>
     * <li>Shipping labels of all packages in truck</li>
     * </ul>
     *
     * @return Truck Report
     */
    @Override
    public String report() {
        //TODO
    }



}
