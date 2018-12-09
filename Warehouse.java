import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Warehouse</h1>
 *
 * Creates Object Warehouse that will be used for
 * maintaning vehicles and packages
 *
 * @author Sam Vonderahe, Aniruddha Dahad
 *
 * @version 12/9/2018
 *
 */

public class Warehouse {
    static Scanner in = new Scanner(System.in);
    final static String FOLDER_PATH = "files/";
    final static File VEHICLE_FILE = new File(FOLDER_PATH + "VehicleList.csv");
    final static File PACKAGE_FILE = new File(FOLDER_PATH + "PackageList.csv");
    final static File PROFIT_FILE = new File(FOLDER_PATH + "Profit.txt");
    final static File N_PACKAGES_FILE = new File(FOLDER_PATH + "NumberOfPackages.txt");
    final static File PRIME_DAY_FILE = new File(FOLDER_PATH + "PrimeDay.txt");
    final static double PRIME_DAY_DISCOUNT = .15;
    final static String INVALID_OPTION_TEXT = "Error: Option not available.";

    static ArrayList<Package> packages = new ArrayList<Package>();
    static int packagesShipped = 0;
    static boolean primeDay = false;
    static double profit = 0;
    static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    /**
     * Main Method
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {
        boolean go = true;

        //1) load data (vehicle, packages, profits, packages shipped and primeday) from files using DatabaseManager
        while (go) {
            packages = DatabaseManager.loadPackages(PACKAGE_FILE);
            packagesShipped = DatabaseManager.loadPackagesShipped(N_PACKAGES_FILE);
            primeDay = DatabaseManager.loadPrimeDay(PRIME_DAY_FILE);
            profit = DatabaseManager.loadProfit(PROFIT_FILE);
            vehicles = DatabaseManager.loadVehicles(VEHICLE_FILE);
            //2) Show menu and handle user inputs

            String menuOption3;
            if (primeDay) {
                menuOption3 = "3) Deactivate Prime Day\n";
            } else {
                menuOption3 = "3) Activate Prime Day\n";
            }
            int option = 0;
            while (true) {
                System.out.println(
                        "==========Options==========\n" +
                                "1) Add Package\n" +
                                "2) Add Vehicle\n" +
                                menuOption3 +
                                "4) Send Vehicle\n" +
                                "5) Print Statistics\n" +
                                "6) Exit\n" +
                                "===========================");
                // String optionError = "Error: Option not available.";


                try {
                    option = Integer.parseInt(in.nextLine());
                    if (option < 1 || option > 6) {
                        System.out.println(INVALID_OPTION_TEXT);
                    } else {
                        System.out.print("\n");
                        break;
                    }
                } catch (NumberFormatException n) {
                    System.out.println(INVALID_OPTION_TEXT);
                }
            }
            switch (option) {
                case 1:
                    addPackageMenu();
                    break;
                case 2:
                    addVehicleMenu();
                    break;
                case 3:
                    if (!primeDay) {
                        primeDay = true;
                        for (Package p : packages) {
                            p.setPrice(p.getPrice() * 0.85);
                        }
                    } else {
                        primeDay = false;
                        for (Package p : packages) {
                            p.setPrice(p.getPrice() / 0.85);
                        }
                    }
                    break;
                case 4:
                    sendVehicleMenu();
                    break;
                case 5:
                    stats();
                    break;
                case 6:
                    go = false;

            }


            //3) save data (vehicle, packages, profits, packages shipped and primeday)
            // to files (overwriting them) using DatabaseManager
            DatabaseManager.savePackages(PACKAGE_FILE, packages);
            DatabaseManager.savePackagesShipped(N_PACKAGES_FILE, packagesShipped);
            DatabaseManager.savePrimeDay(PRIME_DAY_FILE, primeDay);
            DatabaseManager.saveProfit(PROFIT_FILE, profit);
            DatabaseManager.saveVehicles(VEHICLE_FILE, vehicles);
        }

    }

    public static void addPackageMenu() {

        System.out.println("Enter Package ID:");
        String id = in.nextLine();
        System.out.println("Enter Product Name:");
        String name = in.nextLine();
        double weight = 0;
        while (true) {
            System.out.println("Enter Weight:");
            try {
                weight = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number");
            }
        }
        double price = 0;
        while (true) {
            System.out.println("Enter Price:");

            try {
                price = Double.parseDouble(in.nextLine());
                if (primeDay) {
                    price = price * .85;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number");
            }
        }
        System.out.println("Enter Buyer Name:");
        String buyerName = in.nextLine();
        System.out.println("Enter Address:");
        String address = in.nextLine();
        System.out.println("Enter City:");
        String city = in.nextLine();
        System.out.println("Enter State:");
        String state = in.nextLine();
        int zipCode = 0;
        while (true) {
            System.out.println("Enter ZIP Code:");
            try {
                zipCode = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number");
            }
        }
        ShippingAddress sa = new ShippingAddress(buyerName, address, city, state, zipCode);
        Package p = new Package(id, name, weight, price, sa);
        packages.add(p);
        System.out.println(p.shippingLabel());

    }

    public static void addVehicleMenu() {
        int option = 0;
        while (true) {
            System.out.println(
                    "Vehicle Options:\n" +
                            "1) Truck\n" +
                            "2) Drone\n" +
                            "3) Cargo Plane");

            try {
                option = Integer.parseInt(in.nextLine());
                if (option < 1 || option > 3) {
                    System.out.println(INVALID_OPTION_TEXT);
                } else {
                    break;
                }
            } catch (NumberFormatException n) {
                System.out.println(INVALID_OPTION_TEXT);
            }
        }
        System.out.println("Enter License Plate No.:");
        String license = in.nextLine();
        double weight = 0;
        while (true) {
            System.out.println("Enter Maximum Carry Weight:");
            try {
                weight = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Number");
            }
        }
        Vehicle v = new Vehicle();
        switch (option) {
            case 1:
                v = new Truck(license, weight);
                break;
            case 2:
                v = new Drone(license, weight);
                break;
            case 3:
                v = new CargoPlane(license, weight);
        }
        vehicles.add(v);
    }

    public static void sendVehicleMenu() {
        if (vehicles.size() == 0) {
            System.out.println("Error: No vehicles available.");
            return;
        }
        if (packages.size() == 0) {
            System.out.println("Error: No packages available.");
            return;
        }
        int choice = 0;
        while (true) {
            System.out.println("Options:\n" +
                    "1) Send Truck\n" +
                    "2) Send Drone\n" +
                    "3) Send Cargo Plane\n" +
                    "4) Send First Available");
            try {
                choice = Integer.parseInt(in.nextLine());
                if (choice > 4 || choice < 1) {
                    System.out.println("Invalid number");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
        Vehicle sending = new Vehicle();
        boolean available = false;
        switch (choice) {
            case 1:
                for (Vehicle v : vehicles) {
                    if (v instanceof Truck) {
                        available = true;
                        sending = v;
                        break;
                    }
                }
                if (!available) {
                    System.out.println("Error: No vehicles of selected type are available.");
                } else {
                    send(sending);
                }
                break;
            case 2:
                for (Vehicle v : vehicles) {
                    if (v instanceof Drone) {
                        available = true;
                        sending = v;
                        break;
                    }
                }
                if (!available) {
                    System.out.println("Error: No vehicles of selected type are available.");
                } else {
                    send(sending);
                }
                break;
            case 3:
                for (Vehicle v : vehicles) {
                    if (v instanceof CargoPlane) {
                        available = true;
                        sending = v;
                        break;
                    }
                }
                if (!available) {
                    System.out.println("Error: No vehicles of selected type are available.");
                } else {
                    send(sending);
                }
                break;
            case 4:
                sending = vehicles.get(0);
                send(sending);
        }
    }

    public static void send(Vehicle v) {
        int choice = 0;
        while (true) {
            System.out.println("ZIP Code Options:\n" +
                    "1) Send to first ZIP Code\n" +
                    "2) Send to mode of ZIP Codes");
            try {
                choice = Integer.parseInt(in.nextLine());
                if (choice < 1 || choice > 2) {
                    System.out.println("Invalid Number");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
        int zip = 0;
        switch (choice) {
            case 1:
                zip = packages.get(0).getDestination().getZipCode();
                v.setZipDest(zip);
                break;
            case 2:
                int occurances = 0;
                int mostOccurances = 1;
                for (int i = 0; i < packages.size(); i++) {
                    for (int k = 0; k < packages.size(); k++) {
                        if (packages.get(i).getDestination().getZipCode() ==
                                packages.get(k).getDestination().getZipCode()) {
                            occurances++;
                        }
                    }
                    if (occurances > mostOccurances) {
                        mostOccurances = occurances;
                        zip = packages.get(i).getDestination().getZipCode();
                    }
                }
                if (mostOccurances == 1) {
                    zip = packages.get(0).getDestination().getZipCode();
                }
                v.setZipDest(zip);
                break;

        }
        v.fill(packages);
        System.out.println(v.report());
        profit += v.getProfit();
        v.empty();

    }

    public static void stats() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.printf("==========Statistics==========\n" +
                "Profits:                 %s\n" +
                "Packages Shipped:                %d\n" +
                "Packages in Warehouse:           %d\n" +
                "==============================\n", nf.format(profit), packagesShipped, packages.size());
    }

    public static void printStatisticsReport(double profits, int pShipped, int numberOfPackages) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String money = nf.format(profits);


        System.out.println("==========Statistics==========\n" +
                "Profits:                 $" + money + "\n" +
                "Packages Shipped:                " + pShipped + "\n" +
                "Packages in Warehouse:           " + numberOfPackages + "\n" +
                "==============================");
    }
}