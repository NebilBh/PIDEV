/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import entities.Portfolio;
import entities.ReclamationForGUI;
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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.ServicePortfolio;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class MembrePortfolioAfficher_interfaceController implements Initializable {

    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private AnchorPane root;
    
    

    @FXML
    private void acceuil(MouseEvent event) throws IOException {
        
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembreAcceuilInterface.fxml"));
                root.getChildren().setAll(pane);    
    }

    @FXML
    private void profil(MouseEvent event) {
    }

    @FXML
    private void portfolio(MouseEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("MembrePortfolioAfficher_interface.fxml"));
         root.getChildren().setAll(pane);
    }

    @FXML
    private void groupes(MouseEvent event) {
    }

    @FXML
    private void evenements(MouseEvent event) {
    }

    @FXML
    private void inbox(MouseEvent event) {
    }

    @FXML
    private void deconnexion(MouseEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembrePortfolioAjouter_interface.fxml"));
                root.getChildren().setAll(pane); 
    }

    @FXML
    private void voirportfolio(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembrePortfolioAfficher_interface.fxml"));
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
                	Portfolio currentOffre = (Portfolio) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentOffre);
                        //remove from DB
                        srv.supprimer(currentOffre);
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
    private TableView<Portfolio> tabPorte = new TableView<Portfolio>();
    
    ServicePortfolio srv = new ServicePortfolio();
        ObservableList<Portfolio> olist = FXCollections.observableArrayList(srv.afficher());
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          tabPorte.setEditable(true);
           TableColumn<Portfolio, Integer> idCol //
             = new TableColumn<Portfolio, Integer>("Id");
        
        
       TableColumn<Portfolio, Integer> idmembreCol //
             = new TableColumn<Portfolio, Integer>("Id Membre");
        
        
       TableColumn<Portfolio, String> titreCol //
              = new TableColumn<Portfolio, String>("Titre");
        
        
        TableColumn<Portfolio, String> descriptionCol //
              = new TableColumn<Portfolio, String>("Description");
        
        TableColumn<Portfolio, String> lienCol //
              = new TableColumn<Portfolio, String>("Lien");
        
        
           TableColumn actionCol = new TableColumn<>("Supprimer");
           
           
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_port"));
        idmembreCol.setCellValueFactory(new PropertyValueFactory<>("id_membre"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        lienCol.setCellValueFactory(new PropertyValueFactory<>("lien"));
        actionCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        
        
       idCol.setVisible(false);
       idmembreCol.setVisible(false);
        titreCol.setMinWidth(100);
        descriptionCol.setMinWidth(100);
        lienCol.setMinWidth(100);
         actionCol.setMinWidth(100);
      
     
        tabPorte.getColumns().addAll(idCol,idmembreCol,titreCol,descriptionCol,lienCol,actionCol);
        
        
        tabPorte.setItems(olist);
        
            titreCol.setCellFactory(TextFieldTableCell.<Portfolio> forTableColumn());
        titreCol.setOnEditCommit((TableColumn.CellEditEvent<Portfolio, String> event) -> {
            TablePosition<Portfolio, String> pos = event.getTablePosition();
 
            String newTitre = event.getNewValue();
 
            int row = pos.getRow();
            Portfolio o = event.getTableView().getItems().get(row);
 
            o.setTitre(newTitre);
            
            srv.modifierPortfolio(o);
        }); 
         descriptionCol.setCellFactory(TextFieldTableCell.<Portfolio> forTableColumn());
        descriptionCol.setOnEditCommit((TableColumn.CellEditEvent<Portfolio, String> event) -> {
            TablePosition<Portfolio, String> pos = event.getTablePosition();
 
            String newDescription = event.getNewValue();
 
            int row = pos.getRow();
            Portfolio o = event.getTableView().getItems().get(row);
 
            o.setDescription(newDescription);
            
            srv.modifierPortfolio(o);
        }); 
    
         lienCol.setCellFactory(TextFieldTableCell.<Portfolio> forTableColumn());
        lienCol.setOnEditCommit((TableColumn.CellEditEvent<Portfolio, String> event) -> {
            TablePosition<Portfolio, String> pos = event.getTablePosition();
 
            String newLien = event.getNewValue();
 
            int row = pos.getRow();
            Portfolio o = event.getTableView().getItems().get(row);
 
            o.setLien(newLien);
            
            srv.modifierPortfolio(o);
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

           
    

