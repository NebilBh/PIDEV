/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import entities.Publication_entities;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.Publication_services;

/**
 * FXML Controller class
 *
 * @author salon2
 */
public class AdminPublicationAfficherInterfaceController implements Initializable {

    @FXML
    private TableView<Publication_entities> table;
    @FXML
    private TextField rechercher;
    @FXML
    private Button retourner;
    
    TableColumn<Publication_entities, String> column_titre = new TableColumn<Publication_entities,String>("Titre");
    TableColumn<Publication_entities, String> column_description = new TableColumn<Publication_entities,String>("Description");
    //TableColumn<Publication_entities, String> column_id = new TableColumn<Publication_entities,String>("Id");
    TableColumn column_image = new TableColumn<Publication_entities,String>("Image");
   // TableColumn<Publication_entities, String> column_id_groupe = new TableColumn<Publication_entities,String>("Groupe");
    //TableColumn<Publication_entities, String> column_id_publicateur = new TableColumn<Publication_entities,String>("Publicateur");
    //TableColumn<Publication_entities, Image> column_image_view = new TableColumn<Publication_entities,Image>("Image");
            Publication_services srv  = new Publication_services();
            
    public ObservableList<Publication_entities> data = FXCollections.observableArrayList(srv.afficherPublication());
    private String path;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void retourner(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminPublicationInterface.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //******************************************************************************************************************************************
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
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
    
    private class ButtonCell2 extends TableCell<Disposer.Record, Boolean> {
            final Button cellButton = new Button("cliquer pour afficher l'image");
            private String path = "";
            Publication_entities pb ;
        
        ButtonCell2(){
                
        	
                cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    
                	Publication_entities currentOffre = (Publication_entities) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                	//remove selected item from the table list
                        //System.out.println("ccc"+currentOffre.getImage());
                	//pb=currentOffre;
                        path=currentOffre.getImage();
                        path = path.replace('\\','/');
                        System.out.println(path);
                        final ImageView imageView = new ImageView(new Image("file:///"+path));   
                        imageView.setFitHeight(90);
                        imageView.setFitWidth(90);
                        cellButton.setGraphic(imageView);
                        
                }
            });
                //cellButton.fire();
            
            
            
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
       
                 table.getColumns().addAll(column_titre,column_description,column_image);
                  Publication_services srv  = new Publication_services();
              
         /*Image image = new Image(column_image.getText());
         column_image_view.setImage(image);*/
       /*  path="file:/"+column_image.getText();
         final ImageView imagee = new ImageView(path);
         column_image.setImage(imagee);
         table.set;
                  */
       
        
        
        
            
        
        srv.afficherPublication();
         column_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
         column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        // column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        column_image.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
           
        column_image.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell2();
            }
        
        });
        // column_id_groupe.setCellValueFactory(new PropertyValueFactory<>("id_groupe"));
        // column_id_publicateur.setCellValueFactory(new PropertyValueFactory<>("id_publicateur"));
        // column_image_view.setCellValueFactory(new PropertyValueFactory<>("image_view"));
        column_image.setMaxWidth(130);
        column_image.setMinWidth(130);
        TableColumn colsupprimer = new TableColumn("Supprimer");
          
        colsupprimer.setMinWidth(100);
        colsupprimer.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
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
        column_titre.setOnEditCommit((TableColumn.CellEditEvent<Publication_entities, String> t) -> {
         TablePosition<Publication_entities, String> pos = t.getTablePosition();
 
            String newType = t.getNewValue();
             int row = pos.getRow();
            Publication_entities o = t.getTableView().getItems().get(row);
            o.setTitre(newType);
             srv.modifierPublication3(o);
       
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
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });

    }    

    @FXML
    private void show(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
 
    }
    @FXML
    public void recher(ActionEvent event) {
         
         column_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        column_description.setCellValueFactory(new PropertyValueFactory<>("description"));
       // column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        //column_image.setCellValueFactory(new PropertyValueFactory<>("image"));
       // column_id_groupe.setCellValueFactory(new PropertyValueFactory<>("id_groupe"));
       // column_id_publicateur.setCellValueFactory(new PropertyValueFactory<>("id_publicateur"));
        
   
        Publication_services srv  = new Publication_services();
       
        
        data=FXCollections.observableArrayList(srv.rechercher5(rechercher.getText()));
        
        table.setItems(data);
  

   
}    
    
   
}
