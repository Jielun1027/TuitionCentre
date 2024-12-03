/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAccountModule;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public static boolean isDuplicate(String username, String email, List<UserAccount> users) {
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
                UserAccountUtilities.invalidMessage("At least eight characters including symbol, number, uppercase letter and lowercase letter.");
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
        System.out.println("-------------------");
        System.out.println("User Account Module");
        System.out.println("-------------------");
        System.out.println("1. Register Account \n");
        System.out.println("2. Login \n");
        System.out.print("Your option : ");

    }

    public void registerAccount(List<UserAccount> users) {
        try {
            UserAccount newUser = control.createUserAccount();
            if (control.isDuplicate(newUser.getUsername(), newUser.getEmail(), users)) {
                System.out.println("\nFailed to create the account: Username or Email already exists.");
            } else {
                users.add(newUser);
                control.saveUsers(users);
                System.out.println("\nCreated a user account successfully!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void login(List<UserAccount> users) {
        try {
            //login function

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        boolean choiceValid = false;
        int choice = 0;
        int firstChoice = 1;
        int lastChoice = 2;

        List<UserAccount> users = control.loadUsers();

//        while (choice == 0) {
//            System.out.println("enter:");
//            String data = scanner.nextLine();
//            UserAccountUtilities.validateUserType(data);
//
//        }
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
                control.registerAccount(users);
                break;
            case 2:
//                while (choice == 2) {
                    control.login(users);
//                }
                break;
            default:
                break;
        }

    }
}
