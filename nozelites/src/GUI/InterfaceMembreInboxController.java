/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer.Record;
import entities.MessageForGUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.ServicesMessage;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class InterfaceMembreInboxController implements Initializable {

    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;

    @FXML
    private void acceuil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreAcceuilInterface.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void profil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreProfilInterface.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void portfolio(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembrePortfolioAfficher_interface.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void groupes(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupesInterface.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void inbox(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    
    
    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Vraiment ?");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	MessageForGUI currentMessage = (MessageForGUI) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentMessage);
                        //remove from DB
                        srv.supprimerMessage(currentMessage);
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
            setGraphic(cellButton);
            }
            else{
            setGraphic(null);
            }
        }
    }

    @FXML
    private Button consulterMessagesRecus;
    @FXML
    private Button consulterMessagesEnvoyes;
    @FXML
    private Button consulterOffres;
    @FXML
    private Button envoyerMessage;
    @FXML
    private AnchorPane anchorMessageEnvoyes;
    @FXML
    private TextField rechercherMessage;
    @FXML
    private TableView<MessageForGUI> tabMessages = new TableView<MessageForGUI>();
    
    ServicesMessage srv = new ServicesMessage();
    Session session = new Session();
    ObservableList<MessageForGUI> olist = FXCollections.observableArrayList(srv.afficherLesMessagesEnvoyes(session.getIdSession())); //Id du membre connecté
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rechercherMessage.textProperty().addListener((observable, oldValue, newValue) -> {
        //System.out.println("textfield changed from " + oldValue + " to " + newValue);
        olist = FXCollections.observableArrayList(srv.afficherLesMessagesEnvoyes(session.getIdSession())); //Id du membre connecté
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
        
        TableColumn<MessageForGUI, String> aCol //
              = new TableColumn<MessageForGUI, String>("A");
        
        TableColumn<MessageForGUI, String> nomCol //
              = new TableColumn<MessageForGUI, String>("Nom");
        
        TableColumn<MessageForGUI, String> prenomCol //
              = new TableColumn<MessageForGUI, String>("Prenom");
        
        TableColumn<MessageForGUI, String> dateCol //
              = new TableColumn<MessageForGUI, String>("Date");
        
        TableColumn actionCol = new TableColumn<>("Supprimer Message");
        
        aCol.getColumns().addAll(nomCol,prenomCol);
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        objetCol.setCellValueFactory(new PropertyValueFactory<>("objet"));
        texteCol.setCellValueFactory(new PropertyValueFactory<>("texte"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        actionCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        
        idCol.setVisible(false);
        objetCol.setMinWidth(100);
        texteCol.setMinWidth(200);
        nomCol.setMinWidth(100);
        prenomCol.setMinWidth(100);
        actionCol.setMinWidth(150);
        dateCol.setMinWidth(100);
        
        tabMessages.getColumns().addAll(idCol,objetCol,texteCol,aCol,dateCol,actionCol);
        
        tabMessages.setItems(olist);
        
        //Adding the Button to the cell
        actionCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new InterfaceMembreInboxController.ButtonCell();
            }
        
        });
    }    

    @FXML
    private void consulterInboxRecus(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void consulterInboxEnvoyes(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInbox.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void consulterMesOffres(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreOffres.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }

    @FXML
    private void sendAmessage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreEnvoyerMessage.fxml"));
        anchorMessageEnvoyes.getChildren().setAll(pane);
    }
    
}
