package tests.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ADMIN = "admin";

    @BeforeAll
    static void beforeAll() throws IOException, UsernameAlreadyExistsException {
        System.out.println("Before Class");
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
        FileUtils.cleanDirectory(applicationHomePath.toFile());
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
    @Order(5)
    void testValidateLogIn() {
        assertThat(UserService.validateLogin(ADMIN,PASSWORD).equals("Valid"));
    }

    @Test
    @Order(4)
    void testModifyClientAccountInfo() {
        String email="test@gmail.com", firstName="User", lastName="Name", phoneNumber="0712345678";
        assertThat(UserService.modifyClientAccountInfo(ADMIN, PASSWORD, email, firstName, lastName, phoneNumber,"","","","")==true);
        assertThat(UserService.returnCurrentUser(ADMIN,PASSWORD).getEmail().equals(email));
        assertThat(UserService.returnCurrentUser(ADMIN,PASSWORD).getFirstName().equals(firstName));
        assertThat(UserService.returnCurrentUser(ADMIN,PASSWORD).getLastName().equals(lastName));
        assertThat(UserService.returnCurrentUser(ADMIN,PASSWORD).getPhoneNumber().equals(phoneNumber));
    }


    @Test
    @Order(1)
    @DisplayName("Database is initialized, and there are no users")
    void testDatabaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("User is successfully persisted to Database")
    void testUserIsAddedToDatabase() throws UsernameAlreadyExistsException {
        UserService.addUser(ADMIN, PASSWORD, "Administrator");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(ADMIN);
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword(ADMIN, PASSWORD));
        assertThat(user.getRole()).isEqualTo("Administrator");
    }

    @Test
    @Order(3)
    @DisplayName("User can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(UsernameAlreadyExistsException.class, () -> {
            UserService.addUser(ADMIN, ADMIN, "Administrator");
        });
    }


}
