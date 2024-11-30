/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CourseManagement;
import CourseManagement.SubjectManagement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jonch
 */
public class SubjectUII {
    static Scanner sc = new Scanner(System.in);
    static List<SubjectManagement> subjects = new ArrayList<>();
    static BufferedReader enter = new BufferedReader(new InputStreamReader(System.in));
   static Helper helper = new Helper();
 
    public static int mainMenu() {
        int choice = 0;

        System.out.println("**********************");
        System.out.println("*      Selection     *");
        System.out.println("**********************");
        System.out.println("* 1.     Subject       *");
        System.out.println("* 2.     Exit        *");
        System.out.println("**********************");

        System.out.print("\n\nSelect your choice (1-2): ");

        //validation of isDigit and not out of Range
        choice = Helper.digit(1, 2, sc);
        return choice;
    }

   

    public static void tutorMain() throws IOException {

       

        while (true) {
            int choice = 0;
           
            System.out.println("*************************");
            System.out.println("*      Tutor Module     *");
            System.out.println("*************************");
            System.out.println("*  1. Add New Subject     *");
            System.out.println("*  2. View Subjct  *");
            System.out.println("*  3. Edit Exist Subject  *");
            System.out.println("*  4. Delete Subjct    *");
            System.out.println("*  5. Back to Previous  *");
            System.out.println("*************************");
            System.out.print("\n\nSelect your choice (1-5): ");
            //validation of isDigit and not out of Range
            choice = helper.digit(1, 5, sc);

            switch (choice) {

                case 1:
                    addSubject();
                 helper.clearConsole();
                    break;
                case 2:
                   displayAll();
                    System.out.println("Press enter to back to previous...");
                    enter.readLine();
                    break;
                case 3:
                     /* editTutor();*/ ;
                    System.out.println("Press enter to back to previous...");
                    enter.readLine();
                    break;
                case 4:
                   /* deleteSubject();*/
                    System.out.println("Press enter to back to previous...");
                    enter.readLine();
                    break;
             
                case 5:
                    System.out.println("Press enter to back to previous...");
                    enter.readLine();
                    return;
               

            }

        }
    }

/**
 * ********************************************
 * ADD SUBJECT
 * ********************************************
 */
public static void addSubject() throws IOException {
    boolean continueAdding = true;
    int numberOfEntries = 0;

    while (continueAdding) {
        String subjectId;
        String subjectName;
        String subjectDescription;

        // Validate Subject ID
        while (true) {
            System.out.print("Enter Subject ID (e.g., SUBJ123): ");
            subjectId = enter.readLine().trim();
            if (validateSubjectId(subjectId)) {
                break;
            } else {
                System.out.println("Invalid Subject ID format. It should start with 'SUBJ' followed by 3 digits.");
            }
        }

        // Validate Subject Name
        while (true) {
            System.out.print("Enter Subject Name: ");
            subjectName = enter.readLine().trim();
            if (validateSubjectName(subjectName)) {
                break;
            } else {
                System.out.println("Invalid Subject Name. It should not be empty and can only contain letters and spaces.");
            }
        }

        // Validate Subject Description
        while (true) {
            System.out.print("Enter Subject Description: ");
            subjectDescription = enter.readLine().trim();
            if (validateSubjectDescription(subjectDescription)) {
                break;
            } else {
                System.out.println("Invalid Subject Description. It should not be empty and must be between 10 and 200 characters.");
            }
        }
subjects.add(new SubjectManagement(subjectId, subjectName, subjectDescription));
        // Display Confirmation
        System.out.println("**************************************************");
        System.out.println("                 Subject Details                  ");
        System.out.println("**************************************************");
        System.out.printf("  Subject ID           : %-50s\n", subjectId);
        System.out.printf("  Subject Name         : %-50s\n", subjectName);
        System.out.printf("  Subject Description  : %s\n", subjectDescription);
        System.out.println("**************************************************");

        // Confirmation to Register
        System.out.print("Confirm adding this subject? (Y/N): ");
        char confirmation = Character.toUpperCase(enter.readLine().charAt(0));
        if (confirmation == 'Y') {
            // Logic to save the subject (Example: saving to a list)
            // subjects.add(new Subject(subjectId, subjectName, subjectDescription)); // Uncomment when Subject class exists
            System.out.println("\nSubject added successfully!");
            numberOfEntries++;
        } else {
            System.out.println("\nSubject addition canceled.");
        }

        // Ask to Continue Adding
        System.out.print("Do you want to add another subject? (Y/N): ");
        char continueChar = Character.toUpperCase(enter.readLine().charAt(0));
        continueAdding = (continueChar == 'Y');
        if (continueAdding) {
            helper.clearConsole();
        }
    }

    System.out.println("\n" + numberOfEntries + " record(s) have been added.");
    System.out.println("Exiting Subject Module...");
    
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
 * Validates Subject Description.
 * Must not be empty and must be between 10 and 200 characters.
 */
public static boolean validateSubjectDescription(String subjectDescription) {
    return !subjectDescription.isEmpty() && subjectDescription.length() >= 10 && subjectDescription.length() <= 200;
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
            SubjectManagement subject = subjects.get(i); // Assuming Subject is the class used to represent a subject
            System.out.printf("| %02d | %-20s | %-30s | %-50s |\n",
                    i + 1, subject.getSubjectId(), subject.getSubjectName(), subject.getSubjectDescription());
        }

    } else {
        System.out.println("|                                             No records available                                              |");
    }

    System.out.println("+----------------------------------------------------------------------------------------------------------------+");
    System.out.println("|                                              END OF REPORT                                                     |");
    System.out.println("+----------------------------------------------------------------------------------------------------------------+");
}
}
