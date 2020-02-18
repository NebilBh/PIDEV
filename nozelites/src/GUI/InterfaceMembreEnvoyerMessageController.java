/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class InterfaceMembreEnvoyerMessageController implements Initializable {

    @FXML
    private Button consulterMessagesRecus;
    @FXML
    private Button consulterMessagesEnvoyes;
    @FXML
    private Button consulterOffres;
    @FXML
    private Button envoyerMessage;
    @FXML
    private AnchorPane anchorEnvoyerMessage;
    @FXML
    private TextField emailField;
    @FXML
    private TextField objetField;
    @FXML
    private TextArea messageField;
    @FXML
    private Button submitMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void consulterInboxRecus(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorEnvoyerMessage.getChildren().setAll(pane);
    }

    @FXML
    private void consulterInboxEnvoyes(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInbox.fxml"));
        anchorEnvoyerMessage.getChildren().setAll(pane);
    }

    @FXML
    private void consulterMesOffres(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreOffres.fxml"));
        anchorEnvoyerMessage.getChildren().setAll(pane);
    }

    @FXML
    private void sendAmessage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreEnvoyerMessage.fxml"));
        anchorEnvoyerMessage.getChildren().setAll(pane);
    }

    @FXML
    private void submitLeMessage(ActionEvent event) {
    }
    
}
