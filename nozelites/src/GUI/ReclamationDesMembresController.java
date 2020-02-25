/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import entities.ReclamationForMembre;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.ServicesReclamation;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class ReclamationDesMembresController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private void acceuil(MouseEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminAcceuilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void reclamationgroupe(MouseEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ReclamationGROUPE.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void reclamationpublication(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ReclamationPublication.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void reclamationmembres(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ReclamationDesMembres.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void reclamationevenement(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ReclamationEvent.fxml"));
        root.getChildren().setAll(pane);
    }

  private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Supprimer ?");
       
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	ReclamationForMembre currentOffre = (ReclamationForMembre) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentOffre);
                        
                        //remove from DB
                        srv.supprimerReclamationM(currentOffre);
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
        final Button cellButton1 = new Button("traiter ");
     

        //Display button if the row is not empty
     
      ButtonCell1(){
            
        	//Action when the button is pressed
            cellButton1.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    	ReclamationForMembre currentOffre = (ReclamationForMembre) ButtonCell1.this.getTableView().getItems().get(ButtonCell1.this.getIndex());
                //currentOffre.getEtat();
             //currentOffre.setEtat("1");
             srv.traiterReclamation(currentOffre.getId());
             reclm.getItems().clear();
             ObservableList<ReclamationForMembre> olist = FXCollections.observableArrayList(srv.afficherMembreReclamation());
             reclm.getItems().addAll(olist);
             
             
               
               /* 	
                  //      currentOffre.getMail();
                    try {
                      
                        JavaMail.sendMailReclamation(currentOffre.getMail());
                    } catch (Exception ex) {
                        Logger.getLogger(Afficher_reclamationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                */
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
            setGraphic(cellButton1);
            }
            else{
            setGraphic(null);
            }
        }
    }
    
    
@FXML
    private TableView<ReclamationForMembre> reclm = new TableView<ReclamationForMembre>();
    
  //Id du chasseur connecté
  
     ServicesReclamation srv = new ServicesReclamation();
        ObservableList<ReclamationForMembre> olist = FXCollections.observableArrayList(srv.afficherMembreReclamation());

    /**
     * Initializes the controller class.
     */
        //srv.afficherGroupeReclamation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         reclm.setEditable(true);
        
      
       TableColumn<ReclamationForMembre, Integer> idCol //
              = new TableColumn<ReclamationForMembre, Integer>("id");
        
        TableColumn<ReclamationForMembre, String> nomCol //
              = new TableColumn<ReclamationForMembre, String>("nom");
        
        
        TableColumn<ReclamationForMembre, String> prenomCol //
              = new TableColumn<ReclamationForMembre, String>("prenom");
        
        
        TableColumn<ReclamationForMembre, String> mailCol //
              = new TableColumn<ReclamationForMembre, String>("mail");
        
         TableColumn<ReclamationForMembre, String> titreCol //
              = new TableColumn<ReclamationForMembre, String>("nom de la cible");
         
        TableColumn<ReclamationForMembre, String> descriptionCol //
              = new TableColumn<ReclamationForMembre, String>("prenom de la cible");
        
        TableColumn<ReclamationForMembre, String> descriptiongCol //
              = new TableColumn<ReclamationForMembre, String>("description");
        
          TableColumn<ReclamationForMembre, String> etatCol //
              = new TableColumn<ReclamationForMembre, String>("etat");
          
            TableColumn<ReclamationForMembre, String> selectCol //
              = new TableColumn<ReclamationForMembre, String>("selecteur");
            
            TableColumn<ReclamationForMembre, String> dateCol //
              = new TableColumn<ReclamationForMembre, String>("date");
            
            TableColumn actionCol = new TableColumn<>("Supprimer");
            TableColumn traiterCol = new TableColumn<>("Traiter");
         
         idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("nomc"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("prenomc"));
        descriptiongCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>("selecteur"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        actionCol.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
 
        });
        traiterCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }});
        
        
       idCol.setVisible(false);
        nomCol.setMinWidth(100);
        prenomCol.setMinWidth(100);
        mailCol.setMinWidth(100);
        titreCol.setMinWidth(100);
        descriptionCol.setMinWidth(100);
        descriptiongCol.setMinWidth(100);
        etatCol.setMinWidth(100);
        selectCol.setMinWidth(100);
        dateCol.setMinWidth(100);
        actionCol.setMinWidth(100);
     
        
        reclm.getColumns().addAll(idCol,nomCol,prenomCol,mailCol,titreCol,descriptionCol,descriptiongCol,etatCol,selectCol,dateCol,actionCol,traiterCol);
        
        reclm.setItems(olist);
               //Adding the Button to the cell
        actionCol.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell();
            }
            
         
        });
        
        traiterCol.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell1();
            }
            
             
           
        
           
        });
        
        
           
    }
         
           
    
    }


