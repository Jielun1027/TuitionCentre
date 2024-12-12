/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAccountModule;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap; // Added to manage failed attempts

/**
 *
 * @author Hafiz Chew Hoe Leong
 */
public class UserAccountControl {

    static Scanner scanner = new Scanner(System.in);
    private static UserAccountControl control = new UserAccountControl();
    private static final String FILE_NAME = "users.dat";

    // Keep track of failed login attempts per username
    private static final int MAX_FAILED_ATTEMPTS = 3; // Maximum allowed attempts
    private static HashMap<String, Integer> failedAttempts = new HashMap<>(); // Username and attempt count
    private static ArrayList<String> lockedAccounts = new ArrayList<>(); // Track locked accounts

    public static void saveUsers(List<UserAccount> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
            control.saveUsersInHumanReadableFile(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsersInHumanReadableFile(List<UserAccount> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users_readable.txt"))) {
            for (UserAccount user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<UserAccount> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<UserAccount>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("no file found");
            return new ArrayList<>(); // Return an empty list if file not found
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static boolean isUniqueDuplicate(String username, String email, List<UserAccount> users) {
        for (UserAccount user : users) {
            if (user.getUsername().equalsIgnoreCase(username) || user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public UserAccount createUserAccount() {
        System.out.println("\n\n-------------------");
        System.out.println("Register an Account");
        System.out.println("-------------------");

        String username = inputUsername();
        String password = inputPassword();
        String firstName = inputFirstName();
        String secondName = inputSecondName();
        String email = inputEmail();
        String userType = inputUserType();

        return new UserAccount(username, password, firstName, secondName, email, userType);
    }

    public String inputData(String dataField, int maxLength) {
        boolean valid = false;
        String data = "";

        while (valid == false) {
            System.out.print("\nEnter " + dataField + ": ");
            data = scanner.nextLine();
            valid = true;
            valid = UserAccountUtilities.validateStringLength(data, maxLength);

            if (valid == false) {
                System.out.println("The " + dataField + " is invalid.\n");
            }
        }

        return data;
    }

    public String inputUsername() {
        boolean valid = false;
        String data = "";
        do {
            data = inputData("Username", 100);
            valid = UserAccountUtilities.validateUsername(data);

            if (!valid) {
                UserAccountUtilities.invalidMessage("Username only allowed character and digit, no space or symbol.");
            }
        } while (valid == false);

        return data;
    }

    public String inputPassword() {
        boolean passwordValid = false;
        String password = "";

        do {
            password = inputData("Password", 20);
            passwordValid = UserAccountUtilities.validatePassword(password);

            if (!passwordValid) {
                UserAccountUtilities.invalidMessage(
                        "At least eight characters including symbol, number, uppercase letter and lowercase letter.");
            }
        } while (passwordValid == false);

        return password;
    }

    public String confirmPassword1() {

        boolean passwordValid = false;
        String password = "";

        do {
            System.out.print("\nEnter your new password (type 'x' to cancel):");
            password = scanner.nextLine();

            if (password.equalsIgnoreCase("x")) {
                return null; // Indicate cancellation
            }

            passwordValid = UserAccountUtilities.validatePassword(password);

            if (!passwordValid) {
                UserAccountUtilities.invalidMessage(
                        "At least eight characters including symbol, number, uppercase letter and lowercase letter.");
            }
        } while (passwordValid == false);

        return password;
    }

    public String confirmPassword2(String originalPassword) {
        boolean confirmed = false;
        String confirmPassword = "";

        do {
            System.out.print("\nPlease confirm your password (type 'x' to cancel):");
            confirmPassword = scanner.nextLine();

            if (confirmPassword.equalsIgnoreCase("x")) {
                return null; // Indicate cancellation
            }

            if (confirmPassword.equals(originalPassword)) {
                confirmed = true;
            } else {
                System.out.println("\nPasswords do not match. Please try again.");
            }
        } while (!confirmed);

        return confirmPassword;
    }

    public String inputFirstName() {
        boolean valid = false;
        String data = "";

        do {
            data = inputData("First Name", 20);
            valid = UserAccountUtilities.validateText(data);

            if (!valid) {
                UserAccountUtilities.invalidMessage("Only character and space is allowed.");
            }
        } while (valid == false);

        return data;
    }

    public String inputSecondName() {
        boolean valid = false;
        String data = "";

        do {
            data = inputData("Second Name", 20);
            valid = UserAccountUtilities.validateText(data);

            if (!valid) {
                UserAccountUtilities.invalidMessage("Only character and space is allowed.");
            }
        } while (valid == false);

        return data;
    }

    public String inputEmail() {
        boolean emailValid = false;
        String email = "";
        do {
            email = inputData("Email", 20);
            emailValid = UserAccountUtilities.validateEmail(email);

            if (!emailValid) {
                UserAccountUtilities.invalidMessage("Please using a correct email.");
            }
        } while (emailValid == false);

        return email;
    }

    public String inputUserType() {
        boolean valid = false;
        String data = "";
        do {
            data = inputData("User Type", 20);
            valid = UserAccountUtilities.validateUserType(data);

            if (!valid) {
                UserAccountUtilities.invalidMessage("User type only consists of student and teacher.");
            }
        } while (valid == false);

        return data;
    }

    public static void moduleUI() {
        System.out.println("\n\n-------------------");
        System.out.println("User Account Module");
        System.out.println("-------------------");
        System.out.println("1. Register Account \n");
        System.out.println("2. Login \n");
        System.out.print("Your option : ");

    }

    public boolean registerAccount(List<UserAccount> users) {
        try {
            UserAccount newUser = control.createUserAccount();
            if (control.isUniqueDuplicate(newUser.getUsername(), newUser.getEmail(), users)) {
                System.out.println("\nFailed to create the account: Username or Email already exists.");
            } else {
                users.add(newUser);
                control.saveUsers(users);
                System.out.println("\nCreated a user account successfully!");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public UserAccount enterUserAccount(List<UserAccount> users) {
        System.out.println("\n\n---------------");
        System.out.println("Login To System");
        System.out.println("---------------");

        String username = "";
        boolean valid = false;

        do {
            System.out.print("\nEnter Username (or 'x' to reset password): ");
            username = scanner.nextLine();

            if (username.equalsIgnoreCase("x")) {
                forgotPassword(users); // Navigate to Forgot Password function
                return null; // Exit the current flow
            }

            valid = !UserAccountUtilities.checkEmpty(username);
            if (!valid) {
                System.out.println("The field must not be blank.");
            }
        } while (!valid);

        String password = "";
        valid = false;

        do {
            System.out.print("\nEnter Password (or 'x' to reset password): ");
            password = scanner.nextLine();

            if (password.equalsIgnoreCase("x")) {
                forgotPassword(users); // Navigate to Forgot Password function
                return null; // Exit the current flow
            }

            valid = !UserAccountUtilities.checkEmpty(password);
            if (!valid) {
                System.out.println("The field must not be blank.");
            }
        } while (!valid);

        return new UserAccount(username, password, "", "", "", "");
    }

    public boolean checkLoginInfo(String username, String password, List<UserAccount> users) {
        for (UserAccount user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;

    }

    public boolean loginSystem(List<UserAccount> users) {
        try {
            UserAccount newUser = control.enterUserAccount(users);
            if (newUser == null) {
                return false; // Exit if the user resets the password
            }
            String username = newUser.getUsername();

            // Check if the username exists in the records before checking for locking or
            // login
            boolean userExists = false;
            for (UserAccount user : users) {
                if (user.getUsername().equals(username)) {
                    userExists = true;
                    break;
                }
            }

            if (!userExists) {
                System.out.println("\nThe username or password is incorrect.");
                return false; // Exit if the username doesn't exist in records
            }

            // Check if the account is locked
            for (UserAccount user : users) {
                if (user.getUsername().equals(username) && "locked".equals(user.getStatus())) {
                    System.out.println("\nAccount is locked. Please contact support from admin.");
                    return false;
                }
            }

            // Check login credentials
            if (control.checkLoginInfo(username, newUser.getPassword(), users)) {
                System.out.println("\nLogin Successfully.");

                // Reset failed attempts on successful login
                failedAttempts.remove(username);
                return true;
            } else {
                System.out.println("\nThe username or password is incorrect.");

                // Increment failed attempts
                failedAttempts.put(username, failedAttempts.getOrDefault(username, 0) + 1);

                // Lock account if max attempts reached
                if (failedAttempts.get(username) >= MAX_FAILED_ATTEMPTS) {
                    for (UserAccount user : users) {
                        if (user.getUsername().equals(username)) {
                            user.setStatus("locked"); // Update status to "locked"
                            break;
                        }
                    }
                    System.out.println(
                            "\nAccount locked due to too many failed login attempts. Please contact support from admin.");
                    control.saveUsers(users); // Save updated list to both files
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public UserAccount forgotPassword(List<UserAccount> users) {
        System.out.println("\n\n----------------");
        System.out.println("Forgot Password");
        System.out.println("----------------");

        // Prompt for username and email
        String username = "";
        boolean validUsername = false;
        do {
            username = inputData("Username", 20);
            validUsername = !UserAccountUtilities.checkEmpty(username);
            if (!validUsername) {
                System.out.println("The field must not be blank.");
            }
        } while (!validUsername);

        String email = "";
        boolean validEmail = false;
        do {
            email = inputEmail();
            validEmail = !UserAccountUtilities.checkEmpty(email);
            if (!validEmail) {
                System.out.println("The field must not be blank.");
            }
        } while (!validEmail);

        // Validate username and email combination
        for (UserAccount user : users) {
            if (user.getUsername().equals(username) && user.getEmail().equals(email)) {
                System.out.println("\nUsername and Email verified.");

                // Allow user to reset password
                String newPassword = confirmPassword1();

                if (newPassword == null) {
                    System.out.println("\nPassword reset canceled. Returning to main menu.");
                    return null;
                }

                String confirmedPassword = confirmPassword2(newPassword);

                if (confirmedPassword == null) {
                    System.out.println("\nPassword reset canceled. Returning to main menu.");
                    return null;
                }

                user.setPassword(confirmedPassword);
                System.out.println("\nPassword reset successfully.");
                control.saveUsers(users); // Save the updated list to the file
                return user;
            }
        }

        System.out.println("\nInvalid Username or Email. Please try again.");
        return null;
    }

    public static void homeUI() {
        System.out.println("\n\n-------------------------------");
        System.out.println("Tuition Centre System Main Menu");
        System.out.println("-------------------------------");
        System.out.println("1. (Function in processing) \n");
        System.out.print("Your option : ");

    }

    public static void main(String[] args) {
        boolean choiceValid = false;
        int choice = 0;
        int firstChoice = 1;
        int lastChoice = 2;

        List<UserAccount> users = control.loadUsers();

        // test case
        // boolean testValid = false;
        // while (testValid == false) {
        ////            System.out.println("enter:");
////            String data = scanner.nextLine();
////            UserAccountUtilities.validateUserType(data);
        // testValid=control.loginSystem(users);
        //
        // if (testValid==true)
        // control.homeUI();
        //
        // }
        boolean choiceResult = false;

        do {

            while (choiceValid == false) {
                control.moduleUI();

                String input = scanner.nextLine();
                choiceValid = UserAccountUtilities.validateDigit(input, firstChoice, lastChoice);

                if (choiceValid == true) {
                    choice = Integer.parseInt(input);
                } else {
                    System.out.println("The option only ranged from " + firstChoice + " to " + lastChoice + ".\n\n");
                }

            }

            switch (choice) {
                case 1:
                    choiceResult = control.registerAccount(users);
                    break;
                case 2:
                    choiceResult = control.loginSystem(users);
                    break;
                default:
                    break;
            }

            if (choiceResult == true) {
                switch (choice) {
                    case 1:
                        System.out.println("Navigating to Login Function.");
                        choiceResult = control.loginSystem(users);

                        if (choiceResult) {
                            control.homeUI();
                        }
                        break;
                    case 2:
                        control.homeUI();
                        break;
                    default:
                        break;
                }
            }
            choiceValid = false;
        } while (choiceResult == false);

    }
}
