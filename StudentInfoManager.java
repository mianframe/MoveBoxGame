import java.util.Scanner;

public class StudentInfoManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input student details
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age : ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter course      : ");
        String course = scanner.nextLine();

        System.out.print("Enter year        : ");
        String grade = scanner.nextLine();

        // Display student details
        System.out.println("\n New Student Details:");
        System.out.println("Name    : " + name);
        System.out.println("Age     : " + age);
        System.out.println("Course  : " + course);
        System.out.println("Year   : " + grade);
    }
}