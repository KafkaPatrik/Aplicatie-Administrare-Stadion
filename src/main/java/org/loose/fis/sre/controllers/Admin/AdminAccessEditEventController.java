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
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.model.Eveniment;

public class AdminAccessEditEventController {


    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnEditEvent;
    @FXML
    private Label id_text;
    @FXML
    private Label participants_text;
    @FXML
    private Label date_text;
    @FXML
    private Label parkingPrice_text;
    @FXML
    private Label parkingPlaceNr_text;
    @FXML
    private Label ticketPrice_text;
    @FXML
    private Label title_text;
    @FXML
    private Label location_text;
    @FXML
    private Label description_text;
    private static Eveniment event;

    public void initialize(){
        id_text.setText(Integer.toString(event.get_event_Id()));
        participants_text.setText(Integer.toString(event.get_event_max_participants()));
        date_text.setText(event.get_event_Date());
        parkingPrice_text.setText(Integer.toString(event.getParkingPrice()));
        parkingPlaceNr_text.setText(Integer.toString(event.getMaxParkingSpots()));
        ticketPrice_text.setText(Integer.toString(event.getTicketPrice()));
        title_text.setText(event.get_event_Title());
        location_text.setText(event.get_event_Location());
        description_text.setText(event.get_event_Description());
    }
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

    public void handleEditEventAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adminEditEvent.fxml"));
        Stage window = (Stage) btnEditEvent.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }
    public static Eveniment getEvent() {
        return event;
    }

    public static void setEvent(Eveniment event) {
        AdminAccessEditEventController.event = event;
    }
}