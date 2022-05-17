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
import java.util.Objects;
import java.io.IOException;
import javafx.scene.text.Text;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class HomePageController {

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnAccountInfo;
    @FXML
    private Button btnShowSelection;
    @FXML
    private Button btnShowList;
    @FXML
    private Text selectionMessage;
    @FXML
    private ListView<String> list;
    private ObservableList<String> items=FXCollections.observableArrayList();
    public static Eveniment current_selected_event;

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
    public void handleAddEventAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adminAddEvent.fxml"));
        Stage window = (Stage) btnAccountInfo.getScene().getWindow();
        window.setScene(new Scene(root, 600, 450));
    }
    public void handleCreateListAction(){
        items.removeAll();
        items=EventService.getTitleList();
        list.setItems(items);
    }
    public void handleShowSelectionAction(){
        String selection;
        selection=list.getSelectionModel().getSelectedItem();
        selection="Ati selectat:" + selection;
        selectionMessage.setText(selection);
    }
    public void handleBuyTicketAction(){
        String event_title;
        event_title=list.getSelectionModel().getSelectedItem();
        if(EventService.returnEventByTitle(event_title)!=null)
        current_selected_event=EventService.returnEventByTitle(event_title);
        else
            System.out.println("ERROR:No event with the title "+event_title+" was found in the database");
        //System.out.println("TEST CURRENT EVENT "+ current_selected_event.get_event_Title());
    }


}


