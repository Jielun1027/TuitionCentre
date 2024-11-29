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
import Course.Course;
import Student.Student;

public class Payment {
    private String paymentId;   
    private Student student;  
    private Course course;   
    private double amountPaid; 
    private String paymentMethod; 
    private LocalDate paymentDate; 
    private boolean isPaid;     

    public Payment(String paymentId, Student student, Course course, double amountPaid, String paymentMethod) {
        this.paymentId = paymentId;
        this.student = student;
        this.course = course;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentDate = LocalDate.now();
        this.isPaid = false;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
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
                + ", Course: " + course.getCourseName() + ", Amount Paid: " + amountPaid
                + ", Payment Method: " + paymentMethod + ", Date: " + paymentDate
                + ", Status: " + (isPaid ? "Paid" : "Pending");
    }
}
