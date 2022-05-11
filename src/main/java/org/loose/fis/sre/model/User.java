package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class User {
    @Id
    private String username;
    private String password;
    private String role;
    private String firstName, lastName;
    private String email;
    private String phoneNumber;
    //Data of User in use of application
    private static String Current_role, Current_firstName, Current_lastName, Current_email, Current_phoneNumber;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static String getCurrent_role() {
        return Current_role;
    }

    public static void setCurrent_role(String current_role) {
        User.Current_role = current_role;
    }

    public static String getCurrent_firstName() {
        return Current_firstName;
    }

    public static void setCurrent_firstName(String current_firstName) {
        User.Current_firstName = current_firstName;
    }

    public static String getCurrent_lastName() {
        return Current_lastName;
    }

    public static void setCurrent_lastName(String current_lastName) {
        User.Current_lastName = current_lastName;
    }

    public static String getCurrent_email() {
        return Current_email;
    }

    public static void setCurrent_email(String current_email) {
        User.Current_email = current_email;
    }

    public static String getCurrent_phoneNumber() {
        return Current_phoneNumber;
    }

    public static void setCurrent_phoneNumber(String current_phoneNumber) {
        User.Current_phoneNumber = current_phoneNumber;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName="aaa";
        this.lastName="bbb";
        this.email="ccc";
        this.phoneNumber="ddd";
    }

    public User() {
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

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

}
