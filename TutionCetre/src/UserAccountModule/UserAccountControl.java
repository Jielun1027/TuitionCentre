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

    Scanner scanner = new Scanner(System.in);
    private static UserAccountControl control = new UserAccountControl();

    public UserAccount createUserAccount() {
        String username = inputData("username");
        String password = inputData("password");
        String firstName = inputData("firstName");
        String secondName = inputData("secondName");
        String email = inputData("email");
        String userType = inputData("userType");
        UserAccount newUser = new UserAccount(username, password, firstName, secondName, email, userType);

        System.out.println("1" + newUser.toString());

        return new UserAccount(username, password, firstName, secondName, email, userType);
    }

    public void addUserAccount() {
        UserAccount newUser = control.createUserAccount();
        System.out.println("2");
        System.out.println("2" + newUser.toString());
    }

    public String inputData(String dataField) {
        boolean valid = false;
        String data = "";

        while (valid == false) {
            System.out.print("Enter " + dataField + ": ");
            data = scanner.nextLine();
            System.out.println();
            valid = true;
//            valid = ProgrammeUtility.validateStringLength(level, 8);

            if (valid == false) {
                System.out.println("The " + dataField + " is invalid.\n");
            }
        }

        return data;
    }

    public static void main(String[] args) {

        control.createUserAccount();
    }
}
