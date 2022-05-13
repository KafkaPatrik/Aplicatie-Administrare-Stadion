package org.loose.fis.sre.exceptions;

public class UsernameAlreadyExistsException extends Exception {

    private String username;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("Există deja un cont cu username-ul: %s!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
