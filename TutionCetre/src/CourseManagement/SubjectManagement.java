package CourseManagement;

/**
 * Class to manage Subject details
 */
public class SubjectManagement {
    private String subjectId;
    private String subjectName;
    private String subjectDescription;

    // Constructor
    public SubjectManagement(String subjectId, String subjectName, String subjectDescription) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
    }

    // Getters and Setters
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    // Optional: Override toString for convenient printing
    @Override
    public String toString() {
        return String.format("Subject ID: %s, Name: %s, Description: %s", 
                             subjectId, subjectName, subjectDescription);
    }
}
