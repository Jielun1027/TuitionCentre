/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserAccountModule;

/**
 *
 * @author Hafiz Chew Hoe Leong
 */

import java.io.Serializable;

public class UserAccount implements Serializable {

    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String userType;
    private String status;

    public UserAccount(String username, String password, String firstName, String secondName, String email, String userType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.userType = userType;
        this.status="active";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserAccount{" + "username=" + username + ", password=" + password + ", firstName=" + firstName + ", secondName=" + secondName + ", email=" + email + ", userType=" + userType + ", status=" + status + '}';
    }


}
