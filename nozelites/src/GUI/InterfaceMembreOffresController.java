/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import entities.OffreForGUI;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.ServicesOffre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class InterfaceMembreOffresController implements Initializable {

    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    
    Session session = new Session();

    @FXML
    private void acceuil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreAcceuilInterface.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void profil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreProfilInterface.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void portfolio(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembrePortfolioAfficher_interface.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void groupes(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupesInterface.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void inbox(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }
    
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Go For It!");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	OffreForGUI currentOffre = (OffreForGUI) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                        //change in DB
                        srv.accepterOffre(currentOffre);
                        
                        tabOffres.getItems().clear();
                        ObservableList<OffreForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresRecus(session.getIdSession())); //Id du membre connecté
                        tabOffres.getItems().addAll(olist);
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
    
    private class ButtonCell1 extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Not For Me");
        
        ButtonCell1(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	OffreForGUI currentOffre = (OffreForGUI) ButtonCell1.this.getTableView().getItems().get(ButtonCell1.this.getIndex());
                        //change in DB
                        srv.refuserOffre(currentOffre);
                        
                        tabOffres.getItems().clear();
                        ObservableList<OffreForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresRecus(session.getIdSession())); //Id du membre connecté
                        tabOffres.getItems().addAll(olist);
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
    private AnchorPane anchorOffre;
    @FXML
    private TableView<OffreForGUI> tabOffres = new TableView<OffreForGUI>();
    @FXML
    private TextField chercherOffre;
    
    ServicesOffre srv = new ServicesOffre();
    ObservableList<OffreForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresRecus(session.getIdSession())); //Id du membre connecté
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        chercherOffre.textProperty().addListener((observable, oldValue, newValue) -> {
        //System.out.println("textfield changed from " + oldValue + " to " + newValue);
        olist = FXCollections.observableArrayList(srv.afficherLesOffresRecus(session.getIdSession())); //Id du chasseur connecté
        ObservableList<OffreForGUI> olistRech = FXCollections.observableArrayList();
        olistRech.clear();

        for (int i=0;i<olist.size();i++){
            if(olist.get(i).getType().contains(newValue) || olist.get(i).getEntreprise().contains(newValue) || olist.get(i).getDomaine().contains(newValue) || olist.get(i).getPoste().contains(newValue) || olist.get(i).getRequis().contains(newValue) || olist.get(i).getDescription().contains(newValue) || olist.get(i).getDate().contains(newValue) || olist.get(i).getEtat().contains(newValue) || olist.get(i).getNom().contains(newValue) || olist.get(i).getPrenom().contains(newValue))
            {
                olistRech.add(olist.get(i));
            }
        }
  
        tabOffres.setItems(olistRech);
        }); 
        
        TableColumn<OffreForGUI, String> idCol //
              = new TableColumn<OffreForGUI, String>("Id");
        
        TableColumn<OffreForGUI, String> typeCol //
              = new TableColumn<OffreForGUI, String>("Type");
    
        TableColumn<OffreForGUI, String> entrepriseCol//
                  = new TableColumn<OffreForGUI, String>("Entreprise");
        
        TableColumn<OffreForGUI, String> domaineCol //
                  = new TableColumn<OffreForGUI, String>("Domaine");
        
        TableColumn<OffreForGUI, String> posteCol //
                  = new TableColumn<OffreForGUI, String>("Poste");
        
        TableColumn<OffreForGUI, String> requisCol //
                  = new TableColumn<OffreForGUI, String>("Requis");
        
        TableColumn<OffreForGUI, String> descriptionCol //
                  = new TableColumn<OffreForGUI, String>("Description");
        
        TableColumn<OffreForGUI, String> dateCol //
                  = new TableColumn<OffreForGUI, String>("Date");
        
        TableColumn<OffreForGUI, String> etatCol //
                  = new TableColumn<OffreForGUI, String>("Etat");
        
        TableColumn<OffreForGUI, String> userNameCol //
                  = new TableColumn<OffreForGUI, String>("Recruteur");
        
        TableColumn<OffreForGUI, String> nomCol //
              = new TableColumn<OffreForGUI, String>("Nom");
 
        TableColumn<OffreForGUI, String> prenomCol //
              = new TableColumn<OffreForGUI, String>("Prenom");
        
        TableColumn accepterCol = new TableColumn<>("Accepter");
        
        TableColumn refuserCol = new TableColumn<>("Refuser");
        
        TableColumn<OffreForGUI, String> choixCol //
              = new TableColumn<OffreForGUI, String>("Choix");
        
        choixCol.getColumns().addAll(accepterCol,refuserCol);
        userNameCol.getColumns().addAll(nomCol, prenomCol);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        entrepriseCol.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        domaineCol.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        posteCol.setCellValueFactory(new PropertyValueFactory<>("poste"));
        requisCol.setCellValueFactory(new PropertyValueFactory<>("requis"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        accepterCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        refuserCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        
        idCol.setVisible(false);
        typeCol.setMinWidth(100);
        entrepriseCol.setMinWidth(100);
        domaineCol.setMinWidth(100);
        posteCol.setMinWidth(100);
        requisCol.setMinWidth(100);
        descriptionCol.setMinWidth(100);
        dateCol.setMinWidth(100);
        etatCol.setMinWidth(100);
        nomCol.setMinWidth(100);
        prenomCol.setMinWidth(100);
        accepterCol.setMinWidth(100);
        refuserCol.setMinWidth(100);
        
        tabOffres.getColumns().addAll(idCol,typeCol,entrepriseCol,domaineCol,posteCol,requisCol,descriptionCol,dateCol,etatCol,userNameCol,choixCol);      
        
        tabOffres.setItems(olist);
        
        //Adding the Button to the cell
        accepterCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
        
        //Adding the Button to the cell
        refuserCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell1();
            }
        
        });
        
    }    

    @FXML
    private void consulterInboxRecus(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void consulterInboxEnvoyes(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInbox.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void consulterMesOffres(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreOffres.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void sendAmessage(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreEnvoyerMessage.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }
    
}
