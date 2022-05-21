package tests.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
        UserService.initDatabase();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each");
    }


    @Test
    void testValidateLogIn() {
        assertThat(UserService.validateLogin(USERNAME,PASSWORD).equals("Valid"));
    }

    @Test
    void testModifyClientAccountInfo() {
        String email="test@gmail.com", firstName="User", lastName="Name", phoneNumber="0712345678";
        assertThat(UserService.modifyClientAccountInfo(USERNAME, PASSWORD, email, firstName, lastName, phoneNumber)==true);
        assertThat(UserService.returnCurrentUser(USERNAME,PASSWORD).getEmail().equals(email));
        assertThat(UserService.returnCurrentUser(USERNAME,PASSWORD).getFirstName().equals(firstName));
        assertThat(UserService.returnCurrentUser(USERNAME,PASSWORD).getLastName().equals(lastName));
        assertThat(UserService.returnCurrentUser(USERNAME,PASSWORD).getPhoneNumber().equals(phoneNumber));
    }



}
