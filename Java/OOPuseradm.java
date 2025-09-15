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

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}

class RentalService {
    private List<Car> carInventory = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    public void addCar(Car car) {
        carInventory.add(car);
        System.out.println("Car added: " + car);
    }

    public void registerCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer registered: " + customer);
    }

    public List<Car> getCarInventory() { // New method to access carInventory
        return carInventory;
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
        System.out.println("Car rented: " + car + " to customer " + customer);
    }

    public void returnCar(String plateNumber) {
        for (Car car : carInventory) {
            if (car.getPlateNumber().equalsIgnoreCase(plateNumber) && car.isRented()) {
                car.returnCar();
                System.out.println("Car returned: " + car);
                return;
            }
        }
        System.out.println("Car not found or already returned.");
    }

    public boolean adminLogin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}

public class CarRentalSystem {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();
        Scanner scanner = new Scanner(System.in);

        // Sample data
        rentalService.addCar(new Car("123ABC", "Toyota Corolla"));
        rentalService.addCar(new Car("456DEF", "Honda Civic"));
        rentalService.registerCustomer(new Customer("Alice", "C001"));
        rentalService.registerCustomer(new Customer("Bob", "C002"));

        while (true) {
            System.out.println("\nEdCarDo Rental System");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                // Admin Login
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
            } else if (choice == 2) {
                // User Login
                System.out.print("Enter customer ID: ");
                String customerId = scanner.nextLine();
                if (rentalService.findCustomer(customerId) != null) {
                    System.out.println("User logged in.");
                    userMenu(rentalService, scanner, customerId);
                } else {
                    System.out.println("Customer not found.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void adminMenu(RentalService rentalService, Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add Car");
            System.out.println("2. View Cars");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (option == 1) {
                System.out.print("Enter car plate number: ");
                String plateNumber = scanner.nextLine();
                System.out.print("Enter car model: ");
                String model = scanner.nextLine();
                rentalService.addCar(new Car(plateNumber, model));
            } else if (option == 2) {
                System.out.println("Car Inventory:");
                for (Car car : rentalService.getCarInventory()) { // Use the getter method
                    System.out.println(car);
                }
            } else if (option == 3) {
                System.out.println("Admin logged out.");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    private static void userMenu(RentalService rentalService, Scanner scanner, String customerId) {
        while (true) {
            System.out.println("\nUser Menu");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (option == 1) {
                System.out.print("Enter car model to rent: ");
                String model = scanner.nextLine();
                rentalService.rentCar(customerId, model);
            } else if (option == 2) {
                System.out.print("Enter car plate number to return: ");
                String plateNumber = scanner.nextLine();
                rentalService.returnCar(plateNumber);
            } else if (option == 3) {
                System.out.println("User logged out.");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }
    }
}