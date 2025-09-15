import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class ArrList {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> savedInputs = new ArrayList<>();
        int x = 0;
        int choice = 0;
        int i = 0;

        while (choice != 7) {
            Menu();
            System.out.print("Input choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                System.out.print("Input number of array elements: ");
                i = input.nextInt();
                for (int j = 0; j < i; j++) {
                    System.out.print("Enter element " + (j + 1) + ": ");
                    int element = input.nextInt();
                    savedInputs.add(element);
                }
            } else if (choice == 2) {
                if (savedInputs.size() > 0) {
                    System.out.print("Input array index: ");
                    i = input.nextInt();
                    if (i >= savedInputs.size()) {
                        System.out.println("Input provided is out of bounds!\n");
                    } else {
                        System.out.print("Input value for index " + i + ": ");
                        int in = input.nextInt();
                        savedInputs.set(i, in);
                    }
                } else {
                    System.out.print("No elements to update.\n");
                }
            } else if (choice == 3) {
                if (savedInputs.size() > 0) {
                    System.out.println("Input element to search: ");
                    boolean isFound = false;
                    int s = input.nextInt();
                    int counter = 0;
                    for (int j = 0; j < savedInputs.size(); j++) {
                        if (savedInputs.get(j) == s) {
                            isFound = true;
                            break;
                        }
                        counter++;
                    }

                    if (isFound) {
                        System.out.println("Element found at index " + counter);
                    } else {
                        System.out.println("Element not found!\n");
                    }
                } else {
                    System.out.print("Array is empty! No element to search!\n");
                }
            } else if (choice == 4) {
                if (savedInputs.size() > 0) {
                    
                    ArrayList<Integer> arrCopy = new ArrayList<>(savedInputs);
                    System.out.println("Arrays elements copied ");
                    for (int num : arrCopy) {
                        System.out.println(num + "  ");
                    }
                } else {
                    System.out.print("Array is empty! Copy not available.\n");
                }
            } else if (choice == 5) {
                if (savedInputs.size() > 0) {
                    Collections.sort(savedInputs);
                    System.out.println("Arrays elements sorted!");
                } else {
                    System.out.print("Array is empty, You can't sort elements.\n");
                }
            } else if (choice == 6) {
                if (savedInputs.size() > 0) {
                    System.out.println("Sorted Array:"); 
                    for (int arrList : savedInputs) {
                        System.out.print(arrList + " ");
                    }
                    System.out.println();
                } else {
                    System.out.print("Array is empty, no elements to display.\n");
                }
            }  else if (choice == 7) {
                System.out.println("Thank you for using the System! ");
                break;
            }
        }
    }

    public static void Menu() {
        System.out.println("\nArraylist Menu ");
        System.out.println("1. Input Number of Array Elements");
        System.out.println("2. Update Element ");
        System.out.println("3. Search Element ");
        System.out.println("4. Copy Array Element ");
        System.out.println("5. Sort Array ");
        System.out.println("6. Display Array ");
        System.out.println("7. Exit");
    }
}