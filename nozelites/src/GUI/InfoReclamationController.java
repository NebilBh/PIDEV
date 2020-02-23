/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import entities.ReclamationForGUI;
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
public class InfoReclamationController implements Initializable {

  private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Supprimer ?");
       
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	ReclamationForGUI currentOffre = (ReclamationForGUI) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentOffre);
                        
                        //remove from DB
                        srv.supprimerReclamation(currentOffre);
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
    private TableView<ReclamationForGUI> mes_recl = new TableView<ReclamationForGUI>();
    
  //Id du chasseur connecté
    @FXML
    private AnchorPane afficher;

     ServicesReclamation srv = new ServicesReclamation();
        ObservableList<ReclamationForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresEnvoyees(2));

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         mes_recl.setEditable(true);
          
        TableColumn<ReclamationForGUI, String> nomCol //
              = new TableColumn<ReclamationForGUI, String>("nom");
        
        
        TableColumn<ReclamationForGUI, String> prenomCol //
              = new TableColumn<ReclamationForGUI, String>("prenom");
        
        
        TableColumn<ReclamationForGUI, String> mailCol //
              = new TableColumn<ReclamationForGUI, String>("mail");
        
        TableColumn<ReclamationForGUI, String> descriptionCol //
              = new TableColumn<ReclamationForGUI, String>("description");
        
        
          
            TableColumn<ReclamationForGUI, String> selectCol //
              = new TableColumn<ReclamationForGUI, String>("selecteur");
            
            TableColumn<ReclamationForGUI, String> dateCol //
              = new TableColumn<ReclamationForGUI, String>("date");
            
            TableColumn actionCol = new TableColumn<>("Supprimer");
            
            
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
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
      
    
        nomCol.setMinWidth(100);
        prenomCol.setMinWidth(100);
        mailCol.setMinWidth(100);
        descriptionCol.setMinWidth(100);
       
        selectCol.setMinWidth(100);
        dateCol.setMinWidth(100);
        actionCol.setMinWidth(100);
     
        
        mes_recl.getColumns().addAll(nomCol,prenomCol,mailCol,descriptionCol,selectCol,dateCol,actionCol);
        
     //aaaaaaaaa//   ServicesReclamation srv = new ServicesReclamation();
      // aaaa/// ObservableList<ReclamationForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresEnvoyees(2));
   //Id du chasseur connecté
    //Id du chasseur connecté
    
    
    
        mes_recl.setItems(olist);
        descriptionCol.setCellFactory(TextFieldTableCell.<ReclamationForGUI> forTableColumn());
        descriptionCol.setOnEditCommit((TableColumn.CellEditEvent<ReclamationForGUI, String> event) -> {
            TablePosition<ReclamationForGUI, String> pos = event.getTablePosition();
 
            String newDescription = event.getNewValue();
 
            int row = pos.getRow();
            ReclamationForGUI o = event.getTableView().getItems().get(row);
 
            o.setDescription(newDescription);
            
            srv.modifierReclamationFor(o);
        }); 
           selectCol.setCellFactory(TextFieldTableCell.<ReclamationForGUI> forTableColumn());
        selectCol.setOnEditCommit((TableColumn.CellEditEvent<ReclamationForGUI, String> event) -> {
            TablePosition<ReclamationForGUI, String> pos = event.getTablePosition();
 
            String newSelecteur = event.getNewValue();
 
            int row = pos.getRow();
            ReclamationForGUI o = event.getTableView().getItems().get(row);
 
            o.setSelecteur(newSelecteur);
            
            srv.modifierReclamationFor(o);
        }); 
    
        
       
           
               //Adding the Button to the cell
        actionCol.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell();
            }
            
             
           
        
        
        });
        
      
           
    }
         
           
    
    }


   

 
        

        

