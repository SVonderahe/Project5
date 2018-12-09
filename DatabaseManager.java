import java.io.*;
import java.util.ArrayList;


/**
 * <h1>Database Manager</h1>
 * <p>
 * Used to locally save and retrieve data.
 *
 * Loads and saves warehouse information to \files
 *
 * @author Sam Vonderahe, Aniruddha Dahad
 *
 * @version 12/9/2018
 *
 */
public class DatabaseManager {

    /**
     * Creates an ArrayList of Vehicles from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of vehicles
     */
    public static ArrayList<Vehicle> loadVehicles(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                String[] args = line.split(",");
                String type = args[0];
                String licensePlate = args[1];
                double maxWeight = Double.parseDouble(args[2]);

                if (args[0].equalsIgnoreCase("Truck")) {
                    Truck truck = new Truck(licensePlate, maxWeight);
                    vehicles.add(truck);
                } else if (args[0].equalsIgnoreCase("Cargo Plane")) {
                    CargoPlane plane = new CargoPlane(licensePlate, maxWeight);
                    vehicles.add(plane);
                } else if (args[0].equalsIgnoreCase("Drone")) {
                    Drone drone = new Drone(licensePlate, maxWeight);
                    vehicles.add(drone);
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("File path incorrect.");
        }

        return vehicles;
    }


    /**
     * Creates an ArrayList of Packages from the passed CSV file. The values are in
     * the CSV file as followed:
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     * <p>
     * If filePath does not exist, a blank ArrayList will be returned.
     *
     * @param file CSV File
     * @return ArrayList of packages
     */

    public static ArrayList<Package> loadPackages(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ArrayList<Package> packages = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                String[] args = line.split(",");
                String id = args[0];
                String product = args[1];
                double weight = Double.parseDouble(args[2]);
                double price = Double.parseDouble(args[3]);
                String name = args[4];
                String address = args[5];
                String city = args[6];
                String state = args[7];
                int zip = Integer.parseInt(args[8]);

                ShippingAddress destination = new ShippingAddress(name, address, city, state, zip);
                Package p = new Package(id, product, weight, price, destination);
                packages.add(p);

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("File path incorrect.");
        }

        return packages;
    }


    /**
     * Returns the total Profits from passed text file. If the file does not exist 0
     * will be returned.
     *
     * @param file file where profits are stored
     * @return profits from file
     */
    public static double loadProfit(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        double profit = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null) {
                line = "0.0";
            }
            profit = Double.parseDouble(line);

        } catch (IOException e) {
            System.out.println("File path incorrect.");
        } catch (NumberFormatException n) {
            System.out.println("Incorrect format in file");
        }
        return profit;
    }


    /**
     * Returns the total number of packages shipped stored in the text file. If the
     * file does not exist 0 will be returned.
     *
     * @param file file where number of packages shipped are stored
     * @return number of packages shipped from file
     */
    public static int loadPackagesShipped(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int numPackages = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null) {
                line = "0";
            }
            numPackages = Integer.parseInt(line);

        } catch (IOException e) {
            System.out.println("File path incorrect.");
        } catch (NumberFormatException n) {
            System.out.println("Incorrect format in file");
        }

        return numPackages;
    }


    /**
     * Returns whether or not it was Prime Day in the previous session. If file does
     * not exist, returns false.
     *
     * @param file file where prime day is stored
     * @return whether or not it is prime day
     */
    public static boolean loadPrimeDay(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null) {
                line = "0";
            }
            int primeDay = Integer.parseInt(line);

            if (primeDay == 1) {
                return true;
            } else if (primeDay == 0) {
                return false;
            }

        } catch (IOException e) {
            System.out.println("File path incorrect.");
            e.printStackTrace();
        } catch (NumberFormatException n) {
            System.out.println("Incorrect format in file");
        }

        return false;
    }


    /**
     * Saves (writes) vehicles from ArrayList of vehicles to file in CSV format one vehicle per line.
     * Each line (vehicle) has following fields separated by comma in the same order.
     * <ol>
     * <li>Vehicle Type (Truck/Drone/Cargo Plane)</li>
     * <li>Vehicle License Plate</li>
     * <li>Maximum Carry Weight</li>
     * </ol>
     *
     * @param file     File to write vehicles to
     * @param vehicles ArrayList of vehicles to save to file
     */
    public static void saveVehicles(File file, ArrayList<Vehicle> vehicles) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();

            for (Vehicle v : vehicles) {
                if (v instanceof Truck) {
                    Truck truck = (Truck) v;
                    bw.write("Truck," + truck.getLicensePlate() + "," + truck.getMaxWeight());
                } else if (v instanceof Drone) {
                    Drone drone = (Drone) v;
                    bw.write("Drone," + drone.getLicensePlate() + "," + drone.getMaxWeight());
                } else if (v instanceof CargoPlane) {
                    CargoPlane plane = (CargoPlane) v;
                    bw.write("Cargo Plane," + plane.getLicensePlate() + "," + plane.getMaxWeight());
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("File IO Exception");
        }
    }


    /**
     * Saves (writes) packages from ArrayList of package to file in CSV format one package per line.
     * Each line (package) has following fields separated by comma in the same order.
     * <ol>
     * <li>ID</li>
     * <li>Product Name</li>
     * <li>Weight</li>
     * <li>Price</li>
     * <li>Address Name</li>
     * <li>Address</li>
     * <li>City</li>
     * <li>State</li>
     * <li>ZIP Code</li>
     * </ol>
     *
     * @param file     File to write packages to
     * @param packages ArrayList of packages to save to file
     */
    public static void savePackages(File file, ArrayList<Package> packages) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();

            for (Package p : packages) {
                bw.write(p.getID() + "," +
                        p.getProduct() + "," +
                        p.getWeight() + "," +
                        p.getPrice() + "," +
                        p.getDestination().getName() + "," +
                        p.getDestination().getAddress() + "," +
                        p.getDestination().getCity() + "," +
                        p.getDestination().getState() + "," +
                        p.getDestination().getZipCode());

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("File IO Exception");
        }
    }


    /**
     * Saves profit to text file.
     *
     * @param file   File to write profits to
     * @param profit Total profits
     */

    public static void saveProfit(File file, double profit) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();
            bw.write(profit + "");

        } catch (IOException e) {
            System.out.println("File IO Exception");
        }
    }


    /**
     * Saves number of packages shipped to text file.
     *
     * @param file      File to write profits to
     * @param nPackages Number of packages shipped
     */

    public static void savePackagesShipped(File file, int nPackages) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();
            bw.write(nPackages + "");

        } catch (IOException e) {
            System.out.println("File IO Exception");
        }
    }


    /**
     * Saves status of prime day to text file. If it is primeDay "1" will be
     * writtern, otherwise "0" will be written.
     *
     * @param file     File to write profits to
     * @param primeDay Whether or not it is Prime Day
     */

    public static void savePrimeDay(File file, boolean primeDay) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();

            if (primeDay) {
                bw.write("1");
            } else {
                bw.write("0");
            }

        } catch (IOException e) {
            System.out.println("File IO Exception");
        }
    }
}