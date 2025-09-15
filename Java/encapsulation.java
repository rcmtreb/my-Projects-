import java.util.Scanner;

public class Main {
    private String studentID;
    private String name;
    private int age;
    private double gpa;

    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Main student = new Main();

            System.out.print("Enter Student ID: ");
            String studentID = scanner.nextLine();
            student.setStudentID(studentID);

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            student.setName(name);

          
            int age;
            while (true) {
                System.out.print("Enter Age (15-40): ");
                age = scanner.nextInt();
                if (age >= 15 && age <= 40) {
                    student.setAge(age);
                    break;
                } else {
                    System.out.println("Invalid age. Age must be between 15 and 40.");
                }
            }

            
            double gpa;
            while (true) {
                System.out.print("Enter GPA (0.0 - 4.0): ");
                gpa = scanner.nextDouble();
                if (gpa >= 0.0 && gpa <= 4.0) {
                    student.setGpa(gpa);
                    break;
                } else {
                    System.out.println("Invalid GPA. GPA must be between 0.0 and 4.0.");
                }
            }

            System.out.println("\nStudent Information:");
            student.displayInfo();

            scanner.nextLine();

            System.out.print("\nDo you want to enter another student's information? (yes/no): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("no")) {
                break;
            }
        }

        scanner.close();
        System.out.println("Program terminated.");
    }
}