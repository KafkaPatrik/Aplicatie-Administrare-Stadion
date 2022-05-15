package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.loose.fis.sre.model.User;

import java.io.IOException;


public class HomePageController {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnAccountInfo;


    public void handleLoggingOut(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) btnLogOut.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleHomePageAction (ActionEvent actionEvent) throws IOException {
        Parent root;

        if(LoginController.current_user.getRole().equals("Administrator"))
            root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAdmin.fxml"));
        else
            root = FXMLLoader.load(getClass().getClassLoader().getResource("homePage.fxml"));
        Stage window = (Stage) btnHomePage.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleAccountInfoAction(ActionEvent actionEvent) throws IOException {
        Parent root;
        if(LoginController.current_user.getRole().equals("Administrator"))
            root = FXMLLoader.load(getClass().getClassLoader().getResource("adminAccountInfoAccess.fxml"));
            else
        root = FXMLLoader.load(getClass().getClassLoader().getResource("clientAccountInfoAccess.fxml"));
        Stage window = (Stage) btnAccountInfo.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

}


