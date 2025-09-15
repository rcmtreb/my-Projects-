import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String plateNumber;
    private String model;
    private boolean isRented;

    public Car(String plateNumber, String model) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.isRented = false;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public boolean isRented() {
        return isRented;
    }

    public void rentCar() {
        isRented = true;
    }

    public void returnCar() {
        isRented = false;
    }

    @Override
    public String toString() {
        return "Car{" +
                "plateNumber='" + plateNumber + '\'' +
                ", model='" + model + '\'' +
                ", isRented=" + isRented +
                '}';
    }
}

class Customer {
    private String name;
    private String customerId;

    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }
}

class RentalRecord {
    private Customer customer;
    private Car car;
    private boolean isReturned;

    public RentalRecord(Customer customer, Car car) {
        this.customer = customer;
        this.car = car;
        this.isReturned = false;
    }

    public String getCustomerId() {
        return customer.getCustomerId();
    }

    public void markReturned() {
        this.isReturned = true;
    }

    @Override
    public String toString() {
        return "RentalRecord{" +
                "Customer='" + customer.getName() + '\'' +
                ", Car='" + car.getModel() + " (" + car.getPlateNumber() + ")" + '\'' +
                ", Status='" + (isReturned ? "Returned" : "Rented") + '\'' +
                '}';
    }
}

class RentalService {
    private List<Car> carInventory = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<RentalRecord> rentalRecords = new ArrayList<>();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    public void addCar(Car car) {
        carInventory.add(car);
        System.out.println("Car added: " + car);
    }

    public void removeCar(String plateNumber) {
        carInventory.removeIf(car -> car.getPlateNumber().equalsIgnoreCase(plateNumber));
        System.out.println("Car removed: " + plateNumber);
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer registered: " + customer);
    }

    public Car findAvailableCar(String model) {
        for (Car car : carInventory) {
            if (car.getModel().equalsIgnoreCase(model) && !car.isRented()) {
                return car;
            }
        }
        return null;
    }

    public Customer findCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public void rentCar(String customerId, String model) {
        Customer customer = findCustomer(customerId);
        Car car = findAvailableCar(model);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        if (car == null) {
            System.out.println("Car model not available.");
            return;
        }
        car.rentCar();
        RentalRecord record = new RentalRecord(customer, car);
        rentalRecords.add(record);
        System.out.println("Car rented: " + car + " to customer " + customer.getName());
    }

    public void returnCar(String plateNumber) {
        for (Car car : carInventory) {
            if (car.getPlateNumber().equalsIgnoreCase(plateNumber) && car.isRented()) {
                car.returnCar();
                for (RentalRecord record : rentalRecords) {
                    if (record.toString().contains(plateNumber) && !record.toString().contains("Returned")) {
                        record.markReturned();
                        break;
                    }
                }
                System.out.println("Car returned: " + car);
                return;
            }
        }
        System.out.println("Car not found or already returned.");
    }

    public boolean adminLogin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }

    public void viewAvailableCars() {
        boolean hasAvailableCars = false;
        for (Car car : carInventory) {
            if (!car.isRented()) {
                System.out.println("Model: " + car.getModel() + ", Plate Number: " + car.getPlateNumber());
                hasAvailableCars = true;
            }
        }
        if (!hasAvailableCars) {
            System.out.println("No cars available.");
        }
    }

    public void viewAllRentalRecords() {
        if (rentalRecords.isEmpty()) {
            System.out.println("No rental records available.");
        } else {
            for (RentalRecord record : rentalRecords) {
                System.out.println(record);
            }
        }
    }

    public void viewUserRentalRecords(String customerId) {
        boolean hasRecords = false;
        for (RentalRecord record : rentalRecords) {
            if (record.getCustomerId().equals(customerId)) {
                System.out.println(record);
                hasRecords = true;
            }
        }
        if (!hasRecords) {
            System.out.println("No rental records for this user.");
        }
    }

    public void viewCarStatus() {
        if (carInventory.isEmpty()) {
            System.out.println("No cars in inventory.");
            return;
        }

        System.out.println("Car Status:");
        for (Car car : carInventory) {
            String status = car.isRented() ? "Rented" : "Available";
            System.out.println("Model: " + car.getModel() + ", Plate Number: " + car.getPlateNumber() + ", Status: " + status);
        }
    }
}

public class CarRentalSystem {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCar Rental System");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Register New User");
            System.out.println("4. Display Available Cars");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter admin username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String password = scanner.nextLine();
                    if (rentalService.adminLogin(username, password)) {
                        System.out.println("Admin logged in.");
                        adminMenu(rentalService, scanner);
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case "2":
                    System.out.print("Enter customer ID: ");
                    String customerId = scanner.nextLine();
                    if (rentalService.findCustomer(customerId) != null) {
                        System.out.println("User logged in.");
                        userMenu(rentalService, scanner, customerId);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your customer ID: ");
                    String newCustomerId = scanner.nextLine();
                    rentalService.registerCustomer(new Customer(name, newCustomerId));
                    break;

                case "4":
                    System.out.println("Available Cars:");
                    rentalService.viewAvailableCars();
                    break;

                case "5":
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void adminMenu(RentalService rentalService, Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. View Available Cars");
            System.out.println("4. View All Rental Records");
            System.out.println("5. View Car Status");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter car plate number: ");
                    String plateNumber = scanner.nextLine();
                    System.out.print("Enter car model: ");
                    String model = scanner.nextLine();
                    rentalService.addCar(new Car(plateNumber, model));
                    break;

                case "2":
                    System.out.print("Enter car plate number to remove: ");
                    String plateToRemove = scanner.nextLine();
                    rentalService.removeCar(plateToRemove);
                    break;

                case "3":
                    System.out.println("Available Cars:");
                    rentalService.viewAvailableCars();
                    break;

                case "4":
                    System.out.println("All Rental Records:");
                    rentalService.viewAllRentalRecords();
                    break;

                case "5":
                    rentalService.viewCarStatus();
                    break;

                case "6":
                    System.out.println("Admin logged out.");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void userMenu(RentalService rentalService, Scanner scanner, String customerId) {
        while (true) {
            System.out.println("\nUser Menu");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. View Rental Records");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter car model to rent: ");
                    String model = scanner.nextLine();
                    rentalService.rentCar(customerId, model);
                    break;

                case "2":
                    System.out.print("Enter car plate number to return: ");
                    String plateNumber = scanner.nextLine();
                    rentalService.returnCar(plateNumber);
                    break;

                case "3":
                    System.out.println("Your Rental Records:");
                    rentalService.viewUserRentalRecords(customerId);
                    break;

                case "4":
                    System.out.println("User logged out.");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}