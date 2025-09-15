import java.util.Scanner;

public class Stack {
    private int[] arr;
    private int top;

    public Stack(int size) {
        arr = new int[size];
        top = -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int x) {
        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
        System.out.println(x + " pushed into the stack.");
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        int x = arr[top];
        top--;
        return x;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.println("Elements in the stack:");
        for (int i = top; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }

    public int size() {
        return top + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the maximum size of the stack: ");
        int size = sc.nextInt();
        Stack s = new Stack(size);
        int choice, x;

        while (true) {
            System.out.println("\nMAIN MENU\n");
            System.out.println("1. Initialize Stack\n");
            System.out.println("2. Push\n");
            System.out.println("3. Pop\n");
            System.out.println("4. Peek\n");
            System.out.println("5. Size\n");
            System.out.println("6. Display\n");
            System.out.println("7. Exit\n");
            System.out.print("Input choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    s = new Stack(size);
                    System.out.println("Stack initialized.");
                    break;
                case 2:
                    System.out.print("Enter element to push: ");
                    x = sc.nextInt();
                    s.push(x);
                    break;
                case 3:
                    x = s.pop();
                    if (x != -1) {
                        System.out.println(x + " popped from the stack.");
                    }
                    break;
                case 4:
                    x = s.peek();
                    if (x != -1) {
                        System.out.println("Top element is: " + x);
                    }
                    break;
                case 5:
                    System.out.println("Size of the stack: " + s.size());
                    break;
                case 6:
                    s.display();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
