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
import Course.Course;
import Student.Student;

public class TutionFeePage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Course course1 = new Course("C001", "Science", 120.0);
        Course course2 = new Course("C003", "English", 110.0);
        Course course3 = new Course("C004", "Mathematics", 130.0);

        Student student1 = new Student("12345", "John Doe", Arrays.asList(course1, course2, course3));

        PaymentService paymentService = new PaymentService();

        System.out.println("\nStudent Name: " + student1.getStudentName());
        System.out.println("=========   ========================   ==========");
        System.out.println("Course ID   Course Name                 Price");
        System.out.println("=========   ========================   ==========");
        double totalAmount = 0;
        for (Course course : student1.getEnrolledCourses()) {
            System.out.printf("%-10s  %-25s   %-10.2f\n", course.getCourseId(), course.getCourseName(), course.getPrice());
            totalAmount += course.getPrice();
        }
        System.out.println("=========   ========================   ==========");
        System.out.printf("%-10s %-25s    %-10.2f\n", "Total", "", totalAmount);
        System.out.println("=========   ========================   ==========");

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

        String currentMonth = LocalDate.now().getMonth().toString();
        currentMonth = currentMonth.substring(0, 1) + currentMonth.substring(1).toLowerCase();

        System.out.println("\nTuition fee for " + currentMonth + " is paid successfully!");

        scanner.close();
    }
}
