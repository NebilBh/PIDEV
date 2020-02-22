/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.MessageForGUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.ServicesMessage;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class InterfaceMembreInboxRecusController implements Initializable {

    @FXML
    private Button consulterMessagesRecus;
    @FXML
    private Button consulterMessagesEnvoyes;
    @FXML
    private Button consulterOffres;
    @FXML
    private Button envoyerMessage;
    @FXML
    private AnchorPane anchorMessageRecus;
    @FXML
    private TextField rechercherMessage;
    @FXML
    private TableView<MessageForGUI> tabMessages = new TableView<MessageForGUI>();
    
    ServicesMessage srv = new ServicesMessage();
    ObservableList<MessageForGUI> olist = FXCollections.observableArrayList(srv.afficherLesMessagesRecus(5)); //Id du membre connecté
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rechercherMessage.textProperty().addListener((observable, oldValue, newValue) -> {
        //System.out.println("textfield changed from " + oldValue + " to " + newValue);
        olist = FXCollections.observableArrayList(srv.afficherLesMessagesRecus(5)); //Id du membre connecté
        ObservableList<MessageForGUI> olistRech = FXCollections.observableArrayList();
        olistRech.clear();

        for (int i=0;i<olist.size();i++){
            if(olist.get(i).getObjet().contains(newValue) || olist.get(i).getTexte().contains(newValue) || olist.get(i).getNom().contains(newValue) || olist.get(i).getPrenom().contains(newValue) || olist.get(i).getDate().contains(newValue))
            {
                olistRech.add(olist.get(i));
            }
        }
                
        tabMessages.setItems(olistRech);
        });
        
        TableColumn<MessageForGUI, String> idCol //
              = new TableColumn<MessageForGUI, String>("Id");
        
        TableColumn<MessageForGUI, String> objetCol //
              = new TableColumn<MessageForGUI, String>("Objet");
        
        TableColumn<MessageForGUI, String> texteCol //
              = new TableColumn<MessageForGUI, String>("Message");
        
        TableColumn<MessageForGUI, String> deCol //
              = new TableColumn<MessageForGUI, String>("De");
        
        TableColumn<MessageForGUI, String> nomCol //
              = new TableColumn<MessageForGUI, String>("Nom");
        
        TableColumn<MessageForGUI, String> prenomCol //
              = new TableColumn<MessageForGUI, String>("Prenom");
        
        TableColumn<MessageForGUI, String> dateCol //
              = new TableColumn<MessageForGUI, String>("Date");
        
        deCol.getColumns().addAll(nomCol,prenomCol);
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        objetCol.setCellValueFactory(new PropertyValueFactory<>("objet"));
        texteCol.setCellValueFactory(new PropertyValueFactory<>("texte"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        idCol.setVisible(false);
        objetCol.setMinWidth(100);
        texteCol.setMinWidth(200);
        nomCol.setMinWidth(100);
        prenomCol.setMinWidth(100);
        dateCol.setMinWidth(100);
        
        tabMessages.getColumns().addAll(idCol,objetCol,texteCol,deCol,dateCol);
        
        tabMessages.setItems(olist);
    }    

    @FXML
    private void consulterInboxRecus(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorMessageRecus.getChildren().setAll(pane);
    }

    @FXML
    private void consulterInboxEnvoyes(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInbox.fxml"));
        anchorMessageRecus.getChildren().setAll(pane);
    }

    @FXML
    private void consulterMesOffres(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreOffres.fxml"));
        anchorMessageRecus.getChildren().setAll(pane);
    }

    @FXML
    private void sendAmessage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreEnvoyerMessage.fxml"));
        anchorMessageRecus.getChildren().setAll(pane);
    }
    
}
