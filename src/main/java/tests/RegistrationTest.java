package tests;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.TicketService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class RegistrationTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";


    @Start
    void start(Stage primaryStage) throws Exception {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
        FileUtils.cleanDirectory(applicationHomePath.toFile());
        UserService.initDatabase();
        EventService.initDatabase();
        TicketService.initTicketsDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        primaryStage.setTitle("Registration Example");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
    }

    @Test
    void testRegistration(FxRobot robot) {
        robot.clickOn("#usernameField");
        robot.write(USERNAME);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD);
        robot.clickOn("#role").clickOn("Client");
        robot.clickOn("#btnSingUp");

        assertThat(robot.lookup("#registrationMessage").queryText()).hasText("Contul a fost creat cu succes!");
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#btnSingUp");
        assertThat(robot.lookup("#registrationMessage").queryText()).hasText(
                String.format("Există deja un cont cu username-ul: %s!", USERNAME)
        );
    }
}