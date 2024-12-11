package CourseManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Course Management UI
 * Handles subject creation, validation, and basic CRUD operations.
 */
public class CourseUI {
    static Scanner sc = new Scanner(System.in);
    static List<Course> subjects = new ArrayList<>();
    static BufferedReader enter = new BufferedReader(new InputStreamReader(System.in));
    static Helper helper = new Helper();
    
  public static void hardCodeData() {
        // Creating and adding subjects
        Course subject1 = new Course("SUBJ001", "Introduction to Programming", 100.0);
        subjects.add(subject1);

        Course subject2 = new Course("SUBJ002", "Data Structures and Algorithms", 200.0);
        subjects.add(subject2);

        Course subject3 = new Course("SUBJ003", "Software Engineering", 300.0);
        subjects.add(subject3);

        Course subject4 = new Course("SUBJ004", "Database Management", 150.0);
        subjects.add(subject4);
    }
    public static int mainMenu() {
        System.out.println("**********************");
        System.out.println("*      Selection     *");
        System.out.println("**********************");
        System.out.println("* 1. Subject Module  *");
        System.out.println("* 2. Exit            *");
        System.out.println("**********************");
        System.out.print("\nSelect your choice (1-2): ");

        // Validation of input range (1 to 2)
        return Helper.digit(1, 2, sc);
    }

    public static void tutorMain() throws IOException {
        hardCodeData() ;
                
        // Creating and adding subj
        while (true) {
            System.out.println("*************************");
            System.out.println("*      Subject Module     *");
            System.out.println("*************************");
            System.out.println("*  1. Add New Subject   *");
            System.out.println("*  2. View Subjects     *");
            System.out.println("*  3. Edit Subject      *");
            System.out.println("*  4. Delete Subject    *");
            System.out.println("*  5. Back to Main Menu *");
            System.out.println("*************************");
            System.out.print("\nSelect your choice (1-5): ");

            int choice = helper.digit(1, 5, sc);
            switch (choice) {
                case 1:
                    addSubject();
                    helper.clearConsole();
                    break;
                case 2:
                    displayAll();
                    helper.clearConsole();
                    break;
                case 3:
                    // TODO: Implement editSubject()
                    System.out.println("Feature under development.");
                    break;
                case 4:
                   deleteSubject();
                   helper.clearConsole();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    return;
            }
        }
    }

    /**
     * Add Subject - Handles subject creation with validation.
     */
    public static void addSubject() throws IOException {
        boolean continueAdding = true;
        int numberOfEntries = 0;

        while (continueAdding) {
            String subjectId, subjectName;
            Double price = null;

            // Subject ID Validation
            while (true) {
                System.out.print("Enter Subject ID (e.g., SUBJ123): ");
                subjectId = enter.readLine().trim();
                if (validateSubjectId(subjectId)) break;

            }

            // Subject Name Validation
            while (true) {
                System.out.print("Enter Subject Name: ");
                subjectName = enter.readLine().trim();
                if (validateSubjectName(subjectName)) break;
               
            }

            // Price Validation
            while (true) {
                System.out.print("Enter Price: ");
                String priceInput = enter.readLine().trim();
                if (validatePrice(priceInput)) {
                    price = Double.parseDouble(priceInput); // Parse and store valid price
                    break;
                } 
            }

            // Add Subject to List
            subjects.add(new Course(subjectId, subjectName, price));
            System.out.println("**************************************************");
            System.out.println("                 Subject Details                  ");
            System.out.println("**************************************************");
            System.out.printf("  Subject ID           : %-50s\n", subjectId);
            System.out.printf("  Subject Name         : %-50s\n", subjectName);
            System.out.printf("  Price                : $%-50.2f\n", price);
            System.out.println("**************************************************");

            // Confirmation and Loop Continuation
            System.out.print("Do you want to add another subject? (Y/N): ");
            char continueChar = Character.toUpperCase(enter.readLine().charAt(0));
            continueAdding = (continueChar == 'Y');
            if (continueAdding) helper.clearConsole();

            numberOfEntries++;
        }

        System.out.println("\n" + numberOfEntries + " record(s) have been added.");
        System.out.println("Exiting Add Subject Module...");
    }

    /**
     * Display All Subjects
     */
    public static void displaySubjects() {
        System.out.println("******************** Subject List ********************");
        if (subjects.isEmpty()) {
            System.out.println("No subjects available.");
        } else {
            for (int i = 0; i < subjects.size(); i++) {
                Course subject = subjects.get(i);
                System.out.printf("%d. Subject ID: %s | Name: %s | Price: $%.2f\n", i + 1, subject.getCourseId(), subject.getCourseName(), subject.getPrice());
            }
        }
        System.out.println("******************************************************");
    }

