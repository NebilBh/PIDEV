/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import entities.ReclamationForGroupe;
import entities.ReclamationForGroupe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.ServicesReclamation;
/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class ReclamationGROUPEController implements Initializable {

    //@FXML
   // private TableView<?> reclg;

   
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Supprimer ?");
       
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	ReclamationForGroupe currentOffre = (ReclamationForGroupe) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentOffre);
                        
                        //remove from DB
                        srv.supprimerReclamationG(currentOffre);
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
     
    }
@FXML
    private TableView<ReclamationForGroupe> reclg = new TableView<ReclamationForGroupe>();
    
  //Id du chasseur connecté
  
     ServicesReclamation srv = new ServicesReclamation();
        ObservableList<ReclamationForGroupe> olist = FXCollections.observableArrayList(srv.afficherGroupeReclamation());

    /**
     * Initializes the controller class.
     */
        //srv.afficherGroupeReclamation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         reclg.setEditable(true);
        
      
       
        
        TableColumn<ReclamationForGroupe, String> nomCol //
              = new TableColumn<ReclamationForGroupe, String>("nom");
        
        
        TableColumn<ReclamationForGroupe, String> prenomCol //
              = new TableColumn<ReclamationForGroupe, String>("prenom");
        
        
        TableColumn<ReclamationForGroupe, String> mailCol //
              = new TableColumn<ReclamationForGroupe, String>("mail");
        
         TableColumn<ReclamationForGroupe, String> titreCol //
              = new TableColumn<ReclamationForGroupe, String>("titre groupe");
         
        TableColumn<ReclamationForGroupe, String> descriptionCol //
              = new TableColumn<ReclamationForGroupe, String>("description");
        
        TableColumn<ReclamationForGroupe, String> descriptiongCol //
              = new TableColumn<ReclamationForGroupe, String>("description groupe");
        
          TableColumn<ReclamationForGroupe, String> etatCol //
              = new TableColumn<ReclamationForGroupe, String>("etat");
          
            TableColumn<ReclamationForGroupe, String> selectCol //
              = new TableColumn<ReclamationForGroupe, String>("selecteur");
            
            TableColumn<ReclamationForGroupe, String> dateCol //
              = new TableColumn<ReclamationForGroupe, String>("date");
            
            TableColumn actionCol = new TableColumn<>("Supprimer");
            TableColumn traiterCol = new TableColumn<>("Traiter");
         
        
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptiongCol.setCellValueFactory(new PropertyValueFactory<>("descriptiong"));
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
     
        
        reclg.getColumns().addAll(nomCol,prenomCol,mailCol,titreCol,descriptionCol,descriptiongCol,etatCol,selectCol,dateCol,actionCol,traiterCol);
        
        reclg.setItems(olist);
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



