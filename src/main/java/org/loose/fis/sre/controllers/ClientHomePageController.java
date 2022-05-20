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


    public void handleShowSelectionAction(){
        String selection;
        selection=list.getSelectionModel().getSelectedItem();
        selection="Ați selectat:  " + selection;
        selectionMessage.setText(selection);
    }

}


