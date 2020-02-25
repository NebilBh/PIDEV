/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import entities.ReclamationForGUI;
import entities.ReclamationForPub;
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
public class ReclamationPublicationController implements Initializable {

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
                	ReclamationForPub currentOffre = (ReclamationForPub) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentOffre);
                        
                        //remove from DB
                        srv.supprimerReclamationP(currentOffre);
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
        final Button cellButton1 = new Button("en cours ");
     

        //Display button if the row is not empty
     
      ButtonCell1(){
            
        	//Action when the button is pressed
            cellButton1.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    	ReclamationForPub currentOffre = (ReclamationForPub) ButtonCell1.this.getTableView().getItems().get(ButtonCell1.this.getIndex());
                //currentOffre.getEtat();
             //currentOffre.setEtat("1");
             srv.traiterReclamation(currentOffre.getId());
             reclp.getItems().clear();
             ObservableList<ReclamationForPub> olist = FXCollections.observableArrayList(srv.afficherPublicationReclamation());
             reclp.getItems().addAll(olist);
             
             
               
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
    
      private class ButtonCell2 extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton2 = new Button("traiter ");
     

        //Display button if the row is not empty
     
      ButtonCell2(){
            
        	//Action when the button is pressed
            cellButton2.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    	ReclamationForPub currentOffre = (ReclamationForPub) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                //currentOffre.getEtat();
             //currentOffre.setEtat("1");
             srv.traiter1Reclamation(currentOffre.getId());
             reclp.getItems().clear();
             ObservableList<ReclamationForPub> olist = FXCollections.observableArrayList(srv.afficherPublicationReclamation());
             reclp.getItems().addAll(olist);
        
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
            setGraphic(cellButton2);
            }
            else{
            setGraphic(null);
            }
        }
    }
    
    
@FXML
    private TableView<ReclamationForPub> reclp = new TableView<ReclamationForPub>();
    
  //Id du chasseur connecté
  
     ServicesReclamation srv = new ServicesReclamation();
        ObservableList<ReclamationForPub> olist = FXCollections.observableArrayList(srv.afficherPublicationReclamation());

    /**
     * Initializes the controller class.
     */
        //srv.afficherGroupeReclamation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         reclp.setEditable(true);
        
      
        TableColumn<ReclamationForPub, Integer> idCol //
              = new TableColumn<ReclamationForPub, Integer>("id");
        
        TableColumn<ReclamationForPub, String> nomCol //
              = new TableColumn<ReclamationForPub, String>("nom");
        
        
        TableColumn<ReclamationForPub, String> prenomCol //
              = new TableColumn<ReclamationForPub, String>("prenom");
        
        
        TableColumn<ReclamationForPub, String> mailCol //
              = new TableColumn<ReclamationForPub, String>("mail");
        
         TableColumn<ReclamationForPub, String> titreCol //
              = new TableColumn<ReclamationForPub, String>("titre publication");
         
        TableColumn<ReclamationForPub, String> descriptionCol //
              = new TableColumn<ReclamationForPub, String>("description");
        
        TableColumn<ReclamationForPub, String> descriptiongCol //
              = new TableColumn<ReclamationForPub, String>("description publication");
        
          TableColumn<ReclamationForPub, String> etatCol //
              = new TableColumn<ReclamationForPub, String>("etat");
          
            TableColumn<ReclamationForPub, String> selectCol //
              = new TableColumn<ReclamationForPub, String>("selecteur");
            
            TableColumn<ReclamationForPub, String> dateCol //
              = new TableColumn<ReclamationForPub, String>("date");
            
            TableColumn actionCol = new TableColumn<>("Supprimer");
            TableColumn traiterCol = new TableColumn<>("Traiter");
         TableColumn traiter1Col = new TableColumn<>("Traiter");
         idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptiongCol.setCellValueFactory(new PropertyValueFactory<>("descriptionp"));
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
     
        
        reclp.getColumns().addAll(nomCol,prenomCol,mailCol,titreCol,descriptionCol,descriptiongCol,etatCol,selectCol,dateCol,actionCol,traiterCol,traiter1Col);
        
        reclp.setItems(olist);
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
        
           traiter1Col.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell2();
            }
     
        });
        
        
           
    }
         
           
    
    }    
    

