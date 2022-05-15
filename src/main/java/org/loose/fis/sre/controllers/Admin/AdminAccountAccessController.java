package org.loose.fis.sre.controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;
import java.io.IOException;

public class AdminAccountAccessController {


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
    @FXML
    private Label roleText;
    @FXML
    private Text warningNewAccountMessage;



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
                roleText.setText("Rol: " + current_user.getRole());
                if (current_user.getEmail().length()==0)
                    warningNewAccountMessage.setText("Trebuie completate datele contului\ndeoarece dețineți un cont nou creat!");
            }
        }
    }


    public void handleModifyDataAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adminAccountInfoAccess.fxml"));
        Stage window = (Stage) btnModifyData.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }
}
