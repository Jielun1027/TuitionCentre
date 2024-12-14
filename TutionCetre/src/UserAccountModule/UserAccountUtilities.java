/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAccountModule;

import java.util.regex.Pattern;

/**
 *
 * @author Hafiz Chew Hoe Leong
 */
public class UserAccountUtilities {

    public static boolean validateDigit(String input, int minRange, int maxRange) {

        try {
            if (input == null) {
                return false;
            }

            // Parse the input string to an integer
            int value = Integer.parseInt(input);

            // Check if the parsed integer is within the specified range
            if (value >= minRange && value <= maxRange) {
                return true; // Valid integer within the range
            } else {
                return false; // Integer is outside the range
            }
        } catch (NumberFormatException e) {
            // Parsing failed, input is not a valid integer
            return false;
        }
    }

    public static boolean validateStringLength(String data, int maxLength) {
        if (data == null) {
            return false;
        }

        return data.length() <= maxLength;
    }

    public static boolean validateUsername(String data) {
        // Validate if a string is a email 
        boolean isMatch = Pattern.compile("^[a-zA-Z0-9]+$")
                .matcher(data)
                .find();
        return isMatch;
    }

    public static boolean validatePassword(String data) {
        // Validate strong password
        boolean isMatch = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[.#?!@$%^&*-])(?!.*\\s).{8,}$")
                .matcher(data)
                .find();

        return isMatch;
    }

    public static boolean validateText(String data) {
        boolean isMatch = Pattern.compile("^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$")
                .matcher(data)
                .find();
        return isMatch;
    }

    public static boolean validateEmail(String data) {

        // Validate if a string is a email 
        boolean isMatch = Pattern.compile("^\\S+@\\S+\\.\\S+$")
                .matcher(data)
                .find();
        return isMatch;
    }

    public static boolean validateUserType(String data) {

        // Validate if a string is a email 
        boolean isMatch = Pattern.compile("^(?i)(student|teacher)$")
                .matcher(data)
                .find();
        return isMatch;
    }

    public static boolean checkEmpty(String data) {
        return data.trim().isEmpty();
    }

    public static void invalidMessage(String strText) {
        System.out.println("The input format is incorrect. " + strText);
    }
}
