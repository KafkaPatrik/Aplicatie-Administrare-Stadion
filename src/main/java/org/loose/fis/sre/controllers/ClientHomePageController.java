package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.loose.fis.sre.controllers.Client.BuyConcertTicketController;
import org.loose.fis.sre.controllers.Client.BuyMatchTicketController;
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.model.Eveniment;

import java.util.Locale;
import java.util.Objects;
import java.io.IOException;
import javafx.scene.text.Text;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClientHomePageController {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnAccountInfo;
    @FXML
    private Text selectionMessage;
    @FXML
    private ListView<String> list;
    @FXML
    private Button btnBuyTicket;
    @FXML
    private ObservableList<String> items=FXCollections.observableArrayList();

    public void initialize(){
        items.removeAll();
        items=EventService.getTitleList();
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

    public void handleShowSelectionAction(ActionEvent actionEvent) {
        String selection;
        selection=list.getSelectionModel().getSelectedItem();
        selection="Ati selectat:" + selection;
        selectionMessage.setText(selection);
    }

    public void handleBuyTicketAction(ActionEvent actionEvent) throws IOException {
        String eventTitle;
        eventTitle = list.getSelectionModel().getSelectedItem();
        Eveniment eveniment = EventService.returnEventByTitle(eventTitle);
        if(eveniment!=null) {
            String arr[] = eventTitle.split(" ", 2);
            if (arr[0].toUpperCase(Locale.ROOT).equals("CONCERT")){
                BuyConcertTicketController.setEvent(eveniment);
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("buyConcertTicket.fxml"));
                Stage window = (Stage) btnBuyTicket.getScene().getWindow();
                window.setScene(new Scene(root, 600, 450));
            }
            else if (arr[0].toUpperCase(Locale.ROOT).equals("MECI")) {
                BuyMatchTicketController.setEvent(eveniment);
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("buyMatchTicket.fxml"));
                Stage window = (Stage) btnBuyTicket.getScene().getWindow();
                window.setScene(new Scene(root, 600, 450));
            }
        }
        else
            System.out.println("ERROR:No event with the title "+eventTitle+" was found in the database");
        //System.out.println("TEST CURRENT EVENT "+ current_selected_event.get_event_Title());

    }
}


