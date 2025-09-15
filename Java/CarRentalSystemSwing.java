import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarRentalSystemSwing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}

// Main application frame
class MainFrame extends JFrame {
    private RentalService rentalService;

    public MainFrame() {
        rentalService = new RentalService();

        setTitle("Car Rental System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JButton adminButton = new JButton("Admin Login");
        JButton userButton = new JButton("User Login");
        JButton createAccountButton = new JButton("Create Account");
        JButton exitButton = new JButton("Exit");

        adminButton.addActionListener(e -> new AdminLoginFrame(rentalService));
        userButton.addActionListener(e -> new UserLoginFrame(rentalService));
        createAccountButton.addActionListener(e -> new CreateAccountFrame(rentalService));
        exitButton.addActionListener(e -> System.exit(0));

        add(adminButton);
        add(userButton);
        add(createAccountButton);
        add(exitButton);

        setVisible(true);
    }
}

// Create Account frame
class CreateAccountFrame extends JFrame {
    public CreateAccountFrame(RentalService rentalService) {
        setTitle("Create Account");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel idLabel = new JLabel("Customer ID:");
        JTextField idField = new JTextField();
        JButton createButton = new JButton("Create");

        createButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            if (!name.isEmpty() && !id.isEmpty()) {
                rentalService.addCustomer(new Customer(id, name));
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel());
        panel.add(createButton);

        add(panel);
        setVisible(true);
    }
}

// Admin login frame
class AdminLoginFrame extends JFrame {
    public AdminLoginFrame(RentalService rentalService) {
        setTitle("Admin Login");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            Admin admin = new Admin();
            if (admin.login(username, password)) {
                new AdminPanel(rentalService);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }
}

// User login frame
class UserLoginFrame extends JFrame {
    public UserLoginFrame(RentalService rentalService) {
        setTitle("User Login");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel idLabel = new JLabel("Customer ID:");
        JTextField idField = new JTextField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String customerId = idField.getText();
            Customer customer = rentalService.findCustomer(customerId);
            if (customer != null) {
                new UserPanel(rentalService, customer);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }
}

// Admin panel
class AdminPanel extends JFrame {
    public AdminPanel(RentalService rentalService) {
        setTitle("Admin Panel");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JButton addCarButton = new JButton("Add Car");
        JButton removeCarButton = new JButton("Remove Car");
        JButton viewAvailableCarsButton = new JButton("View Available Cars");
        JButton viewRecordsButton = new JButton("View Rental Records");
        JButton viewStatusButton = new JButton("View Car Status");
        JButton logoutButton = new JButton("Logout");

        addCarButton.addActionListener(e -> {
            String plateNumber = JOptionPane.showInputDialog(this, "Enter Plate Number:");
            String model = JOptionPane.showInputDialog(this, "Enter Model:");
            if (plateNumber != null && model != null) {
                rentalService.addCar(new Car(plateNumber, model));
                JOptionPane.showMessageDialog(this, "Car added successfully!");
            }
        });

        removeCarButton.addActionListener(e -> {
            String plateNumber = JOptionPane.showInputDialog(this, "Enter Plate Number to Remove:");
            rentalService.removeCar(plateNumber);
            JOptionPane.showMessageDialog(this, "Car removed successfully!");
        });

        viewAvailableCarsButton.addActionListener(e -> new CarTableFrame(rentalService.getAvailableCars(), "Available Cars"));

        viewRecordsButton.addActionListener(e -> new RentalRecordTableFrame(rentalService.getAllRentalRecords()));

        viewStatusButton.addActionListener(e -> new CarTableFrame(rentalService.getAllCars(), "Car Status"));

        logoutButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(addCarButton);
        panel.add(removeCarButton);
        panel.add(viewAvailableCarsButton);
        panel.add(viewRecordsButton);
        panel.add(viewStatusButton);
        panel.add(logoutButton);

        add(panel);
        setVisible(true);
    }
}

// User panel
class UserPanel extends JFrame {
    public UserPanel(RentalService rentalService, Customer customer) {
        setTitle("User Panel");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JButton rentCarButton = new JButton("Rent Car");
        JButton returnCarButton = new JButton("Return Car");
        JButton viewRecordsButton = new JButton("View Rental Records");
        JButton logoutButton = new JButton("Logout");

        rentCarButton.addActionListener(e -> {
            String model = JOptionPane.showInputDialog(this, "Enter Car Model to Rent:");
            rentalService.rentCar(customer.getId(), model);
            JOptionPane.showMessageDialog(this, "Car rented successfully!");
        });

        returnCarButton.addActionListener(e -> {
            String plateNumber = JOptionPane.showInputDialog(this, "Enter Car Plate Number to Return:");
            rentalService.returnCar(plateNumber);
            JOptionPane.showMessageDialog(this, "Car returned successfully!");
        });

        viewRecordsButton.addActionListener(e -> new RentalRecordTableFrame(rentalService.getUserRentalRecords(customer.getId())));

        logoutButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(rentCarButton);
        panel.add(returnCarButton);
        panel.add(viewRecordsButton);
        panel.add(logoutButton);

        add(panel);
        setVisible(true);
    }
}

// Car table frame
class CarTableFrame extends JFrame {
    public CarTableFrame(List<Car> cars, String title) {
        setTitle(title);
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columnNames = {"Plate Number", "Model", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Car car : cars) {
            String status = car.isRented() ? "Rented" : "Available";
            model.addRow(new Object[]{car.getPlateNumber(), car.getModel(), status});
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table));

