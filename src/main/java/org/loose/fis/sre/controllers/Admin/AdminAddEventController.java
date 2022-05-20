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
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.exceptions.EventAlreadyExistsException;

import java.io.IOException;

public class AdminAddEventController {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnSaveClientData;
    @FXML
    private TextField idField;
    @FXML
    private TextField participantsField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField descriptionField;
    @FXML
    private Text modifiedMessage;
    @FXML
    private TextField parkingPriceField;
    @FXML
    private TextField parkingSpotsField;
    @FXML
    private TextField ticketPriceField;

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


    public void handleAddEvent(ActionEvent actionEvent) {
        int id_data =Integer.parseInt(idField.getText());
        int partic_data =Integer.parseInt(participantsField.getText());
        String title_data = titleField.getText();
        String location_data = locationField.getText();
        String date_data = dateField.getText();
        String description_data = descriptionField.getText();
        int parking_price = Integer.parseInt(parkingPriceField.getText());
        int max_Parking_Spots = Integer.parseInt(parkingSpotsField.getText());
        int ticketPrice=Integer.parseInt(ticketPriceField.getText());

        try {
            EventService.addEvent(id_data,partic_data,title_data,location_data,date_data,description_data, max_Parking_Spots, parking_price,ticketPrice);
            modifiedMessage.setText("Eveniment adăugat cu succes!");
        } catch (EventAlreadyExistsException e) {
            modifiedMessage.setText(e.getMessage());
        }

    }


}
