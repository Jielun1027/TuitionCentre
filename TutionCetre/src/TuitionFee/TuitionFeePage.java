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
import java.time.YearMonth;
import java.util.stream.Collectors;

public class TuitionFeePage {

    private static List<Course> unpaidCourses = new ArrayList<>();
    private static Set<String> paidMonths = new HashSet<>();
    private static Student student;
    private static List<Student> students = new ArrayList<>();
    private static PaymentService paymentService = new PaymentService();
    private static final LocalDate DUE_DATE = LocalDate.of(2024, 12, 5);

    public static void main(String[] args) {
        initializeCourses();
        initializeStudent();
        initializeStudents();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("================");
            System.out.println("Payment");
            System.out.println("================");
            System.out.println("1. Make Payment");
            System.out.println("2. View Payment History");
            System.out.println("3. View Overdue Payments");
            System.out.println("================");
            System.out.print("Enter Selection: ");

            int selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 1:
                    makePayment(scanner);
                    break;
                case 2:
                    viewPaymentHistory();
                    break;
                case 3:
                    viewOverduePayments();
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

    private static void initializeStudents() {
        // Add students without creating payments yet
        students.add(new Student("12345", "John Doe", unpaidCourses));
        students.add(new Student("23456", "Jane Smith", unpaidCourses));
        students.add(new Student("34567", "Alice Johnson", unpaidCourses));
        students.add(new Student("45678", "Bob Lee", unpaidCourses));
        students.add(new Student("56789", "Charlie Brown", unpaidCourses));
        students.add(new Student("67890", "David Wilson", unpaidCourses));
        students.add(new Student("78901", "Emily Davis", unpaidCourses));
        students.add(new Student("89012", "Frank Harris", unpaidCourses));
        students.add(new Student("90123", "Grace Clark", unpaidCourses));
        students.add(new Student("01234", "Hannah Moore", unpaidCourses));

        LocalDate paymentDate = LocalDate.of(2024, 12, 3);
        LocalDate overdueDate = LocalDate.of(2024, 12, 5);

        for (int i = 1; i < students.size(); i += 2) {
            Student paidStudent = students.get(i);
            Payment paidPayment = new Payment("P" + (i + 1), paidStudent, unpaidCourses, 360.0, "Online Banking", paymentDate);
            paymentService.createPayment(paidStudent, unpaidCourses, 360.0, "Online Banking", paymentDate);
        }

        for (int i = 0; i < students.size(); i += 2) {
            Student unpaidStudent = students.get(i);
            if (paymentService.getPaymentsForStudent(unpaidStudent).isEmpty()
                    || paymentService.getPaymentsForStudent(unpaidStudent).stream().noneMatch(p -> YearMonth.from(p.getPaymentDate()).equals(YearMonth.from(paymentDate)))) {
                double overdueAmount = unpaidStudent.getEnrolledCourses().stream()
                        .mapToDouble(Course::getPrice)
                        .sum();
            }
        }
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

        paymentService.createPayment(student, student.getEnrolledCourses(), totalAmount, paymentMethod, LocalDate.now());
        paidMonths.add(currentMonth);

        System.out.println("\nTuition fee for " + currentMonth + " is paid successfully using " + paymentMethod + "!\n");
    }

    private static void viewPaymentHistory() {
        List<Payment> payments = paymentService.getPaymentHistory();
        if (payments.isEmpty()) {
            System.out.println("\nNo payment history available.\n");
            return;
        }

        System.out.println("\n            ***************");
        System.out.println("            Payment History");
        System.out.println("            ***************");

        for (Payment payment : payments) {
            System.out.println("====================================");
            System.out.println("        Payment ID: " + payment.getPaymentId());
            System.out.println("====================================");
            System.out.println("Course                  Price(RM)");
            System.out.println("------------------------------------");

            double totalAmount = 0;
            for (Course course : payment.getCourses()) {
                System.out.printf("%-20s %10.2f\n", course.getCourseName(), course.getPrice());
                totalAmount += course.getPrice();
            }

            System.out.println("------------------------------------");
            System.out.printf("%-20s %10.2f\n", "Total(RM)", totalAmount);
            System.out.println("------------------------------------");
            System.out.println("Payment Method:     " + payment.getPaymentMethod());
            System.out.println("Payment Date:       " + payment.getPaymentDate());
            System.out.println("====================================\n");
        }
    }

    public static void viewOverduePayments() {
        LocalDate today = LocalDate.now();

        System.out.println("\n========================================== Current Month: " + YearMonth.from(today) + " =========================================\n");

        System.out.println("===========================================================================================================");
        System.out.println("                                        Payments Made This Month");
        System.out.println("===========================================================================================================");
        System.out.printf("%-5s %-15s %-15s %-25s %-20s\n", "No.", "Payment ID", "Student ID", "Student Name", "Payment Date");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        List<Payment> currentMonthPayments = paymentService.getPaymentHistory()
                .stream()
                .filter(payment -> YearMonth.from(payment.getPaymentDate()).equals(YearMonth.from(today)))
                .collect(Collectors.toList());

        int index = 1; // Initialize counter for the row numbers
        for (Payment payment : currentMonthPayments) {
            System.out.printf("%-5d %-15s %-15s %-25s %-20s\n", index++, payment.getPaymentId(),
                    payment.getStudent().getStudentId(), payment.getStudent().getStudentName(), payment.getPaymentDate());
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------\n");

        System.out.println("===========================================================================================================");
        System.out.println("                                        Overdue Payments");
        System.out.println("===========================================================================================================");
        System.out.printf("%-5s %-15s %-25s %-20s\n", "No.", "Student ID", "Student Name", "Overdue Amount (RM)");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        int overdueIndex = 1;
        for (Student student : students) {
            boolean paymentMadeThisMonth = currentMonthPayments.stream().anyMatch(p -> p.getStudent().equals(student));
            if (!paymentMadeThisMonth && today.isAfter(DUE_DATE)) {
                double overdueAmount = student.getEnrolledCourses().stream()
                        .mapToDouble(Course::getPrice)
                        .sum();

                System.out.printf("%-5d %-15s %-25s %-20.2f\n", overdueIndex++, student.getStudentId(), student.getStudentName(), overdueAmount);
            }
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------\n");
    }

}
