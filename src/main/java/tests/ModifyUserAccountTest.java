package tests;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.controllers.Client.BuyConcertTicketController;
import org.loose.fis.sre.controllers.LoginController;
import org.loose.fis.sre.model.Eveniment;
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.TicketService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testfx.assertions.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(ApplicationExtension.class)
public class ModifyUserAccountTest {

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
        UserService.addUser(USERNAME, PASSWORD, "Client");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientAccountModifyInfo.fxml"));
        primaryStage.setTitle("Aplicatie Administrare Stadion");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
    }


    @Test
    void testModifyUserDataFeature(FxRobot robot) throws IOException {
        robot.clickOn("#usernameField");
        robot.write(USERNAME);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD);
        robot.clickOn("#emailField");
        robot.write("email@gmail.com");
        robot.clickOn("#phoneNumberField");
        robot.write("0712345678");
        robot.clickOn("#lastNameField");
        robot.write("Name");
        robot.clickOn("#firstNameField");
        robot.write("User");
        robot.clickOn("#btnSaveClientData");
        assertThat(robot.lookup("#modifiedMessage").queryText()).hasText("Date modificate cu succes!");
    }


}
