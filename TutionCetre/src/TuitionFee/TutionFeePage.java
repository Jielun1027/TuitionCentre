/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TuitionFee;

/**
 *
 * @author Chiew Jie Lun
 */
import java.time.LocalDate;
import java.util.*;
import CourseManagement.Course;
import Student.Student;

public class TutionFeePage {

    private static List<Course> unpaidCourses = new ArrayList<>();
    private static Set<String> paidMonths = new HashSet<>();
    private static Student student;

    public static void main(String[] args) {
        initializeCourses();
        initializeStudent();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("================");
            System.out.println("Payment");
            System.out.println("================");
            System.out.println("1. Make Payment");
            System.out.println("2. View Payment History");
            System.out.println("================");
            System.out.print("Enter Selection: ");

            int selection = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (selection) {
                case 1:
                    makePayment(scanner);
                    break;
                case 2:
                    System.out.println("This feature is still under development");
                    break;
                default:
                    System.out.println("Invalid selection. Exiting...");
                    return;
            }
        }
    }

    private static void initializeCourses() {
        unpaidCourses.add(new Course("C001", "Science", 120.0));
        unpaidCourses.add(new Course("C003", "English", 110.0));
        unpaidCourses.add(new Course("C004", "Mathematics", 130.0));
    }

    private static void initializeStudent() {
        student = new Student("12345", "John Doe", unpaidCourses);
    }

    private static void makePayment(Scanner scanner) {
        String currentMonth = LocalDate.now().getMonth().toString();
        currentMonth = currentMonth.substring(0, 1) + currentMonth.substring(1).toLowerCase();

        if (paidMonths.contains(currentMonth)) {
            System.out.println("\nTuition fee for " + currentMonth + " has already been paid.\n");
            return;
        }

        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("\nNo courses to be paid for.\n");
            return;
        }

        // Display student information and courses to be paid
        System.out.println("\n\nStudent Name: " + student.getStudentName());
        System.out.println("\n              Tuition Fee(" + currentMonth + ")");
        System.out.println("========   ========================   ============");
        System.out.println("Course ID   Course Name                 Price(RM)");
        System.out.println("========   ========================   ============");

        double totalAmount = 0;
        for (Course course : student.getEnrolledCourses()) {
            System.out.printf("%-10s  %-25s   %-10.2f\n", course.getCourseId(), course.getCourseName(), course.getPrice());
            totalAmount += course.getPrice();
        }
        System.out.println("========   ========================   ============");
        System.out.printf("%-10s %-25s    %-10.2f\n", "Total(RM)", "", totalAmount);
        System.out.println("========   ========================   ============");

        System.out.println("\nSelect a payment method:");
        System.out.println("1. Credit/Debit Card");
        System.out.println("2. Online Banking");
        System.out.println("3. Touch'N GO");
        System.out.print("Enter your choice (1 or 2 or 3): ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        String paymentMethod = "";
        switch (paymentChoice) {
            case 1:
                paymentMethod = "Credit/Debit Card";
                break;
            case 2:
                paymentMethod = "Online Banking";
                break;
            case 3:
                paymentMethod = "Touch'N Go";
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
                return;
        }

        paidMonths.add(currentMonth);

        System.out.println("\nTuition fee for " + currentMonth + " is paid successfully using " + paymentMethod + "!\n");
    }
}
