package com.revature.model;

import java.util.Objects;

public class NewUser {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String passwordAgain;

    public NewUser() {
    }

    public NewUser(String firstName, String lastName, String username, String password, String passwordAgain) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.passwordAgain = passwordAgain;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordAgain='" + passwordAgain + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewUser newUser = (NewUser) o;
        return firstName.equals(newUser.firstName) && lastName.equals(newUser.lastName) && username.equals(newUser.username) && password.equals(newUser.password) && passwordAgain.equals(newUser.passwordAgain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password, passwordAgain);
    }
}
