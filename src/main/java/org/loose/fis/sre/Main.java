package org.loose.fis.sre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.TicketService;
import org.loose.fis.sre.services.UserService;
import org.loose.fis.sre.services.EventService;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDirectory();
        UserService.initDatabase();
        EventService.initDatabase();
        TicketService.initTicketsDatabase();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Aplicatie Administrare Stadion");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
