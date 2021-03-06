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
    private String stadiumAdress;
    private String capacity;
    private String stadiumName;
    private String ownerName;

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

    public void setStadiumAdress(String stadiumAdress) {
        this.stadiumAdress = stadiumAdress;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName="";
        this.lastName="";
        this.email="";
        this.phoneNumber="";
        this.stadiumAdress="";
        this.capacity="";
        this.stadiumName="";
        this.ownerName="";
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

    public String getStadiumAdress() {
        return stadiumAdress;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public String getOwnerName() {
        return ownerName;
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
