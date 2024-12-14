package tutioncetre.AdminInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AdminInterface {
    // Inner class to handle individual class timetables
    public static class Timetable {
        private Map<String, String[]> schedule;
        private static final String[] VALID_DAYS = {"MON", "TUE", "WED", "THU", "FRI"}; // Valid day abbreviations

        // Constructor: Initializes timetable with empty slots for each day (MON, TUE, etc.)
        public Timetable() {
            schedule = new HashMap<>();
            for (String day : VALID_DAYS) {
                schedule.put(day, new String[8]); // 8 periods per day
            }
        }

        // Get the timetable schedule
        public Map<String, String[]> getSchedule() {
            return schedule;
        }

        // Set subject for a specific day and period
        public void setSlot(String day, int period, String subject) {
            if (schedule.containsKey(day) && period >= 1 && period <= 8) {
                schedule.get(day)[period - 1] = subject;
            } else {
                System.out.println("Invalid day or period!");
            }
        }

        // Print the timetable in a table-like format
        public void printTimetable() {
            System.out.println("=====================================");
            System.out.println("| Period | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |");
            System.out.println("=====================================");

            schedule.forEach((day, periods) -> {
                System.out.print("| " + day + " | ");
                for (int i = 0; i < periods.length; i++) {
                    // If the period is not assigned, display "Free"
                    String subject = periods[i] == null ? "Free" : periods[i];
                    System.out.print(subject + " | ");
                }
                System.out.println();
            });
            System.out.println("=====================================");
        }

        // Method to validate the day input from the user
        public boolean isValidDay(String day) {
            for (String validDay : VALID_DAYS) {
                if (validDay.equalsIgnoreCase(day)) {
                    return true;
                }
            }
            return false;
        }
    }

    // Inner class to manage all the class timetables
    private Map<String, Timetable> classTimetables;

    public AdminInterface() {
        classTimetables = new HashMap<>();
    }

    // Add a new class with its timetable
    public void addClass(String className) {
        if (!classTimetables.containsKey(className)) {
            classTimetables.put(className, new Timetable());
            System.out.println("Class " + className + " added.");
        } else {
            System.out.println("Class " + className + " already exists.");
        }
    }

    // Get the timetable for a specific class
    public Timetable getTimetable(String className) {
        return classTimetables.get(className);
    }

    // Print all class timetables
    public void printAllTimetables() {
        if (classTimetables.isEmpty()) {
            System.out.println("No timetables exist.");
        } else {
            classTimetables.forEach((className, timetable) -> {
                System.out.println("Timetable for " + className + ":");
                timetable.printTimetable();
                System.out.println();
            });
        }
    }

    // Delete all timetables
    public void deleteAllTimetables() {
        if (classTimetables.isEmpty()) {
            System.out.println("No timetable to delete.");
        } else {
            classTimetables.clear();
            System.out.println("All timetables have been deleted.");
        }
    }

    // Function to customize timetables
    public void customizeTimetable() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Class");
            System.out.println("2. Customize Class Timetable");
            System.out.println("3. Print All Timetables");
            System.out.println("4. Delete All Timetables");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();  // Accepts integer input
                scanner.nextLine();  // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                scanner.nextLine();  // Clear the buffer
                continue;  // Skip the rest of the loop and ask again
            }

            switch (choice) {
                case 1:
                    // Add a new class
                    System.out.print("Enter Class Name (e.g., 3A, 3B, etc.): ");
                    String className = scanner.nextLine();
                    addClass(className);
                    break;

                case 2:
                    // Customize timetable for a specific class
                    System.out.print("Enter Class Name to Customize (e.g., 3A, 3B, etc.): ");
                    className = scanner.nextLine();
                    Timetable timetable = getTimetable(className);

                    if (timetable != null) {
                        String day;
                        // Loop to ensure valid day input
                        do {
                            System.out.print("Enter Day (MON, TUE, WED, THU, FRI): ");
                            day = scanner.nextLine().toUpperCase();  // Convert to uppercase for comparison
                            if (!timetable.isValidDay(day)) {
                                System.out.println("Invalid day! Please enter a valid day.");
                            }
                        } while (!timetable.isValidDay(day)); // Keep asking until valid day is entered

                        int period = -1;
                        // Loop to ensure valid period input
                        do {
                            System.out.print("Enter Period (1-8): ");
                            try {
                                period = scanner.nextInt();  // Accepts integer input
                                scanner.nextLine();  // Clear the buffer
                                if (period < 1 || period > 8) {
                                    System.out.println("Period must be between 1 and 8. Please try again.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid period! Please enter a number between 1 and 8.");
                                scanner.nextLine();  // Clear the buffer
                            }
                        } while (period < 1 || period > 8); // Keep asking until valid period is entered

                        // Ask for subject input
                        System.out.print("Enter a Subject: ");
                        String subject = scanner.nextLine().trim();
                        if (subject.isEmpty()) {
                            System.out.println("Subject cannot be empty. Please try again.");
                            continue;
                        }

                        timetable.setSlot(day, period, subject);
                    } else {
                        System.out.println("Class not found!");
                    }
                    break;

                case 3:
                    // Print all class timetables
                    printAllTimetables();
                    break;

                case 4:
                    // Delete all timetables
                    deleteAllTimetables();
                    break;

                case 5:
                    // Exit the program
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Create and run the Admin Interface
        AdminInterface adminInterface = new AdminInterface();
        adminInterface.customizeTimetable();
    }
}
