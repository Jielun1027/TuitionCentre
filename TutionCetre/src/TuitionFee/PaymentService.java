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
import java.util.List;
import CourseManagement.Course;
import Student.Student;

public class PaymentService {
    private List<Payment> payments;
    
    public PaymentService(){
        this.payments = new ArrayList<>();
    }
    
    public Payment createPayment(Student student, Course course, double amountPaid, String paymentMethod) {
        String paymentId = "TFP" + (payments.size() + 1);
        Payment payment = new Payment(paymentId, student, course, amountPaid, paymentMethod);
        payments.add(payment);
        return payment;
    }
    
    public boolean updatePaymentStatus(Payment payment){
        payment.setPaid(true);
        return true;
    }
    
//    public void displayPaymentsForStudent(String studentId) {
//        System.out.println("Payments for StudentID: " + studentId);
//        for(Payment payment: payments){
//            if(payment.getStudent().getStudentId().equals(studentId)){
//                System.out.println(payment);
//            }
//        }
//    }
//  
}
