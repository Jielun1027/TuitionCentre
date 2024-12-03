/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAccountModule;

import java.util.Scanner;

/**
 *
 * @author Hafiz Chew Hoe Leong
 */
public class UserAccountControl {

    static Scanner scanner = new Scanner(System.in);
    private static UserAccountControl control = new UserAccountControl();

    public UserAccount createUserAccount() {
        System.out.println("\n\n-------------------");
        System.out.println("Register an Account");
        System.out.println("-------------------");
        String username = inputData("username", 20);
        String password = inputData("password", 20);
        String firstName = inputData("firstName", 20);
        String secondName = inputData("secondName", 20);
        String email = inputData("email", 20);
        String userType = inputData("userType", 10);

        return new UserAccount(username, password, firstName, secondName, email, userType);
    }

    public void addUserAccount() {
        try {
            UserAccount newUser = control.createUserAccount();
            System.out.println("\nCreated a user account successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String inputData(String dataField, int maxLength) {
        boolean valid = false;
        String data = "";

        while (valid == false) {
            System.out.print("Enter " + dataField + ": ");
            data = scanner.nextLine();
            valid = true;
            valid = UserAccountUtilities.validateStringLength(data, maxLength);

            if (valid == false) {
                System.out.println("The " + dataField + " is invalid.\n");
            }
        }

        return data;
    }

    public static void moduleUI() {
        System.out.println("-------------------");
        System.out.println("User Account Module");
        System.out.println("-------------------");
        System.out.println("1. Register Account \n");
        System.out.print("Your option : ");

    }

    public static void main(String[] args) {
        boolean choiceValid = false;
        int choice = 0;

        while (choiceValid == false) {
            control.moduleUI();

            String input = scanner.nextLine();
            choiceValid = UserAccountUtilities.validateDigit(input, 1, 1);

            if (choiceValid == true) {
                choice = Integer.parseInt(input);
            }

        }

        switch (choice) {
            case 1:
                control.addUserAccount();
                break;
            default:
                break;
        }

    }
}
