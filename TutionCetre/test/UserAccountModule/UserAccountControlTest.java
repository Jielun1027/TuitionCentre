/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package UserAccountModule;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hafiz Chew Hoe Leong
 */
public class UserAccountControlTest {

    private static UserAccountControl control = new UserAccountControl();

    public UserAccountControlTest() {
    }

//    @Test
//    public void testRegisterAccountWithCorrectCriteria() {
//        // Arrange
//        List<UserAccount> users = new ArrayList<>();
//
//        UserAccount newUser = new UserAccount("username1", "Password@123", "John",
//                "Doe", "john.doe@example.com", "student");
//        boolean result = control.registerAccount(users, newUser);
//
//        assertTrue("User account should be registered successfully.", result);
//        assertEquals("Users list should have one user.", 1, users.size());
//        assertEquals("The registered username should match.", "username1", users.get(0).getUsername());
//    }
    
//    @Test
//    public void testRegisterAccountWithDuplicateUsernamen() {
//        // Arrange
//        List<UserAccount> users = new ArrayList<>();
//
//        UserAccount existingUser = new UserAccount("username1", "Password@123", 
//                "Alice", "Smith", "alice.smith@example.com", "teacher");
//        users.add(existingUser);
//
//        // Mock duplicate user with same username
//        UserAccount duplicateUser = new UserAccount("username1", "Password@456", 
//                "John", "Doe", "john.doe@example.com", "student");
//        boolean result = control.registerAccount(users, duplicateUser);
//
//        // Assert
//        assertFalse("User account registration should fail for duplicate username.", result);
//        assertEquals("Users list should still have one user.", 1, users.size());
//    }
    
//    @Test
//    public void testRegisterAccountWithDuplicateEmail() {
//        // Arrange
//        List<UserAccount> users = new ArrayList<>();
//
//        UserAccount existingUser = new UserAccount("username1", "Password@123", 
//                "Alice", "Smith", "john.doe@example.com", "teacher");
//        users.add(existingUser);
//
//        // Mock duplicate user with same email
//        UserAccount duplicateUser = new UserAccount("username2", "Password@456", 
//                "John", "Doe", "john.doe@example.com", "student");
//        boolean result = control.registerAccount(users,duplicateUser);
//
//        // Assert
//        assertFalse("User account registration should fail for duplicate email.", result);
//        assertEquals("Users list should still have one user.", 1, users.size());
//    }
    
    @Test
    public void testRegisterAccountWithInvalidPasswordFormat() {
        // Arrange
        List<UserAccount> users = new ArrayList<>();

        boolean result1 = UserAccountUtilities.validatePassword("Short.");
        boolean result2 = UserAccountUtilities.validatePassword("no_capital_letter");
        boolean result3 = UserAccountUtilities.validatePassword("NO_SMALL_LETTER");
        boolean result4 = UserAccountUtilities.validatePassword("noSymbolCharacter");

        // Assert
        assertFalse("User account registration should fail for invalid password format.", result1);
        assertFalse("User account registration should fail for invalid password format.", result2);
        assertFalse("User account registration should fail for invalid password format.", result3);
        assertFalse("User account registration should fail for invalid password format.", result4);

        assertEquals("Users list should not have any users.", 0, users.size());
    }
}
