package tests;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.TicketService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testfx.assertions.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
class LoginTest {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @BeforeEach
    void setUp() throws Exception {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
        UserService.initDatabase();
        EventService.initDatabase();
        TicketService.initTicketsDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Aplicatie Administrare Stadion");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
    }


    @Test
    void testLogIn(FxRobot robot) throws IOException {
        robot.clickOn("#usernameTextField");
        robot.write(USERNAME);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD);

        robot.clickOn("#btnLogIn");
        assertThat(robot.lookup("#selectionMessage").queryText()).hasText("Selectați evenimentul și apăsați afișează");
    }



}
