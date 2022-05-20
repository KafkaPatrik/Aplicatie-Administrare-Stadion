package org.loose.fis.sre.controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Eveniment;
import org.loose.fis.sre.model.Ticket;

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
    private ChoiceBox categorySelector;
    @javafx.fxml.FXML
    private Label purchaseDateText;
    @javafx.fxml.FXML
    private Button btnHomePage;
    @javafx.fxml.FXML
    private Label gatesOpenText;
    @javafx.fxml.FXML
    private Label titleText;
    @javafx.fxml.FXML
    private Label priceText;
    @javafx.fxml.FXML
    private Label idText;
    @javafx.fxml.FXML
    private Label seatText;
    @javafx.fxml.FXML
    private Button btnSaveData;

    @Deprecated
    public void initialize() {
        Eveniment event = BuyConcertTicketController.getEvent();
        Ticket ticket = BuyConcertTicketController.getCurrentTicket();
        categorySelector.getItems().addAll("Cat A", "Cat B", "Cat C");
        titleText.setText(event.get_event_Title());
        dateText.setText("Data: "+event.get_event_Date());
        gatesOpenText.setText("Ora: 21:45");
        priceText.setText("Preț: "+ticket.getPrice());
        seatText.setText("Loc Nr: "+event.getSoldTickets());
        idText.setText("ID Bilet: "+ticket.getIdCode());
        locationText.setText("Locația: "+event.get_event_Location());
        gateText.setText("Categoria Actuală: "+ticket.getCategory());
        descriptionText.setText("Descriere: "+event.get_event_Description());
        nameText.setText("Nume Persoană: "+ticket.getTicketOwnerName());
        phoneNumberText.setText("Nr. Telefon: "+ticket.getPhoneNumber());
        purchaseDateText.setText("Data și Ora Cumpărării: "+ticket.getPurchaseDateTimeStamp());
        if (ticket.isHasParkingSpot()){
            parkingText.setText("Loc Parcare Nr: #"+ticket.getParkingSpot());
        }
        else parkingText.setText("");
    }

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


    public void handleSaveDataAction(ActionEvent actionEvent) throws IOException {
        Eveniment eveniment = BuyConcertTicketController.getEvent();
        Ticket ticket = BuyConcertTicketController.getCurrentTicket();
        String newCategory = (String) categorySelector.getValue();
        ticket.changeCategory(newCategory);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientTicketsHistory.fxml"));
        Stage window = (Stage) btnHomePage.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }
}
