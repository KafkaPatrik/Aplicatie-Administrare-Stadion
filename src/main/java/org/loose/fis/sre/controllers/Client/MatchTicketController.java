package org.loose.fis.sre.controllers.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Eveniment;
import org.loose.fis.sre.model.Ticket;

import java.io.IOException;
import java.util.Locale;

public class MatchTicketController {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Label dateText;
    @FXML
    private Label locationText;
    @FXML
    private Label gateText;
    @FXML
    private Label gatesOpenText;
    @FXML
    private Label priceText;
    @FXML
    private Label idText;
    @FXML
    private Label seatText;
    @FXML
    private Label team1Text;
    @FXML
    private Label team2Text;


    @FXML
    public void initialize() {
        Eveniment event = BuyMatchTicketController.getEvent();
        Ticket ticket = BuyMatchTicketController.getCurrentTicket();
        dateText.setText(event.get_event_Date());
        gatesOpenText.setText("21:45");
        priceText.setText("$"+ticket.getPrice());
        seatText.setText("SEAT: "+event.getSoldTickets());
        idText.setText(ticket.getIdCode());
        locationText.setText(event.get_event_Location());
        gateText.setText(ticket.getCategory().toUpperCase(Locale.ROOT));
        String arr[] = event.get_event_Title().split(" ", 2);
        String teams[] = arr[1].split("-",2);
        team1Text.setText(teams[0]);
        team2Text.setText(teams[1]);
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

}
