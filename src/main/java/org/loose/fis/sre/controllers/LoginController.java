package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    public Label loginMessageLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text registrationMessage;
    @FXML
    public HBox box;
    @FXML
    public Button btnSingUp;
    @FXML
    public Button btnLogIn;



    @FXML
    public void handleRegisterAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Stage window = (Stage) btnSingUp.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    @FXML
    public void handleLoginAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePage.fxml"));
        Stage window = (Stage) btnSingUp.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }
}
