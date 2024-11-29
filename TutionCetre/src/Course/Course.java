/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Course;

/**
 *
 * @author User
 */
public class Course {
    private String courseId;
    private String courseName;
    private double price;
    
    public Course(String courseId, String courseName, double price){
        this.courseId = courseId;
        this.courseName = courseName;
        this.price = price;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course{");
        sb.append("courseId=").append(courseId);
        sb.append(", courseName=").append(courseName);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
    
}
