/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAccountModule;

/**
 *
 * @author Hafiz Chew Hoe Leong
 */
public class UserAccountUtilities {

    public static boolean validateStringLength(String data, int maxLength) {
        if (data == null) {
            return false;
        }

        return data.length() <= maxLength;
    }

//        public static boolean validateUsername(String data, int maxLength) {
//        if (data == null) {
//            return false;
//        }
//
//        return data.length() <= maxLength;
//    }
    
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
}