        setVisible(true);
    }
}

// Rental record table frame
class RentalRecordTableFrame extends JFrame {
    public RentalRecordTableFrame(List<RentalRecord> records) {
        setTitle("Rental Records");
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columnNames = {"Customer", "Car", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (RentalRecord record : records) {
            String status = record.isReturned() ? "Returned" : "Rented";
            model.addRow(new Object[]{record.getCustomer().getName(), record.getCar().getModel(), status});
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table));

        setVisible(true);
    }
}

// Car class
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

    public void setRented(boolean rented) {
        isRented = rented;
    }
}

// Customer class
class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// Admin class
class Admin {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    public boolean login(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }
}

// RentalRecord class
class RentalRecord {
    private Customer customer;
    private Car car;
    private boolean isReturned;

    public RentalRecord(Customer customer, Car car) {
        this.customer = customer;
        this.car = car;
        this.isReturned = false;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}

// RentalService class
class RentalService {
    private List<Car> cars;
    private List<Customer> customers;
    private List<RentalRecord> rentalRecords;

    public RentalService() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentalRecords = new ArrayList<>();

        // Preload data for testing
        customers.add(new Customer("C001", "John Doe"));
        customers.add(new Customer("C002", "Jane Smith"));
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(String plateNumber) {
        cars.removeIf(car -> car.getPlateNumber().equals(plateNumber));
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (!car.isRented()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomer(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public void rentCar(String customerId, String model) {
        Customer customer = findCustomer(customerId);
        if (customer != null) {
            for (Car car : cars) {
                if (car.getModel().equalsIgnoreCase(model) && !car.isRented()) {
                    car.setRented(true);
                    rentalRecords.add(new RentalRecord(customer, car));
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Car not available!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void returnCar(String plateNumber) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(plateNumber) && car.isRented()) {
                car.setRented(false);
                for (RentalRecord record : rentalRecords) {
                    if (record.getCar() == car && !record.isReturned()) {
                        record.setReturned(true);
                        return;
                    }
                }
            }
        }
    }

    public List<RentalRecord> getUserRentalRecords(String customerId) {
        List<RentalRecord> records = new ArrayList<>();
        for (RentalRecord record : rentalRecords) {
            if (record.getCustomer().getId().equals(customerId)) {
                records.add(record);
            }
        }
        return records;
    }

    public List<RentalRecord> getAllRentalRecords() {
        return rentalRecords;
    }
}