/**
 * Program Title: Student Grade Calculator
 * Author: Sanketkakade49
 * Date: 2025-10-11
 *
 * Description: Grade calculation based on Marks.
 *
 * Time Complexity: O(1) 
 * Space Complexity: O(1) 
 */
 import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter marks for the following subjects (0 to 100):");

            double physics = getValidMarks(scanner, "Physics");
            double chemistry = getValidMarks(scanner, "Chemistry");
            double maths = getValidMarks(scanner, "Maths");
            double english = getValidMarks(scanner, "English");

            double average = (physics + chemistry + maths + english) / 4.0;

            char grade;
            if (average >= 90) {
                grade = 'A';
            } else if (average >= 80) {
                grade = 'B';
            } else if (average >= 70) {
                grade = 'C';
            } else if (average >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }

            System.out.println("Final Grade: " + grade);
        }
    }

    private static double getValidMarks(Scanner scanner, String subject) {
        while (true) {
            System.out.print(subject + ": ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a number between 0 and 100.");
                scanner.next(); 
                continue;
            }

            double marks = scanner.nextDouble();
            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks must be between 0 and 100.");
            } else {
                return marks;
            }
        }
    }
}
