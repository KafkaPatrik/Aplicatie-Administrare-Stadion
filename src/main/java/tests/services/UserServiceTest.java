package tests.services;

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

    @BeforeEach
    void setUp() throws Exception {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
        UserService.initDatabase();
    }

    @Test
    void testLogIn() {
        assertThat(UserService.validateLogin(USERNAME,PASSWORD).equals("Valid"));
    }
}
