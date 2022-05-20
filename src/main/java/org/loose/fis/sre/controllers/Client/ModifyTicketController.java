package org.loose.fis.sre.controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyTicketController {


    @javafx.fxml.FXML
    private Label dateText;
    @javafx.fxml.FXML
    private Button btnLogOut;
    @javafx.fxml.FXML
    private Label locationText;
    @javafx.fxml.FXML
    private Label gateText;
    @javafx.fxml.FXML
    private Label parkingText;
    @javafx.fxml.FXML
    private Label descriptionText;
    @javafx.fxml.FXML
    private Label nameText;
    @javafx.fxml.FXML
    private Label phoneNumberText;
    @javafx.fxml.FXML
    private Label gateText1;
    @javafx.fxml.FXML
    private ChoiceBox categorySelector;
    @javafx.fxml.FXML
    private Label purchaseDateText;
    @javafx.fxml.FXML
    private Button btnHomePage;
    @javafx.fxml.FXML
    private Label gatesOpenText;
    @javafx.fxml.FXML
    private Label team2Text;
    @javafx.fxml.FXML
    private Label priceText;
    @javafx.fxml.FXML
    private Label idText;
    @javafx.fxml.FXML
    private Label seatText;
    @javafx.fxml.FXML
    private Button btnLogOut1;

    public void handleLoggingOut(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) btnLogOut.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleHomePageAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageClient.fxml"));
        Stage window = (Stage) btnHomePage.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

}
