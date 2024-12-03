/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Student;

/**
 *
 * @author User
 */

import java.util.*;
import CourseManagement.Course;

public class Student {
    private String studentId;
    private String studentName;
    private List<Course> enrolledCourses;
    
    public Student(String studentId, String studentName, List<Course> enrolledCourses){
        this.studentId = studentId;
        this.studentName = studentName;
        this.enrolledCourses = enrolledCourses;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void displayCourses(){
        System.out.println("Courses enrolled by " + studentName + " (ID: " + studentId + "):");
        for(Course course: enrolledCourses) {
            System.out.println(course);
        }
    }
}
