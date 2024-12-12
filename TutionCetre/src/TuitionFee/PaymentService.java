/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TuitionFee;

/**
 *
 * @author Chiew Jie Lun
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import CourseManagement.Course;
import Student.Student;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PaymentService {

    private List<Payment> paymentHistory;

    public PaymentService() {
        this.paymentHistory = new ArrayList<>();
        initializeSamplePayments();
    }

    private void initializeSamplePayments() {
        Student student = new Student("12345", "John Doe", new ArrayList<>());

        List<Course> courses = Arrays.asList(
                new Course("C001", "Science", 120.0),
                new Course("C003", "English", 110.0),
                new Course("C004", "Mathematics", 130.0)
        );

        paymentHistory.add(new Payment("TFP904324", student, courses, 360.0, "Online Banking", LocalDate.of(2024, 9, 15)));
        paymentHistory.add(new Payment("TFP536224", student, courses, 360.0, "Touch'N Go", LocalDate.of(2024, 10, 15)));
        paymentHistory.add(new Payment("TFP965412", student, courses, 360.0, "Credit/Debit Card", LocalDate.of(2024, 11, 15)));
    }

    String generatePaymentId() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);
        return "TFP" + randomNumber;
    }

    public Payment createPayment(Student student, List<Course> courses, double totalAmount, String paymentMethod, LocalDate paymentDate) {
        String paymentId = generatePaymentId();
        Payment payment = new Payment(paymentId, student, courses, totalAmount, paymentMethod, paymentDate);
        paymentHistory.add(payment);
        return payment;
    }

    public boolean updatePaymentStatus(Payment payment) {
        payment.setPaid(true);
        return true;
    }

    public List<Payment> getPaymentHistory() {
        return new ArrayList<>(paymentHistory);
    }

    public List<Payment> getPaymentsForStudent(Student student) {
        return paymentHistory.stream()
                .filter(payment -> payment.getStudent().equals(student))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsWithOverduePayments(List<Student> allStudents, LocalDate dueDate) {
        List<Student> overdueStudents = new ArrayList<>();

        for (Student student : allStudents) {
            List<Course> unpaidCourses = student.getEnrolledCourses()
                    .stream()
                    .filter(course -> {
                        boolean isPaid = paymentHistory.stream()
                                .anyMatch(payment -> payment.getStudent().equals(student)
                                && payment.getCourses().contains(course)
                                && payment.getPaymentDate().isBefore(dueDate));

                        return !isPaid;
                    })
                    .collect(Collectors.toList());

            if (!unpaidCourses.isEmpty()) {
                overdueStudents.add(student);
            }
        }

        return overdueStudents;
    }

}
