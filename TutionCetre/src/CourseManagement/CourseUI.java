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
        while (true) {
            System.out.println("*************************");
            System.out.println("*      Tutor Module     *");
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
                    displaySubjects();
                    break;
                case 3:
                    // TODO: Implement editSubject()
                    System.out.println("Feature under development.");
                    break;
                case 4:
                    // TODO: Implement deleteSubject()
                    System.out.println("Feature under development.");
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
                System.out.println("Invalid Subject ID format. It should start with 'SUBJ' followed by 3 digits.");
            }

            // Subject Name Validation
            while (true) {
                System.out.print("Enter Subject Name: ");
                subjectName = enter.readLine().trim();
                if (validateSubjectName(subjectName)) break;
                System.out.println("Invalid Subject Name. It should not be empty and can only contain letters and spaces.");
            }

            // Price Validation
            while (true) {
                System.out.print("Enter Price: ");
                String priceInput = enter.readLine().trim();
                if (validatePrice(priceInput)) {
                    price = Double.parseDouble(priceInput); // Parse and store valid price
                    break;
                } else {
                    System.out.println("Invalid Price. Please enter a positive numeric value.");
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

    /**
     * Validates Subject ID format.
     * Format: Must start with 'SUBJ' followed by 3 digits.
     */
    public static boolean validateSubjectId(String subjectId) {
        Pattern pattern = Pattern.compile("^SUBJ\\d{3}$");
        Matcher matcher = pattern.matcher(subjectId);
        return matcher.matches();
    }

    /**
     * Validates Subject Name.
     * Must not be empty and can only contain letters and spaces.
     */
    public static boolean validateSubjectName(String subjectName) {
        return !subjectName.isEmpty() && subjectName.matches("^[a-zA-Z ]+$");
    }

    /**
     * Validates Price.
     * Must be a positive numeric value.
     */
    private static boolean validatePrice(String priceInput) {
        try {
            double price = Double.parseDouble(priceInput);
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
