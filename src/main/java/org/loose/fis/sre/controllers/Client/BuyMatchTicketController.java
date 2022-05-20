package org.loose.fis.sre.controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.TicketAlreadyExistsException;
import org.loose.fis.sre.model.Eveniment;
import org.loose.fis.sre.model.Ticket;
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.services.TicketService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class BuyMatchTicketController {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private ChoiceBox categorySelector;
    @FXML
    private ChoiceBox parkingSelector;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Text modifiedMessage;
    @FXML
    private Button btnBuyTicket;
    @FXML
    private TextField nameField;
    @FXML
    private Button btnCancelAction;
    private static Eveniment event;
    private static Ticket currentTicket;

    @FXML
    public void initialize() {
        categorySelector.getItems().addAll("Cat A", "Cat B", "Cat C");
        parkingSelector.getItems().addAll("Doresc", "Nu Doresc");
    }

    public static Eveniment getEvent() {
        return event;
    }

    public static void setEvent(Eveniment event) {
        BuyMatchTicketController.event = event;
    }

    public static Ticket getCurrentTicket() {
        return currentTicket;
    }

    private static void setCurrentTicket(String currentTicketId) {
        Ticket current = TicketService.returnTicket(currentTicketId);
        BuyMatchTicketController.currentTicket = current;
    }


    public void handleHomePageAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageClient.fxml"));
        Stage window = (Stage) btnHomePage.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleCancelAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageClient.fxml"));
        Stage window = (Stage) btnCancelAction.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleLoggingOut(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) btnLogOut.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleBuyTicketAction(ActionEvent actionEvent) throws TicketAlreadyExistsException, IOException {
        String nameString = nameField.getText();
        String phoneNumberString = phoneNumberField.getText();
        String categoryString = (String) categorySelector.getValue();
        boolean parkingValue = false;
        String parkingSpot ="";
        if (((String)categorySelector.getValue()).equals("Doresc")) {
            parkingValue = true;
            event.updateReservedParkingSpots();
            parkingSpot = ""+event.getReservedParkingSpots();
        }
        event.updateEventSoldTickets();
        //EventService.updateEvent(event);
        String id = "#"+event.get_event_Id()+event.getSoldTickets();
        TicketService.addTicket(id, nameString, phoneNumberString, categoryString,150.00 ,parkingValue, parkingSpot, event.get_event_Id());
        setCurrentTicket(id);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("matchTicket.fxml"));
        Stage window = (Stage) btnBuyTicket.getScene().getWindow();
        window.setScene(new Scene(root, 600, 287));
    }


}
