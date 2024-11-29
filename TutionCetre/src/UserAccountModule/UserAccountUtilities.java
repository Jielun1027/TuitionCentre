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
    
        public static boolean validateUsername(String data, int maxLength) {
        if (data == null) {
            return false;
        }

        return data.length() <= maxLength;
    }
}
