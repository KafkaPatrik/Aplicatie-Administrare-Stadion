package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.EventService;
import org.loose.fis.sre.model.Eveniment;
import org.loose.fis.sre.controllers.Admin.AdminAccessEditEventController;
import java.util.Objects;
import java.io.IOException;
import javafx.scene.text.Text;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AdminHomePageController {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnEditEvent;
    @FXML
    private Button btnDeleteEvent;
    @FXML
    private Button btnAccountInfo;
    @FXML
    private Button btnShowSelection;
    @FXML
    private Button btnShowList;
    @FXML
    private Text selectionMessage;
    @FXML
    public ListView<String> list;
    public ObservableList<String> items=FXCollections.observableArrayList();
    public static Eveniment current_selected_event;

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAdmin.fxml"));
        Stage window = (Stage) btnHomePage.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }

    public void handleAccountInfoAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adminAccountInfoAccess.fxml"));
        Stage window = (Stage) btnAccountInfo.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }
    public void handleAddEventAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adminAddEvent.fxml"));
        Stage window = (Stage) btnAccountInfo.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }
    public void handleShowSelectionAction(){
        String selection;
        selection=list.getSelectionModel().getSelectedItem();
        current_selected_event=EventService.returnEventByTitle(selection);
        int temp_ticket_cnt=current_selected_event.get_event_max_participants()-current_selected_event.getSoldTickets();
        int temp_parking_cnt=current_selected_event.getMaxParkingSpots()-current_selected_event.getReservedParkingSpots();
        if(selection==null)
           selection="Nu ati selectat nici un eveniment.";
        else {
            selection = "Ati selectat evenimentul:\n" + selection +
                    "\nLocatie:" + current_selected_event.get_event_Location()+
                    "\nData:" + current_selected_event.get_event_Date()+
                    "\nBilete disponibile:" + temp_ticket_cnt+"/"+current_selected_event.get_event_max_participants()+
                    "\nLoc. Parcare disponibile:" +temp_parking_cnt +"/"+current_selected_event.getMaxParkingSpots()
                     ;
            selectionMessage.setText(selection);
        }
    }
    public void handleEditEventAction(ActionEvent actionEvent)throws IOException{
        String selection;
        selection=list.getSelectionModel().getSelectedItem();
        current_selected_event=EventService.returnEventByTitle(selection);
        if(current_selected_event!=null) {
            AdminAccessEditEventController.setEvent(current_selected_event);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adminAccessEditEvent.fxml"));
            Stage window = (Stage) btnEditEvent.getScene().getWindow();
            window.setScene(new Scene(root, 600, 450));
        }
             else
        System.out.println("WARNING: No event is selected.");
    }
    public void handleDeleteEventAction(){
        String selection;
        selection=list.getSelectionModel().getSelectedItem();
        current_selected_event=EventService.returnEventByTitle(selection);
        EventService.deleteEvent(current_selected_event);
        initialize();
        selectionMessage.setText("Evenimentul a fost sters cu succes!");
    }
}


