import java.util.Scanner;
import java.util.Stack;

public class LinkedListUsingStacks {
    private Stack<Integer> mainStack;
    private Stack<Integer> helperStack;

    public LinkedListUsingStacks() {
        mainStack = new Stack<>();
        helperStack = new Stack<>();
    }

    public void add(int value) {
        mainStack.push(value);
    }

    public int remove() {
        if (mainStack.isEmpty()) {
            System.out.println("The linked list is empty. Nothing to remove.");
            return -1;
        }
  
        while (!mainStack.isEmpty()) {
            helperStack.push(mainStack.pop());
        }
        
        int removedElement = helperStack.pop();
        
        while (!helperStack.isEmpty()) {
            mainStack.push(helperStack.pop());
        }
        
        return removedElement;
    }

    public void display() {
        if (mainStack.isEmpty()) {
            System.out.println("The linked list is empty.");
            return;
        }
        
        while (!mainStack.isEmpty()) {
            helperStack.push(mainStack.pop());
        }
        
        System.out.print("Linked List: ");
        while (!helperStack.isEmpty()) {
            int value = helperStack.pop();
            System.out.print(value + " ");
            mainStack.push(value);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListUsingStacks linkedList = new LinkedListUsingStacks();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add element");
            System.out.println("2. Remove element");
            System.out.println("3. Display linked list");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the value to add: ");
                    int value = scanner.nextInt();
                    linkedList.add(value);
                    System.out.println(value + " added to the linked list.");
                    break;
                case 2:
                    int removedValue = linkedList.remove();
                    if (removedValue != -1) {
                        System.out.println("Removed: " + removedValue);
                    }
                    break;
                case 3:
                    linkedList.display();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}