import java.util.ArrayList;

/**
 * <h1>Vehicle</h1> Represents a vehicle
 */

public class Vehicle implements Profitable {
    private String licensePlate;
    private double maxWeight;
    private double currentWeight;
    private int zipDest;
    private ArrayList<Package> packages;


    /**
     * Default Constructor
     */

    // TODO Errors might be caused by double,int assignment. Test
    //============================================================================
    public Vehicle() {
        licensePlate = "";
        maxWeight = currentWeight = zipDest = 0;
        packages = new ArrayList<>();
    }
    
    //============================================================================


    /**
     * Constructor
     * 
     * @param licensePlate license plate of vehicle
     * @param maxWeight    maximum weight of vehicle
     */
    //============================================================================
    public Vehicle(String licensePlate, double maxWeight) {
        this();
        this.licensePlate = licensePlate;
        this.maxWeight = maxWeight;
    }
    
    //============================================================================

    
    /**
     * Returns the license plate of this vehicle
     * 
     * @return license plate of this vehicle
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    
    
    
    
    /**
     * Updates the license plate of vehicle
     * 
     * @param licensePlate license plate to be updated to
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    
    
    
    
    

    /**
     * Returns the maximum weight this vehicle can carry
     * 
     * @return the maximum weight that this vehicle can carry
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    
    
    
    
    /**
     * Updates the maximum weight of this vehicle
     * 
     * @param maxWeight max weight to be updated to
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    
    
    
    
    
    /**
     * Returns the current weight of all packages inside vehicle
     * 
     * @return current weight of all packages inside vehicle
     */
    public double getCurrentWeight() {
        return currentWeight;
    }
    
    
    
    
    

    /**
     * Returns the current ZIP code destination of the vehicle
     * 
     * @return current ZIP code destination of vehicle
     */
    public int getZipDest() {
        return zipDest;
    }
    
    
    
    
    

    /**
     * Updates the ZIP code destination of vehicle
     * 
     * @param zipDest ZIP code destination to be updated to
     */
    public void setZipDest(int zipDest) {
        this.zipDest = zipDest;
    }

    
    
    
    
    
    /**
     * Returns ArrayList of packages currently in Vehicle
     * 
     * @return ArrayList of packages in vehicle
     */
    public ArrayList<Package> getPackages() {
        return packages;
    }

    
    
    
    
    
    /**
     * Adds Package to the vehicle only if has room to carry it (adding it would not
     * cause it to go over its maximum carry weight).
     * 
     * @param pkg Package to add
     * @return whether or not it was successful in adding the package
     */
    public boolean addPackage(Package pkg) {
        if (currentWeight + pkg.getWeight() <= maxWeight) {
            packages.add(pkg);
            currentWeight += pkg.getWeight();
            return true;
        } else {
            return false;
        }
    }

    
    
    
    
    
    /**
     * Clears vehicle of packages and resets its weight to zero
     */
    public void empty() {
        packages = new ArrayList<>();
        currentWeight = 0;
    }
    
    
    
    
    

    /**
     * Returns true if the Vehicle has reached its maximum weight load, false
     * otherwise.
     * 
     * @return whether or not Vehicle is full
     */
    public boolean isFull() {
        if (currentWeight == maxWeight) {
            return true;
        } else {
            return false;
        }
    }

    
    
    
    
    
    /**
     * Fills vehicle with packages with preference of date added and range of its
     * destination zip code. It will iterate over the packages intially at a range
     * of zero and fill it with as many as it can within its range without going
     * over its maximum weight. The amount the range increases is dependent on the
     * vehicle that is using this. This range it increases by after each iteration
     * is by default one.
     * 
     * @param warehousePackages List of packages to add from
     */
    public void fill(ArrayList<Package> warehousePackages) {
        int increment = 1;
        int range = 0;

        // TODO Personal check on labeled loops

        outer:
        while(true) {
            for (Package p : warehousePackages) {
                if (getRange(p) <= range) {
                    if (!addPackage(p)) {
                        break outer;
                    }
                }
            }
            range += increment;
        }
    }

    public int getRange(Package p) {
        return Math.abs(p.getDestination().getZipCode() - zipDest);
    }

    // TODO if something goes wrong, change these from being abstract.

    public double getProfit() {
        int maxRange = getRange(packages.get(0));
        int priceSum = 0;
        double gasPrice = 1.66;

        for (Package p : packages) {
            priceSum += p.getPrice();

            if (maxRange < getRange(p)) {
                maxRange = getRange(p);
            }
        }

        double totalGasCost = gasPrice * maxRange;
        return priceSum - totalGasCost;
    }

    public String report() {
        String report = "";
        report +=
                "==========Truck Report==========\n" +
                        "License Plate No.: " + getLicensePlate() + "\n" +
                        "Destination: " + getZipDest() + "\n" +
                        "Weight Load: " + getCurrentWeight() + "/" + getMaxWeight() + "\n" +
                        "Net Profit: $" + getProfit() + "\n" +
                        "=====Shipping Labels=====";

        for (Package p : packages) {
            report += p.shippingLabel() + "\n";
        }

        report += "==============================";
        return report;
    }
}

