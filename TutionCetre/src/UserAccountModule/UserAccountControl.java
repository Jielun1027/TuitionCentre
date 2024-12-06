/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAccountModule;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Console;
import java.io.IOException;

/**
 *
 * @author Hafiz Chew Hoe Leong
 */
public class UserAccountControl {

    static Scanner scanner = new Scanner(System.in);
    private static UserAccountControl control = new UserAccountControl();
    private static final String FILE_NAME = "users.dat";

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

    public UserAccount enterUserAccount() {
        System.out.println("\n\n---------------");
        System.out.println("Login To System");
        System.out.println("---------------");

        String username = "";

        boolean valid = false;
        do {
            username = inputData("Username", 20);
            valid = !UserAccountUtilities.checkEmpty(username);

            if (!valid) {
                System.out.println("The field must not be blank.");
            }
        } while (valid == false);

        valid = false;

        // String password = "";

        String password = getPasswordInput("Enter your password: ");
        // System.out.println("Your password is: " + password); // For demonstration
        // purposes
        //
        // do {
        // password = inputData("Password", 20);
        // valid = !UserAccountUtilities.checkEmpty(password);
        //
        // if (!valid) {
        // System.out.println("The field must not be blank.");
        // }
        // } while (valid == false);

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
            // login function
            UserAccount newUser = control.enterUserAccount();
            if (control.checkLoginInfo(newUser.getUsername(), newUser.getPassword(), users)) {
                System.out.println("\nLogin Successfully.");
                return true;
            } else {
                System.out.println("\nThe username or password is incorrect.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    public static String getPasswordInput(String prompt) {
        Console console = System.console();

        // If a console is available, use it for password masking
        if (console != null) {
            return new String(console.readPassword(prompt));
        } else {
            // Fallback for IDEs where Console is not available
            System.out.print(prompt);
            return readPasswordFallback();
        }
    }

    private static String readPasswordFallback() {
        StringBuilder password = new StringBuilder();
        try {
            while (true) {
                // Read each character
                int input = System.in.read();
                if (input == '\n' || input == '\r') {
                    // Break on Enter key
                    break;
                } else {
                    password.append((char) input);
                    System.out.print("*"); // Print * for each character
                    System.out.println("yes");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(); // New line after password input
        return password.toString();
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

        boolean choiceResult = false;

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
                    control.loginSystem(users);
                    break;
                case 2:
                    control.homeUI();
                    break;
                default:
                    break;
            }

        } else {
            switch (choice) {
                case 1:
                    control.moduleUI();
                    break;
                case 2:
                    control.loginSystem(users);
                    break;
                default:
                    break;
            }
        }
    }
}
