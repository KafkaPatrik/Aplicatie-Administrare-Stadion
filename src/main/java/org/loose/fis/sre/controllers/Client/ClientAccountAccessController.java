package org.loose.fis.sre.controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;
import java.io.IOException;

public class ClientAccountAccessController {


    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnModifyData;
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private Label emailText;
    @FXML
    private Label lastNameText;
    @FXML
    private Label firstNameText;
    @FXML
    private Label phoneNumberText;



    public void handleLoggingOut(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) btnLogOut.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleHomePageAction (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePage.fxml"));
        Stage window = (Stage) btnHomePage.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }


    public void handleViewDataAction(ActionEvent actionEvent) throws IOException {
        String username=usernameTextField.getText();
        String password=passwordField.getText();

        if (username!=null && password!=null)
        {   User current_user = UserService.returnCurrentUser(username, password);
            if (current_user != null) {
                emailText.setText(current_user.getEmail());
                firstNameText.setText(current_user.getFirstName());
                lastNameText.setText(current_user.getLastName());
                phoneNumberText.setText(current_user.getPhoneNumber());
            }
        }
    }


    public void handleModifyDataAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientAccountModifyInfo.fxml"));
        Stage window = (Stage) btnModifyData.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }
}
