/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer.Record;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.Publication_services;
import entities.Publication_entities;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author salon2
 */
public class Interface_publication_affichageController implements Initializable {

    @FXML
    private TableView<Publication_entities> table;
    @FXML
    TableColumn<Publication_entities, String> column_titre = new TableColumn<Publication_entities,String>("Titre");
    @FXML
    TableColumn<Publication_entities, String> column_description = new TableColumn<Publication_entities,String>("Description");
    @FXML
    TableColumn<Publication_entities, String> column_id = new TableColumn<Publication_entities,String>("Id");
    @FXML
    TableColumn<Publication_entities, String> column_image = new TableColumn<Publication_entities,String>("Image");
    @FXML
    TableColumn<Publication_entities, String> column_id_groupe = new TableColumn<Publication_entities,String>("Groupe");
    @FXML
    TableColumn<Publication_entities, String> column_id_publicateur = new TableColumn<Publication_entities,String>("Publicateur");
            Publication_services srv  = new Publication_services();

    public ObservableList<Publication_entities> data = FXCollections.observableArrayList(srv.afficherPublication());
     //******************************************************************************************************************************************
    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("supprimer");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	Publication_entities currentOffre = (Publication_entities) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	data.remove(currentOffre);
                        //remove from DB
                        Publication_services srv  = new Publication_services();
                        
                        System.out.println("ccc");
                        srv.supprimerPublication(currentOffre);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                 table.getColumns().addAll(column_titre,column_description,column_id,column_image,column_id_groupe,column_id_publicateur);

    }    

    @FXML
    private void show(ActionEvent event) {
        Publication_services srv  = new Publication_services();
        
        srv.afficherPublication();
         column_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
         column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
         column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         column_image.setCellValueFactory(new PropertyValueFactory<>("image"));
         column_id_groupe.setCellValueFactory(new PropertyValueFactory<>("id_groupe"));
         column_id_publicateur.setCellValueFactory(new PropertyValueFactory<>("id_publicateur"));
         TableColumn colsupprimer = new TableColumn("Supprimer");
          
        colsupprimer.setMinWidth(100);
        colsupprimer.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        table.getColumns().addAll(colsupprimer);
        
         //data = FXCollections.observableArrayList(srv.afficherPublication());
         table.setItems(data);
         //ObservableList<Publication_entities> data ;
          // Editable
        table.setEditable(true);
        column_titre.setCellFactory(TextFieldTableCell.<entities.Publication_entities> forTableColumn());
        column_titre.setOnEditCommit( t -> {
        /*int index_row = ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getTablePosition().getRow();
        int index_col = ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getTablePosition().getColumn();
        *///String new_val = (String)((TableColumn.CellEditEvent<entities.Publication_entities, Object>) t).getNewValue();
        System.out.println("modofier");
       
        });
        column_description.setCellFactory(TextFieldTableCell.<entities.Publication_entities> forTableColumn());
        column_description.setOnEditCommit( (TableColumn.CellEditEvent<Publication_entities, String> t) -> {
/*        int index_row = ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getTablePosition().getRow();
        int index_col = ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getTablePosition().getColumn();
     */   
     //   String new_val = (String)((column_titre.getCellData().CellEditEvent<entities.Publication_entities, Object>) t).getNewValue();
       
     System.out.println("modifier");
    
     
        });
        colsupprimer.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
 
    }    
    
   
    
}
