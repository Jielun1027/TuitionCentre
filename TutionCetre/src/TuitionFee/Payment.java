/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TuitionFee;

/**
 *
 * @author Chiew Jie Lun
 */
import java.util.*;
import java.time.LocalDate;
import CourseManagement.Course;
import Student.Student;

public class Payment {

    private String paymentId;
    private Student student;
    private List<Course> courses;
    private double amountPaid;
    private String paymentMethod;
    private LocalDate paymentDate;
    private boolean isPaid;

    public Payment(String paymentId, Student student, List<Course> courses, double amountPaid, String paymentMethod, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.student = student;
        this.courses = courses;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.isPaid = false;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Student getStudent() {
        return student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Payment ID: " + paymentId + ", Student: " + student.getStudentName()
                + ", Amount Paid: " + amountPaid
                + ", Payment Method: " + paymentMethod + ", Date: " + paymentDate
                + ", Status: " + (isPaid ? "Paid" : "Pending");
    }
}
