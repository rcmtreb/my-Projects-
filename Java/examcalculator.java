import java.util.Scanner;

public class ExamScoresCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for the number of sections
        System.out.print("Enter the number of sections: ");
        int numSections = scanner.nextInt();

        // Initialize arrays to store scores
        int[][] scores = new int[numSections][];
        for (int i = 0; i < numSections; i++) {
            System.out.print("Enter the number of students in section " + (i + 1) + ": ");
            int numStudents = scanner.nextInt();
            scores[i] = new int[numStudents];

            // Prompt user for individual scores
            System.out.println("Enter exam scores for section " + (i + 1) + ":");
            for (int j = 0; j < numStudents; j++) {
                System.out.print("Student " + (j + 1) + ": ");
                scores[i][j] = scanner.nextInt();
            }
        }

        // Calculate and display statistics
        for (int i = 0; i < numSections; i++) {
            int sum = 0;
            int maxScore = Integer.MIN_VALUE;
            int minScore = Integer.MAX_VALUE;

            for (int score : scores[i]) {
                sum += score;
                maxScore = Math.max(maxScore, score);
                minScore = Math.min(minScore, score);
            }

            double average = (double) sum / scores[i].length;
            System.out.println("\nSection " + (i + 1) + " Statistics:");
            System.out.println("Average Score: " + average);
            System.out.println("Highest Score: " + maxScore);
            System.out.println("Lowest Score: " + minScore);
        }
        scanner.close();
    }
}
