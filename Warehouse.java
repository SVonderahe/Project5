import java.io.File;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 */

public class Warehouse {
	final static String folderPath = "files/";
    final static File VEHICLE_FILE = new File(folderPath + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(folderPath + "PackageList.csv");
    final static File PROFIT_FILE = new File(folderPath + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(folderPath + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(folderPath + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;
    final static String invalidOptionText = "Error: Option not available.";

    /**
     * Main Method
     * 
     * @param args list of command line arguements
     */
    public static void main(String[] args) {
    	//TODO
    	
    	//1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
    	
    	
    	
    	//2) Show menu and handle user inputs
    	Scanner in = new Scanner(System.in);

        System.out.println(
                "==========Options==========\n" +
                "1) Add Package\n" +
                "2) Add Vehicle\n" +
                "3) Activate Prime Day\n" +
                "4) Send Vehicle\n" +
                "5) Print Statistics\n" +
                "6) Exit\n" +
                "===========================);");

        // TODO Implement error handling for non integer inputs
        int option;

        // String optionError = "Error: Option not available.";

        try {
            option = Integer.parseInt(in.nextLine());
            if (option < 1 || option > 6) {
                System.out.println(invalidOptionText);
            }
        } catch (NumberFormatException n) {
            System.out.println(invalidOptionText);
        }

    	
    	
    	//3) save data (vehicle, packages, profits, packages shipped and primeday) to files (overwriting them) using DatabaseManager
    	
    
    }

    public void addPackage() {

    }

    // TODO unify scanners
    public void addVehicle() {
        Scanner in = new Scanner(System.in);
        System.out.println(
                "Vehicle Options:\n" +
                "1) Truck\n" +
                "2) Drone\n" +
                "3) Cargo Plane");

        int option;

        try {
            option = Integer.parseInt(in.nextLine());
            if (option < 1 || option > 3) {
                System.out.println(invalidOptionText);
            }
        } catch (NumberFormatException n) {
            System.out.println(invalidOptionText);
        }
    }
}