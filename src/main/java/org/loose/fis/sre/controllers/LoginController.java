package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class LoginController {


    @FXML
    public Text loginMessage;
    @FXML
    public TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    public Button btnSingUp;
    @FXML
    public Button btnLogIn;
    public static User current_user;



    @FXML
    public void handleRegisterAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Stage window = (Stage) btnSingUp.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    @FXML
    public void handleLoginAction(ActionEvent actionEvent) throws IOException {
        String username=usernameTextField.getText();
        String password=passwordField.getText();

        String validatingAnswer = UserService.validateLogin(username, password);
        if (validatingAnswer.equals("Valid")) {
            current_user=UserService.returnCurrentUser(username,password);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePage.fxml"));
            Stage window = (Stage) btnLogIn.getScene().getWindow();
            window.setScene(new Scene(root, 600, 450));
        }
        else loginMessage.setText("Credențiale Invalide!\nInroduceți din nou datele!");
    }

}
