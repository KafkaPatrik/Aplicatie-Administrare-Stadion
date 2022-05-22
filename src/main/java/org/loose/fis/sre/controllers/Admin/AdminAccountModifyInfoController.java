package org.loose.fis.sre.controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class AdminAccountModifyInfoController {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnSaveClientData;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField stadiumAdressField;
    @FXML
    private TextField capacityField;
    @FXML
    private TextField stadiumNameField;
    @FXML
    private TextField ownerName;
    @FXML
    private Text modifiedMessage;

    public void handleLoggingOut(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) btnLogOut.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleHomePageAction (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAdmin.fxml"));
        Stage window = (Stage) btnHomePage.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }


    public void handleSaveClientData(ActionEvent actionEvent) {
        String usernameString = usernameField.getText();
        String passwordString = passwordField.getText();
        String phoneNumberString = phoneNumberField.getText();
        String emailString = emailField.getText();
        String firstNameString = firstNameField.getText();
        String lastNameString = lastNameField.getText();
        String stadiumAdressString=stadiumAdressField.getText();
        String capacityFieldString=capacityField.getText();
        String stadiumNameString=stadiumNameField.getText();
        String ownerNameString=ownerName.getText();

        if (UserService.modifyClientAccountInfo(usernameString, passwordString, emailString, firstNameString,lastNameString, phoneNumberString,stadiumAdressString,capacityFieldString,stadiumNameString,ownerNameString)) {
            modifiedMessage.setText("Date modificate cu succes!");
        }
        else modifiedMessage.setText("Încercați din nou! Apărut o eroare!");
    }

}
