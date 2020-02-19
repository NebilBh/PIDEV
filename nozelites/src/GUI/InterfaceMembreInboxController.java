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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.ServicesMessage;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class InterfaceMembreInboxController implements Initializable {
    
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
    ObservableList<MessageForGUI> olist = FXCollections.observableArrayList(srv.afficherLesMessagesEnvoyes(5)); //Id du membre connecté
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        
        idCol.setMinWidth(50);
        objetCol.setMinWidth(50);
        texteCol.setMinWidth(200);
        nomCol.setMinWidth(100);
        prenomCol.setMinWidth(100);
        actionCol.setMinWidth(150);
        
        tabMessages.getColumns().addAll(idCol,objetCol,texteCol,aCol,actionCol);
        
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