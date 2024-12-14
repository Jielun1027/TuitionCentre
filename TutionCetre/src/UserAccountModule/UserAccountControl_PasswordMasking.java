package UserAccountModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 *
 * @author Hafiz Chew Hoe Leong
 */
public class UserAccountControl_PasswordMasking {

    private static UserAccountControl_PasswordMasking control = new UserAccountControl_PasswordMasking();
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
            System.out.println("No file found");
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

    // Changed from scanner.nextLine() to console.readLine()
    public String inputData(String dataField, int maxLength) {
        boolean valid = false;
        String data = "";
        Console console = System.console();

        while (valid == false) {
            System.out.print("\nEnter " + dataField + ": ");
            data = console.readLine(); // Using console for input
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
        String password = "";

        do {
            password = getPasswordInput("\nEnter Password: ");
            valid = !UserAccountUtilities.checkEmpty(password);

            if (!valid) {
                System.out.println("The field must not be blank.");
            }
        } while (valid == false);

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
            UserAccount newUser = control.enterUserAccount();
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
                failedAttempts.remove(username);
                return true;
            } else {
                System.out.println("\nThe username or password is incorrect.");
                failedAttempts.put(username, failedAttempts.getOrDefault(username, 0) + 1);

                if (failedAttempts.get(username) >= MAX_FAILED_ATTEMPTS) {
                    for (UserAccount user : users) {
                        if (user.getUsername().equals(username)) {
                            user.setStatus("locked");
                            break;
                        }
                    }
                    System.out.println(
                            "\nAccount locked due to too many failed login attempts. Please contact support from admin.");
                    control.saveUsers(users);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static String getPasswordInput(String prompt) {
        Console console = System.console();

        if (console != null) {
            return new String(console.readPassword(prompt));
        } else {
            System.out.print(prompt);
            return readPasswordFallback();
        }
    }

    private static String readPasswordFallback() {
        StringBuilder password = new StringBuilder();
        try {
            while (true) {
                int input = System.in.read();
                if (input == '\n' || input == '\r') {
                    break;
                } else if (input == '\b') {
                    if (password.length() > 0) {
                        password.deleteCharAt(password.length() - 1);
                        System.out.print("\b \b");
                    }
                } else {
                    password.append((char) input);
                    System.out.print("*");
                }
            }
            System.out.println(); // Force newline after password
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        boolean choiceResult = false;

        do {
            while (choiceValid == false) {
                control.moduleUI();

                Console console = System.console();
                String input = console.readLine();
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
