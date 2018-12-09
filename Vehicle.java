import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * <h1>Vehicle</h1> Represents a vehicle
 * <p>
 * Creates initializes variables for all vehicles
 *
 * @author Sam Vonderahe, Aniruddha Dahad
 * @version 12/9/2018
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

    //============================================================================
    public Vehicle() {
        licensePlate = "";
        maxWeight = 0;
        currentWeight = 0;
        zipDest = 0;
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
        return currentWeight == maxWeight;
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
        int maxRange = 0;

        for (Package p : warehousePackages) {
            if (getRange(p) > maxRange) {
                maxRange = getRange(p);
            }
        }

        while (true) {
            for (int i = 0; i < warehousePackages.size(); i++) {
                if (getRange(warehousePackages.get(i)) <= range) { // changed from <=
                    if (addPackage(warehousePackages.get(i))) {
                        warehousePackages.remove(i);
                        i--;
                    }
                }

                if (range == maxRange && (warehousePackages.size() == 0 || i == warehousePackages.size() - 1)) {
                    return;
                }
            }
            range += increment;
        }
    }

    public int getRange(Package p) {
        return Math.abs(p.getDestination().getZipCode() - this.getZipDest());
    }


    public double getProfit() {
        return 0;
    }

    public String report() {
        return "";
    }

    public static void main(String[] args) {
//        ArrayList<Package> packages = DatabaseManager.loadPackages
// (new File("C:\\Users\\Aniruddha\\Documents\\Project5-e4b585158fb745
// f1a58d218a7be677b35e275bfa\\Project5 - Final\\src\\files\\PackageList.csv"));
//        Vehicle v = new Vehicle();
//        v.setZipDest(40200);
//        v.setMaxWeight(20);
//        v.fill(packages);
    }
}

