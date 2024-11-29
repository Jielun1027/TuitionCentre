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
        String username = inputData("username",20);
        String password = inputData("password",20);
        String firstName = inputData("firstName",20);
        String secondName = inputData("secondName",20);
        String email = inputData("email",20);
        String userType = inputData("userType",10);

        return new UserAccount(username, password, firstName, secondName, email, userType);
    }

    public void addUserAccount() {
        UserAccount newUser = control.createUserAccount();
        System.out.println(newUser.toString());
    }

    public String inputData(String dataField,int maxLength) {
        boolean valid = false;
        String data = "";

        while (valid == false) {
            System.out.print("Enter " + dataField + ": ");
            data = scanner.nextLine();
            System.out.println();
            valid = true;
            valid = UserAccountUtilities.validateStringLength(data, maxLength);

            if (valid == false) {
                System.out.println("The " + dataField + " is invalid.\n");
            }
        }

        return data;
    }

    public static void main(String[] args) {

        control.addUserAccount();
    }
}