  public static boolean validateSubjectId(String subjectId) {
    // Step 1: Check the format
    Pattern pattern = Pattern.compile("^SUBJ\\d{3}$");
    Matcher matcher = pattern.matcher(subjectId);
    if (!matcher.matches()) {
        System.out.println("Invalid Subject ID format. It should start with 'SUBJ' followed by 3 digits.");
        return false; // Exit early if format is invalid
    }

    // Step 2: Check for duplicates
    for (Course subject : subjects) {
        if (subject.getCourseId().equalsIgnoreCase(subjectId)) {
            System.out.println("Duplicate Subject ID: " + subjectId + " already exists.");
            return false; // Exit early if duplicate is found
        }
    }

    
    return true;
}

    /**
     * Validates Subject Name and checks for duplicates.
     * Must not be empty and can only contain letters and spaces.
     */
    public static boolean validateSubjectName(String subjectName) {
        // Validate format
        if (subjectName.isEmpty() || !subjectName.matches("^[a-zA-Z ]+$")) {
            System.out.println("Invalid Subject Name: It must not be empty and can only contain letters and spaces.");
            return false;
        }

        // Check for duplicates
        for (Course subject : subjects) {
            if (subject.getCourseName().equalsIgnoreCase(subjectName)) {
                System.out.println("Duplicate Subject Name: " + subjectName + " already exists.");
                return false;
            }
        }
        return true;
    }

    /**
     * Validates Price.
     * Must be a positive numeric value between 50 and 1000.
     */
    public static boolean validatePrice(String priceInput) {
        try {
            double price = Double.parseDouble(priceInput); // Convert input to double
            if (price < 50 || price > 1000) {
                System.out.println("Invalid Price: Price must be between 50 and 1000.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid Price: Please enter a numeric value.");
            return false;
        }
    }



/**
     * ******************************************** DISPLAY ALL SUBJECT LIST
     * *********************************************
     */
    public static void displayAll() {
    helper.clearConsole();
    System.out.println("+----------------------------------------------------------------------------------------------------------------+");
    System.out.println("|                                               SUBJECT REPORT                                                    |");
    System.out.println("+----------------------------------------------------------------------------------------------------------------+");
    System.out.println("| NO |      SUBJECT ID       |             SUBJECT NAME             |                SUBJECT DESCRIPTION          |");
    System.out.println("+----------------------------------------------------------------------------------------------------------------+");

    if (subjects.size() != 0) { // Assuming subjects is a List or similar collection

        for (int i = 0; i < subjects.size(); i++) {
            Course subject = subjects.get(i); // Assuming Subject is the class used to represent a subject
            System.out.printf("| %02d | %-21s | %-36s | %-45s |\n",
                    i + 1, subject.getCourseId(), subject.getCourseName(), subject.getPrice());
        }

    } else {
        System.out.println("|                                             No records available                                              |");
    }

    System.out.println("+----------------------------------------------------------------------------------------------------------------+");
    System.out.println("|                                              END OF REPORT                                                     |");
    System.out.println("+----------------------------------------------------------------------------------------------------------------+");
}



    /**
     * ******************************************** DELETE PARTICULAR TUTOR
     * **********************************************/
    
  public static void deleteSubject() throws IOException {
    boolean continueDeleting = true;

    while (continueDeleting) {
        System.out.print("Enter the Subject ID you wish to delete (or -1 to quit): ");
        String subjectIdToDelete = enter.readLine().trim();

        if (subjectIdToDelete.equals("-1")) {
            continueDeleting = false;
            break;
        }

        boolean subjectFound = false;
        Course selectedSubject = null;

        for (Course subject : subjects) {
            if (subject.getCourseId().equalsIgnoreCase(subjectIdToDelete)) {
                subjectFound = true;
                selectedSubject = subject;
                break;
            }
        }

        if (subjectFound) {
            System.out.println("Matching subject found:");
            System.out.println("**************************************************");
            System.out.printf("  Subject ID           : %-50s\n", selectedSubject.getCourseId());
            System.out.printf("  Subject Name         : %-50s\n", selectedSubject.getCourseName());
            System.out.printf("  Price                : $%-50.2f\n", selectedSubject.getPrice());
            System.out.println("**************************************************");

            System.out.print("Do you want to delete this subject? (Y/N): ");
            char choice = Character.toUpperCase(enter.readLine().charAt(0));

            if (choice == 'Y') {
                subjects.remove(selectedSubject);
                System.out.println("Subject with ID " + selectedSubject.getCourseId() + " deleted successfully.");
            } else {
                System.out.println("Subject deletion cancelled.");
            }
        } else {
            System.out.println("No matching subject found with the given ID.");
        }

        System.out.print("Do you want to delete another subject? (Y/N): ");
        char continueChoice = Character.toUpperCase(enter.readLine().charAt(0));

        if (continueChoice != 'Y') {
            continueDeleting = false;
        }
    }
    System.out.println("Exiting Delete Subject Module...");
}

    }