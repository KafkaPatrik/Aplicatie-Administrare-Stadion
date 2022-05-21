package org.loose.fis.sre.controllers.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.controllers.LoginController;
import org.loose.fis.sre.model.Eveniment;
import org.loose.fis.sre.model.Ticket;
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.services.TicketService;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Locale;

public class ViewTicketsHistoryController {

        @FXML
        private Button btnLogOut;
        @FXML
        private Button btnHomePage;
        @FXML
        private Button btnViewTicket;
        @FXML
        private Button btnAccountInfo;
        @FXML
        private Button btnModifyTicket;
        @FXML
        private ListView<String> list;
        @FXML
        private Text message;
        @FXML
        private ObservableList<String> items= FXCollections.observableArrayList();

        public void initialize(){
            items.removeAll();
            items= TicketService.getUserTicketsList(LoginController.current_user);
            list.setItems(items);
        }

        public void handleLoggingOut(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Stage window = (Stage) btnLogOut.getScene().getWindow();
            window.setScene(new Scene(root, 600, 450));
        }


        public void handleHomePageAction (ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageClient.fxml"));
            Stage window = (Stage) btnHomePage.getScene().getWindow();
            window.setScene(new Scene(root, 600, 450));
        }

        public void handleAccountInfoAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientAccountInfoAccess.fxml"));
        Stage window = (Stage) btnAccountInfo.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
        }

        public void handleViewTicketAction(ActionEvent actionEvent) throws IOException {
            String selectedItem = list.getSelectionModel().getSelectedItem();
            String tmp[] = selectedItem.split(" ",2);
            String ticketID = tmp[0];
            Ticket ticket = TicketService.returnTicket(ticketID);
            Eveniment eveniment = EventService.returnCurrentEvent(ticket.getId_event());
            if(eveniment!=null) {
                    String arr[] = tmp[1].split(" ", 2);
                if (arr[0].toUpperCase(Locale.ROOT).equals("CONCERT")){
                    BuyConcertTicketController.setCurrentTicket(ticketID);
                    BuyConcertTicketController.setEvent(eveniment);
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewConcertTicket.fxml"));
                    Stage window = (Stage) btnViewTicket.getScene().getWindow();
                    window.setScene(new Scene(root, 600, 400));
                }
                else if (arr[0].toUpperCase(Locale.ROOT).equals("MECI")) {
                    BuyMatchTicketController.setCurrentTicket(ticketID);
                    BuyMatchTicketController.setEvent(eveniment);
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewMatchTicket.fxml"));
                    Stage window = (Stage) btnViewTicket.getScene().getWindow();
                    window.setScene(new Scene(root, 600, 400));
                }
            }
            else {
                message.setText("Există o eroare, încercați mai târziu!");
            }
        }

        public void handleModifyTicketAction(ActionEvent actionEvent) throws IOException {
            String selectedItem = list.getSelectionModel().getSelectedItem();
            String tmp[] = selectedItem.split(" ",2);
            String ticketID = tmp[0];
            Ticket ticket = TicketService.returnTicket(ticketID);
            Eveniment eveniment = EventService.returnCurrentEvent(ticket.getId_event());
            BuyConcertTicketController.setCurrentTicket(ticketID);
            BuyConcertTicketController.setEvent(eveniment);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifyTicket.fxml"));
            Stage window = (Stage) btnModifyTicket.getScene().getWindow();
            window.setScene(new Scene(root, 600, 400));
        }
}



