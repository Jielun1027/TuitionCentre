/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubjectManagementModule;
import util.Helper;
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
public class SubjectUI {
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

    /**
     * ***********************************************EDIT TUTOR MAIN
     * MODULE************************************************
     
    public static void editTutor() throws IOException {
        boolean continueEditing = true;

        while (true) {
            continueEditing = true;
            helper.clearConsole();
            String ic;
            System.out.println("==============================");
            System.out.println("       Edit Tutor Details     ");
            System.out.println("==============================");

            System.out.print("Please enter tutor IC or 'exit' to quit: ");
            ic = enter.readLine();

            if (ic.equalsIgnoreCase("exit")) {
                continueEditing = false;
                break;
            }

            boolean tutorFound = false;
            Tutor selectedTutor = null;

            for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
                Tutor tutor = tutors.getEntry(i);

                if (tutor.getIc().equalsIgnoreCase(ic)) {
                    tutorFound = true;
                    selectedTutor = tutor;
                    break;
                }
            }

            if (!tutorFound) {
                System.out.println("Tutor with IC " + ic + " not found.");
                System.out.println("Please enter correct IC");
                helper.clearConsole();
                continue;
            }

            while (continueEditing) {

                int choice = 0;
                int invalid = 0;
                boolean confirmEditingDetails = true;
                helper.clearConsole();

                System.out.println("**************************************************");
                System.out.println("                Tutor Details            ");
                System.out.println("**************************************************");
                System.out.println("                                         ");
                System.out.printf("  Faculty     : %-50s\n", selectedTutor.getFaculty());
                System.out.printf("  Name        : %-50s\n", selectedTutor.getName());
                System.out.printf("  IC    Number: %s\n", selectedTutor.getIc());
                System.out.printf("  Gender      : %s\n", selectedTutor.getGender());
                System.out.printf("  Phone Number: %s\n", selectedTutor.getPhoneNumber());
                System.out.printf("  Email       : %s\n", selectedTutor.getEmail());
                System.out.print("  Subjects    : ");
                ListInterface<String> subjects = selectedTutor.getSubjects();
                if (subjects.isEmpty()) {
                    System.out.println("No subjects selected");
                } else {
                    for (int i = 1; i <= subjects.getNumberOfEntries(); i++) {
                        System.out.print(subjects.getEntry(i));
                        if (i < subjects.getNumberOfEntries()) {
                            System.out.print(", ");
                        }
                    }
                }
                System.out.println("\n**************************************************");
                System.out.println("\n");

                System.out.println("*************************");
                System.out.println("*   Edit Tutor Details  *");
                System.out.println("*************************");
                System.out.println("*  1. Phone Number      *");
                System.out.println("*  2. Email             *");
                System.out.println("*  3. Subject           *");
                System.out.println("*  4. Exit              *");
                System.out.println("*************************");
                System.out.print("\n\nSelect your choice (1-4): ");
                //validation of isDigit and not out of Range
                choice = helper.digit(1, 4, sc);

                switch (choice) {
                    case 1:
                        editPhoneNumber(selectedTutor);
                        break;
                    case 2:
                        editEmail(selectedTutor);
                        break;
                    case 3:
                        editSubjects(selectedTutor);

                        break;

                    case 4:
                        continueEditing = false;
                        break;
                }
            }
        }
    }
}
    /**
     * ***************************************************EDIT TUTOR
     * DETAILS****************************************************
     
    public static void editPhoneNumber(Tutor selectedTutor) throws IOException {
        boolean confirmEditingDetails = true;
        int invalid = 0;

        while (confirmEditingDetails) {

            String phoneNumber = "";
            System.out.println("Change Phone Number ? ");
            System.out.println("Current Phone Number > " + selectedTutor.getPhoneNumber());
            do {
                if (invalid != 0) {
                    System.out.println("Wrong format phone number!!! Please re-enter...");
                }

                invalid = 0;
                System.out.print("Enter NEW phone number (01xxxxxxxx) > ");
                phoneNumber = enter.readLine();
                if (phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '1' && phoneNumber.length() == 10 || phoneNumber.length() == 11) {
                    for (int j = 0; j < phoneNumber.length(); j++) {
                        if (!Character.isDigit(phoneNumber.charAt(j))) {
                            invalid++;
                        }
                    }
                } else {
                    invalid++;
                }

            } while (invalid != 0);

            System.out.print("Confirm want to change? (Y/N) ");
            char con = enter.readLine().charAt(0);
            // Consume the newline character left in the input buffer

            con = Character.toUpperCase(con);

            // Confirmation of adding
            if (con == 'Y' && phoneNumber != null) {

                selectedTutor.setPhoneNumber(phoneNumber);
                System.out.println("Phone Number modified Successfully.");

            } else {
                System.out.println("Phone Number modified Unsuccessfully.");

            }

            System.out.print("Do you want to continue changed Phone Number? (Y/N) ");
            char continueChar = enter.readLine().charAt(0);
            // Consume the newline character left in the input buffer

            continueChar = Character.toUpperCase(continueChar);

            if (continueChar == 'Y') {

                confirmEditingDetails = true;
                helper.clearConsole();

            } else {
                confirmEditingDetails = false;
                System.out.println("Press enter to quit...");
                enter.readLine();
                helper.clearConsole();

            }

        }
    }

    public static void editEmail(Tutor selectedTutor) throws IOException {
        boolean confirmEditingDetails = true;
        int invalid = 0;

        while (confirmEditingDetails) {

            String email = "";
            System.out.println("Change Email ? ");
            System.out.println("Current Email > " + selectedTutor.getEmail());
            do {
                if (invalid != 0) {
                    System.out.println("Invalid email format! Please re-enter...");
                }

                invalid = 0;
                System.out.print("Enter the email address > ");
                email = enter.readLine();

                // Regular expression for a basic email format
                String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(emailRegex);
                Matcher matcher = pattern.matcher(email);

                if (!matcher.matches()) {
                    invalid++;
                }
            } while (invalid != 0);
            System.out.print("Confirm want to change? (Y/N) ");
            char con = enter.readLine().charAt(0);
            // Consume the newline character left in the input buffer

            con = Character.toUpperCase(con);

            // Confirmation of adding
            if (con == 'Y' && email != null) {

                selectedTutor.setEmail(email);
                System.out.println("Email modified Successfully.");

            } else {
                System.out.println("Email modified Unsuccessfully.");

            }

            System.out.print("Do you want to continue changed Email? (Y/N) ");
            char continueChar = enter.readLine().charAt(0);
            // Consume the newline character left in the input buffer

            continueChar = Character.toUpperCase(continueChar);

            if (continueChar == 'Y') {

                confirmEditingDetails = true;
                helper.clearConsole();

            } else {
                confirmEditingDetails = false;
                System.out.println("Press enter to quit...");
                enter.readLine();
                helper.clearConsole();

            }

        }
    }

    public static void editSubjects(Tutor selectedTutor) throws IOException {
        boolean confirmEditingDetails = true;

        while (confirmEditingDetails) {
            String subject = "";

            System.out.println("");
            System.out.println("");
            System.out.println("Change Subject? ");
            System.out.print("Current Subjects    : ");

            ListInterface<String> existSubjects = selectedTutor.getSubjects();
            if (existSubjects.isEmpty()) {
                System.out.println("No subjects selected");
            } else {
                for (int i = 1; i <= existSubjects.getNumberOfEntries(); i++) {
                    System.out.print(existSubjects.getEntry(i));
                    if (i < existSubjects.getNumberOfEntries()) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println("");

            helper.printSubject();

            System.out.print("Select choice (8 to END the selection) > ");
            int subject_Selection = helper.digit(1, 8, sc);

            if (subject_Selection == 8) {
                break; // Exit the subject selection loop if "Finish" is selected
            }

            // Set the subject based on the selection
            switch (subject_Selection) {
                case 1:
                    subject = "BACS2042";
                    break;
                case 2:
                    subject = "BACS2163";
                    break;
                case 3:
                    subject = "BAIT2203";
                    break;
                case 4:
                    subject = "BMMS2633";
                    break;
                case 5:
                    subject = "BACS2063";
                    break;
                case 6:
                    subject = "BACS2073";
                    break;
                case 7:
                    subject = "BACS2103";
                    break;
                default:
                    break;
            }
            if (existSubjects.contains(subject)) {
                // Subject already selected, prompt to remove it
                System.out.print("Subject < " + subject + " > already selected. Do you want to remove it? (Y/N) > ");
                String removeChoice = enter.readLine();
                if (removeChoice.equalsIgnoreCase("Y")) {
                    int indexToRemove = existSubjects.indexOf(subject);
                    if (indexToRemove != -1) { // Check if the subject was found in the list
                        existSubjects.remove(indexToRemove + 1);
                        System.out.println("\nSubject removed: " + subject + "\n");
                    } else {
                        System.out.println("Subject not found in the list.");
                    }
                }
            } else {
                // Subject not selected, prompt to add it
                existSubjects.add(subject);
                System.out.println("Subject added: " + subject);
            }

            System.out.print("Do you want to continue changed Subject(s)? (Y/N) ");
            char continueChar = enter.readLine().charAt(0);
            // Consume the newline character left in the input buffer

            continueChar = Character.toUpperCase(continueChar);

            if (continueChar == 'Y') {

                confirmEditingDetails = true;
                helper.clearConsole();

            } else {
                confirmEditingDetails = false;
                System.out.println("Press enter to quit...");
                enter.readLine();
                helper.clearConsole();

            }

        }
    }
*/
    /**
     * ***************************************************Search By Any
     * Words****************************************************
    
    public static void searchTutors() throws IOException {
        boolean continueSearching = true;

        while (continueSearching) {
            System.out.print("Enter search keyword (or -1 to quit): ");
            String keyword = enter.readLine();

            if (keyword.equals("")) {
                System.out.println("Please enter a keyword or -1 to quit.");
                continue;
            }

            if (keyword.equals("-1")) {
                continueSearching = false;
                break;
            }

            System.out.println("+-------------------------------------------------+");
            System.out.println("| NO |   FACULTY   |             NAME             |");
            System.out.println("+-------------------------------------------------+");

            ListInterface<Tutor> matchingTutors = new ArrayList<>();
            boolean foundMatch = false;

            for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
                Tutor tutor = tutors.getEntry(i);

                if (tutor.getName().toLowerCase().contains(keyword.toLowerCase())
                        || tutor.getIc().equalsIgnoreCase(keyword)
                        || tutor.getFaculty().equalsIgnoreCase(keyword)
                        || tutor.getSubjects().contains(keyword.toUpperCase())) {

                    foundMatch = true;
                    matchingTutors.add(tutor);

                    System.out.printf("| %02d |    %-4s     |%-30s|\n", i, tutor.getFaculty(), tutor.getName());
                }
            }

            if (!foundMatch) {
                System.out.println("|           No Matching Tutor Found               |");
            }

            System.out.println("+-------------------------------------------------+");

            if (matchingTutors.getNumberOfEntries() > 0) {
                System.out.print("Enter the record number to see details or 0 to quit: ");
                int choice = helper.digit(0, matchingTutors.getNumberOfEntries(), sc);

                if (choice == 0) {
                    continueSearching = false;
                } else if (choice >= 1 && choice <= matchingTutors.getNumberOfEntries()) {
                    Tutor selectedTutor = matchingTutors.getEntry(choice);
                    displayTutorDetails(selectedTutor);
                } else {
                    System.out.println("Invalid choice. Please enter a valid record number.");
                }
            }
        }
    }
 */
    /**
     * ******************************************** DISPLAY PARTICULAR TUTOR
     * LIST *********************************************
    
    public static void displayTutorDetails(Tutor tutor) throws IOException {
        System.out.println("**************************************************");
        System.out.println("                Tutor Details            ");
        System.out.println("**************************************************");
        System.out.println("                                         ");
        System.out.printf("  Faculty     : %-50s\n", tutor.getFaculty());
        System.out.printf("  Name        : %-50s\n", tutor.getName());
        System.out.printf("  IC    Number: %s\n", tutor.getIc());
        System.out.printf("  Gender      : %s\n", tutor.getGender());

        System.out.printf("  Phone Number: %s\n", tutor.getPhoneNumber());
        System.out.printf("  Email       : %s\n", tutor.getEmail());
        System.out.print("  Subjects    : ");
        ListInterface<String> subjects = tutor.getSubjects();
        if (subjects.isEmpty()) {
            System.out.println("No subjects selected");
        } else {
            for (int i = 1; i <= subjects.getNumberOfEntries(); i++) {
                System.out.print(subjects.getEntry(i));
                if (i < subjects.getNumberOfEntries()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println("\n\n**************************************************");

        System.out.println("");
        System.out.println("Press enter to continue...");
        enter.readLine();

    }
 */
    /**
     * ******************************************** DELETE PARTICULAR TUTOR
     * *********************************************
    
    public static void deleteTutor() throws IOException {
        boolean continueDeleting = true;

        while (continueDeleting) {
            System.out.print("Enter the IC you wish to delete (or -1 to quit): ");
            String icToDelete = enter.readLine();

            if (icToDelete.equals("-1")) {
                continueDeleting = false;
                break;
            }

            boolean tutorFound = false;
            Tutor selectedTutor = null;

            for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
                Tutor tutor = tutors.getEntry(i);

                if (tutor.getIc().equalsIgnoreCase(icToDelete)) {
                    tutorFound = true;
                    selectedTutor = tutor;
                    break;
                }
            }

            if (tutorFound) {
                System.out.println("Matching tutor found:");
                displayTutorDetails(selectedTutor);

                System.out.print("Do you want to delete this tutor? (Y/N): ");
                char choice = Character.toUpperCase(enter.readLine().charAt(0));

                if (choice == 'Y') {
                    int index = tutors.indexOf(selectedTutor);
                    Tutor deletedTutor = tutors.remove(index + 1);

                    System.out.print("Tutor with IC " + deletedTutor.getIc() + " deleted successfully. \nDo you want to delete another tutor? (Y/N): ");
                    char continueChoice = enter.readLine().charAt(0);

                    if (Character.toUpperCase(continueChoice) != 'Y') {
                        continueDeleting = false;
                    }
                } else {
                    System.out.println("Tutor deletion cancelled.");
                }
            } else {
                System.out.println("No matching tutor found with the given IC.");
                System.out.print("Do you want to continue deleting tutors? (Y/N): ");
                char continueChoice = Character.toUpperCase(enter.readLine().charAt(0));

                if (continueChoice != 'Y') {
                    continueDeleting = false;
                }
            }
        }
    }

    //feel free to improve the bubblesort
    public static void filterTutor() throws IOException {

        boolean continueFilter = true;

        while (continueFilter) {

            helper.clearConsole();
            System.out.println("*************************");
            System.out.println("*      Filter By        *");
            System.out.println("*************************");
            System.out.println("*  1. Gender            *"); //Percentage
            System.out.println("*  2. Faculty           *"); //Percentage
            System.out.println("*  3. Subject Teached   *"); //Percentage
            System.out.println("*  4. Back to Previous  *");
            System.out.println("*************************");
            System.out.print("\n\nSelect your choice (1-4): ");

            int choice = helper.digit(1, 4, sc);
            switch (choice) {

                case 1:
                    filterByGender();
                    System.out.println("Press enter to continue...");
                    enter.readLine();
                    break;
                case 2:
                    filterByFaculty();
                    System.out.println("Press enter to continue...");
                    enter.readLine();
                    break;
                case 3:
                    filterBySubject();
                    System.out.println("Press enter to continue...");
                    enter.readLine();
                    break;
                case 4:
                    continueFilter = false;
                    break;
                default:
                    System.out.println("Press enter to continue...");
                    enter.readLine();
                    break;

            }

        }

    }

    public static void filterByGender() {

        String gender = "N";
        String words = "NO";
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Quit");
        System.out.println("Please select gender to filter (1-3)> ");
        int choice = helper.digit(1, 3, sc);

        if (choice == 3) {

            return;
        }
        if (choice == 1) {

            gender = "M";
            words = "Male";
        } else if (choice == 2) {

            gender = "F";
            words = "Female";
        }

        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                      TUTOR LIST <" + words + ">                                                        |");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| NO |   FACULTY   |             NAME             |        IC NO        |    GENDER    | PHONE NUMBER |           EMAIL          |");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");

        if (choice != 3) {

            if (tutors.getNumberOfEntries() != 0) {

                for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
                    Tutor tutor = tutors.getEntry(i);
                    if (tutor.getGender().equals(gender)) {

                        System.out.printf("| %02d |    %-4s     |%-30s|%-12s         |%6s%s%7s|%-14s|%-26s|\n", i, tutor.getFaculty(), tutor.getName(), tutor.getIc(), " ", tutor.getGender(), " ", tutor.getPhoneNumber(), tutor.getEmail());

                    }
                }

            } else {
                System.out.println("|                                                         0 record(s) here                                                       |");

            }
        }

        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                          END OF REPORT                                                         |");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");

    }

    public static void filterByFaculty() {

        helper.printFaculty();
        System.out.println("Please select gender to filter (1-4)> ");
        int choice = helper.digit(1, 4, sc);
        String faculty = "FOCS";
        int found = 0;

        if (choice == 4) {
            return;
        }

        if (choice == 1) {

            faculty = "FOCS";

        } else if (choice == 2) {

            faculty = "FAFB";

        } else if (choice == 3) {

            faculty = "FCCI";

        }

        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                        TUTOR LIST <" + faculty + ">                                                         |");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("| NO |   FACULTY   |             NAME             |        IC NO        |    GENDER    | PHONE NUMBER |           EMAIL          |");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");

        if (choice != 4) {
            if (tutors.getNumberOfEntries() != 0) {

                for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
                    Tutor tutor = tutors.getEntry(i);

                    if (tutor.getFaculty().equals(faculty)) {

                        System.out.printf("| %02d |    %-4s     |%-30s|%-12s         |%6s%s%7s|%-14s|%-26s|\n", i, tutor.getFaculty(), tutor.getName(), tutor.getIc(), " ", tutor.getGender(), " ", tutor.getPhoneNumber(), tutor.getEmail());
                        found++;
                    }
                }

            } else {
                System.out.println("|                                                         0 record(s) here                                                       |");
                found++;
            }

            if (found == 0) {
                System.out.println("|                                                         0 record(s) here                                                       |");
            }

        }

        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                                          END OF REPORT                                                         |");
        System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");

    }

    public static void filterBySubject() {

        String subject = "BACS2042";
        int found = 0;
        boolean continueSubjet = true;
        helper.printSubject();
        System.out.print("Select choice (8 to END the selection) > ");
        int subject_Selection = helper.digit(1, 8, sc);
        // Set the subject based on the selection
        switch (subject_Selection) {
            case 1:
                subject = "BACS2042";
                break;
            case 2:
                subject = "BACS2163";
                break;
            case 3:
                subject = "BAIT2203";
                break;
            case 4:
                subject = "BMMS2633";
                break;
            case 5:
                subject = "BACS2063";
                break;
            case 6:
                subject = "BACS2073";
                break;
            case 7:
                subject = "BACS2103";
                break;
            case 8:
                continueSubjet = false;
                break;
            default:
                break;
        }

        if (continueSubjet) {
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                     TUTOR LIST <" + subject + ">                                                      |");
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("| NO |   FACULTY   |             NAME             |        IC NO        |    GENDER    | PHONE NUMBER |           EMAIL          |");
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");

            for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
                Tutor tutor = new Tutor();
                tutor = tutors.getEntry(i);
                ListInterface<String> subjects = tutor.getSubjects();

                for (int j = 1; j <= subjects.getNumberOfEntries(); j++) {
                    if (subjects.getEntry(j).equals(subject)) {
                        System.out.printf("| %02d |    %-4s     |%-30s|%-12s         |%6s%s%7s|%-14s|%-26s|\n", i, tutor.getFaculty(), tutor.getName(), tutor.getIc(), " ", tutor.getGender(), " ", tutor.getPhoneNumber(), tutor.getEmail());
                        found++;
                        break;
                    }
                }

            }
            if (found == 0) {
                System.out.println("|                                                         0 record(s) here                                                       |");

            }

            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                          END OF REPORT                                                         |");
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------+");

        } else {
            return;
        }

    }

    public static void reportTutor() throws IOException {

        boolean continueReport = true;

        while (continueReport) {
            helper.clearConsole();
            System.out.println("*************************");
            System.out.println("*         Report        *");
            System.out.println("*************************");
            System.out.println("*  1. Tutor List        *"); //all tutor
            System.out.println("*  2. Tutor Faculty     *"); //all tutor with respective faculty overview
            System.out.println("*  3. Tutor Subject     *"); //which subject by who? selection
            System.out.println("*  4. Tutor Gender      *"); //Percentage
            System.out.println("*  5. Back to Previous  *");
            System.out.println("*************************");
            System.out.print("\n\nSelect your choice (1-5): ");
            int choice = helper.digit(1, 5, sc);

            switch (choice) {

                case 1:
                    tutorListReport();
                    System.out.println("Press enter to continue...");
                    enter.readLine();
                    break;
                case 2:
                    tutorFaculty();
                    System.out.println("Press enter to continue...");
                    enter.readLine();
                    break;
                case 3:
                    tutorSubject();
                    System.out.println("Press enter to continue...");
                    enter.readLine();
                    break;
                case 4:
                    tutorGender();
                    System.out.println("Press enter to continue...");
                    enter.readLine();
                    break;

                case 5:
                    System.out.println("Press enter to back to previous");
                    continueReport = false;
                    break;
            }
        }
    }

    public static void tutorListReport() {
        displayAll();
    }

    public static void tutorFaculty() throws IOException {
        int found = 0;
        helper.clearConsole();
        //FOCS
        System.out.println("                                                FOCS FACULTY REPORT                                                ");

        System.out.println("");
        helper.printFacultyReportHeader();

        for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
            Tutor tutor = tutors.getEntry(i);
            if (tutor.getFaculty().equals("FOCS")) {

                System.out.printf("| %02d |%-30s|%-12s         |%6s%s%7s|%-14s|%-26s|\n", i, tutor.getName(), tutor.getIc(), " ", tutor.getGender(), " ", tutor.getPhoneNumber(), tutor.getEmail());
                found++;
            }
        }
        if (found == 0) {
            System.out.println("|                                                  0 record(s) here                                                |");

        }

        helper.printFacultyReportFooter();

        //FAFB
        found = 0;
        System.out.println("");
        System.out.println("");
        System.out.println("                                                FAFB FACULTY REPORT                                                ");
        System.out.println("");
        helper.printFacultyReportHeader();

        for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
            Tutor tutor = tutors.getEntry(i);
            if (tutor.getFaculty().equals("FAFB")) {

                System.out.printf("| %02d |%-30s|%-12s         |%6s%s%7s|%-14s|%-26s|\n", i, tutor.getName(), tutor.getIc(), " ", tutor.getGender(), " ", tutor.getPhoneNumber(), tutor.getEmail());
                found++;
            }
        }

        if (found == 0) {
            System.out.println("|                                                  0 record(s) here                                                |");
        }

        helper.printFacultyReportFooter();

        //FCCI
        found = 0;
        System.out.println("");
        System.out.println("");
        System.out.println("                                                FFCI FACULTY REPORT                                                ");
        System.out.println("");
        helper.printFacultyReportHeader();

        for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
            Tutor tutor = tutors.getEntry(i);
            if (tutor.getFaculty().equals("FCCI")) {

                System.out.printf("| %02d |%-30s|%-12s         |%6s%s%7s|%-14s|%-26s|\n", i, tutor.getName(), tutor.getIc(), " ", tutor.getGender(), " ", tutor.getPhoneNumber(), tutor.getEmail());
                found++;
            }
        }

        if (found == 0) {
            System.out.println("|                                                  0 record(s) here                                                |");
        }

        helper.printFacultyReportFooter();
        enter.readLine();

    }

    //name output need change the layout
    public static void tutorSubject() {

        ListInterface<String> BACS2042 = new ArrayList<>();
        ListInterface<String> BACS2163 = new ArrayList<>();
        ListInterface<String> BAIT2203 = new ArrayList<>();
        ListInterface<String> BMMS2633 = new ArrayList<>();
        ListInterface<String> BACS2063 = new ArrayList<>();
        ListInterface<String> BACS2073 = new ArrayList<>();
        ListInterface<String> BACS2103 = new ArrayList<>();

        for (int i = 1; i <= tutors.getNumberOfEntries(); i++) {
            Tutor tutor = new Tutor();
            tutor = tutors.getEntry(i);
            ListInterface<String> subjects = tutor.getSubjects();
            if (subjects.isEmpty()) {
                System.out.println("No subjects selected");
            } else {
                for (int j = 1; j <= subjects.getNumberOfEntries(); j++) {
                    if (subjects.getEntry(i).equals("BACS2042")) {
                        BACS2042.add(tutor.getName());
                    } else if (subjects.getEntry(i).equals("BACS2163")) {
                        BACS2163.add(tutor.getName());
                    } else if (subjects.getEntry(i).equals("BAIT2203")) {
                        BAIT2203.add(tutor.getName());
                    } else if (subjects.getEntry(i).equals("BMMS2633")) {
                        BMMS2633.add(tutor.getName());
                    } else if (subjects.getEntry(i).equals("BACS2063")) {
                        BACS2063.add(tutor.getName());
                    } else if (subjects.getEntry(i).equals("BACS2073")) {
                        BACS2073.add(tutor.getName());
                    } else if (subjects.getEntry(i).equals("BACS2103")) {
                        BACS2103.add(tutor.getName());
                    }
                }
            }
        }

        System.out.println("+--------------------------------------------------+");
        System.out.println("|      Subject      |             NAME             |");

        if (!BACS2042.isEmpty()) {
            System.out.println("+--------------------------------------------------+");
            for (int j = 1; j <= BACS2042.getNumberOfEntries(); j++) {
                if (j == 1) {
                    System.out.println("|     BACS2042      |     " + BACS2042.getEntry(j) + "             |");
                } else {
                    System.out.println("|                   |     " + BACS2042.getEntry(j) + "             |");
                }
            }

        }
        if (!BACS2063.isEmpty()) {
            System.out.println("+--------------------------------------------------+");
            for (int j = 1; j <= BACS2063.getNumberOfEntries(); j++) {
                if (j == 1) {
                    System.out.println("|     BACS2063      |     " + BACS2063.getEntry(j) + "             |");
                } else {
                    System.out.println("|                   |     " + BACS2063.getEntry(j) + "             |");
                }
            }

        }
        if (!BACS2073.isEmpty()) {
            System.out.println("+--------------------------------------------------+");
            for (int j = 1; j <= BACS2073.getNumberOfEntries(); j++) {
                if (j == 1) {
                    System.out.println("|     BACS2073      |     " + BACS2073.getEntry(j) + "             |");
                } else {
                    System.out.println("|                   |     " + BACS2073.getEntry(j) + "             |");
                }
            }

        }
        if (!BACS2103.isEmpty()) {
            System.out.println("+--------------------------------------------------+");
            for (int j = 1; j <= BACS2103.getNumberOfEntries(); j++) {
                if (j == 1) {
                    System.out.println("|     BACS2103      |     " + BACS2103.getEntry(j) + "             |");
                } else {
                    System.out.println("|                   |     " + BACS2103.getEntry(j) + "             |");
                }
            }

        }
        if (!BACS2163.isEmpty()) {
            System.out.println("+--------------------------------------------------+");
            for (int j = 1; j <= BACS2163.getNumberOfEntries(); j++) {
                if (j == 1) {
                    System.out.println("|     BACS2042      |     " + BACS2163.getEntry(j) + "             |");
                } else {
                    System.out.println("|                   |     " + BACS2163.getEntry(j) + "             |");
                }
            }

        }
        if (!BAIT2203.isEmpty()) {
            System.out.println("+--------------------------------------------------+");
            for (int j = 1; j <= BAIT2203.getNumberOfEntries(); j++) {
                if (j == 1) {
                    System.out.println("|     BACS2042      |     " + BAIT2203.getEntry(j) + "             |");
                } else {
                    System.out.println("|                   |     " + BAIT2203.getEntry(j) + "             |");
                }
            }

        }
        if (!BMMS2633.isEmpty()) {
            System.out.println("+--------------------------------------------------+");
            for (int j = 1; j <= BMMS2633.getNumberOfEntries(); j++) {
                if (j == 1) {
                    System.out.println("|     BACS2042      |     " + BMMS2633.getEntry(j) + "             |");
                } else {
                    System.out.println("|                   |     " + BMMS2633.getEntry(j) + "             |");
                }
            }

        }
        System.out.println("+--------------------------------------------------+");

}

    private static int digit(int i, int i0, Scanner sc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
 */