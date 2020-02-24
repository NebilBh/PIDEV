/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer.Record;
import entities.Offre;
import entities.OffreForGUI;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import services.ServicesOffre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class InterfaceChasseurOffreController implements Initializable {

    @FXML
    private Label BoutonOffre;
    @FXML
    private Label BoutonAcceuil;
    @FXML
    private Label BoutonProfil;
    @FXML
    private Label BoutonElites;
    @FXML
    private Button BoutonDeco;

    @FXML
    private void BoutonOffreGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceChasseurOffre.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonAcceuilGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ChasseurTeteAcceuilInterface.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonProfilGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ChasseurProfilInterface.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonElitesGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceChasseurOffre.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonDecoGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConnectionInterface.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }
    
    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Vraiment ?");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	OffreForGUI currentOffre = (OffreForGUI) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentOffre);
                        //remove from DB
                        srv.supprimerOffre(currentOffre);
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
    private AnchorPane anchorOffre;
    @FXML
    private TableView<OffreForGUI> tabOffres = new TableView<OffreForGUI>();
    @FXML
    private TextField rechercherOffre;
    @FXML
    private Button envoyerOffre;
    
    ServicesOffre srv = new ServicesOffre();
    Session session = new Session();
    ObservableList<OffreForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresEnvoyees(session.getIdSession())); //Id du chasseur connecté
    
    
    
    @FXML
    private void envoyerUneOffre(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceChasseurEnvoyerOffre.fxml"));
        anchorOffre.getChildren().setAll(pane);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rechercherOffre.textProperty().addListener((observable, oldValue, newValue) -> {
        //System.out.println("textfield changed from " + oldValue + " to " + newValue);
        olist = FXCollections.observableArrayList(srv.afficherLesOffresEnvoyees(session.getIdSession())); //Id du chasseur connecté
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
        
        tabOffres.setEditable(true);
        
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
                  = new TableColumn<OffreForGUI, String>("Elite");
        
        TableColumn<OffreForGUI, String> nomCol //
              = new TableColumn<OffreForGUI, String>("Nom");
 
        TableColumn<OffreForGUI, String> prenomCol //
              = new TableColumn<OffreForGUI, String>("Prenom");
        
        TableColumn actionCol = new TableColumn<>("Annuler Offre");
 
        // Add sub columns to the useNameCol
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
        actionCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
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
        actionCol.setMinWidth(100);
        
        tabOffres.getColumns().addAll(idCol,typeCol,entrepriseCol,domaineCol,posteCol,requisCol,descriptionCol,dateCol,etatCol,userNameCol,actionCol);      
        
        tabOffres.setItems(olist);
        
        typeCol.setCellFactory(TextFieldTableCell.<OffreForGUI> forTableColumn());
        typeCol.setOnEditCommit((CellEditEvent<OffreForGUI, String> event) -> {
            TablePosition<OffreForGUI, String> pos = event.getTablePosition();
 
            String newType = event.getNewValue();
 
            int row = pos.getRow();
            OffreForGUI o = event.getTableView().getItems().get(row);
 
            o.setType(newType);
            
            srv.modifierOffre(o);
        });
        
        entrepriseCol.setCellFactory(TextFieldTableCell.<OffreForGUI> forTableColumn());
        entrepriseCol.setOnEditCommit((CellEditEvent<OffreForGUI, String> event) -> {
            TablePosition<OffreForGUI, String> pos = event.getTablePosition();
 
            String newEntreprise = event.getNewValue();
 
            int row = pos.getRow();
            OffreForGUI o = event.getTableView().getItems().get(row);
 
            o.setEntreprise(newEntreprise);
            
            srv.modifierOffre(o);
        });
        
        domaineCol.setCellFactory(TextFieldTableCell.<OffreForGUI> forTableColumn());
        domaineCol.setOnEditCommit((CellEditEvent<OffreForGUI, String> event) -> {
            TablePosition<OffreForGUI, String> pos = event.getTablePosition();
 
            String newDomaine = event.getNewValue();
 
            int row = pos.getRow();
            OffreForGUI o = event.getTableView().getItems().get(row);
 
            o.setDomaine(newDomaine);
            
            srv.modifierOffre(o);
        });
        
        posteCol.setCellFactory(TextFieldTableCell.<OffreForGUI> forTableColumn());
        posteCol.setOnEditCommit((CellEditEvent<OffreForGUI, String> event) -> {
            TablePosition<OffreForGUI, String> pos = event.getTablePosition();
 
            String newPoste = event.getNewValue();
 
            int row = pos.getRow();
            OffreForGUI o = event.getTableView().getItems().get(row);
 
            o.setPoste(newPoste);
            
            srv.modifierOffre(o);
        });
        
        requisCol.setCellFactory(TextFieldTableCell.<OffreForGUI> forTableColumn());
        requisCol.setOnEditCommit((CellEditEvent<OffreForGUI, String> event) -> {
            TablePosition<OffreForGUI, String> pos = event.getTablePosition();
 
            String newRequis = event.getNewValue();
 
            int row = pos.getRow();
            OffreForGUI o = event.getTableView().getItems().get(row);
 
            o.setRequis(newRequis);
            
            srv.modifierOffre(o);
        });
        
        descriptionCol.setCellFactory(TextFieldTableCell.<OffreForGUI> forTableColumn());
        descriptionCol.setOnEditCommit((CellEditEvent<OffreForGUI, String> event) -> {
            TablePosition<OffreForGUI, String> pos = event.getTablePosition();
 
            String newDescription = event.getNewValue();
 
            int row = pos.getRow();
            OffreForGUI o = event.getTableView().getItems().get(row);
 
            o.setDescription(newDescription);
            
            srv.modifierOffre(o);
        });
        
        //Adding the Button to the cell
        actionCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
        
        
    }    
    
}
