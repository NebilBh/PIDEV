/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import entities.ReclamationForGUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import services.ServicesReclamation;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class Afficher_reclamationController implements Initializable {
@FXML
    private TableView<ReclamationForGUI> tab_recl = new TableView<ReclamationForGUI>();
    
  //Id du chasseur connecté
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tab_recl.setEditable(true);
        
      /*  TableColumn<Reclamation, Integer> idCol //
              = new TableColumn<Reclamation, Integer>("Id");
        
        
        TableColumn<Reclamation, Integer> idmembreCol //
              = new TableColumn<Reclamation, Integer>("id_emeteur");
        
        
        TableColumn<Reclamation, String> titreCol //
              = new TableColumn<Reclamation, String>("id_cible");
        
        
        TableColumn<Reclamation, String> descriptionCol //
              = new TableColumn<Reclamation, String>("description");
        
        TableColumn<Reclamation, String> lienCol //
              = new TableColumn<Reclamation, String>("etat");
        
          TableColumn<Reclamation, String> selectCol //
              = new TableColumn<Reclamation, String>("selecteur");
          
            TableColumn<Reclamation, String> dateCol //
              = new TableColumn<Reclamation, String>("date");
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_port"));
        idmembreCol.setCellValueFactory(new PropertyValueFactory<>("id_membre"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        lienCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>("selecteur"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        tab_recl.getColumns().addAll(idCol,idmembreCol,titreCol,descriptionCol,lienCol,selectCol,dateCol);
        
        ServicesReclamation srv = new ServicesReclamation();
        ObservableList<Reclamation> olist = FXCollections.observableArrayList(srv.afficherReclamation());
        tab_recl.setItems(olist);*/
      

      TableColumn<ReclamationForGUI, Integer> idCol //
              = new TableColumn<ReclamationForGUI, Integer>("Id");
        
        
        TableColumn<ReclamationForGUI, String> nomCol //
              = new TableColumn<ReclamationForGUI, String>("nom");
        
        
        TableColumn<ReclamationForGUI, String> prenomCol //
              = new TableColumn<ReclamationForGUI, String>("prenom");
        
        
        TableColumn<ReclamationForGUI, String> mailCol //
              = new TableColumn<ReclamationForGUI, String>("mail");
        
        TableColumn<ReclamationForGUI, String> descriptionCol //
              = new TableColumn<ReclamationForGUI, String>("description");
        
          TableColumn<ReclamationForGUI, String> etatCol //
              = new TableColumn<ReclamationForGUI, String>("etat");
          
            TableColumn<ReclamationForGUI, String> selectCol //
              = new TableColumn<ReclamationForGUI, String>("selecteur");
            
            TableColumn<ReclamationForGUI, String> dateCol //
              = new TableColumn<ReclamationForGUI, String>("date");
            
            
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>("selecteur"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        
        
        idCol.setMinWidth(50);
        nomCol.setMinWidth(100);
        prenomCol.setMinWidth(100);
        mailCol.setMinWidth(100);
        descriptionCol.setMinWidth(100);
        etatCol.setMinWidth(100);
        selectCol.setMinWidth(100);
        dateCol.setMinWidth(100);
     
        
        tab_recl.getColumns().addAll(idCol,nomCol,prenomCol,mailCol,descriptionCol,etatCol,selectCol,dateCol);
        
        ServicesReclamation srv = new ServicesReclamation();
        ObservableList<ReclamationForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresEnvoyees(2));
   //Id du chasseur connecté
    //Id du chasseur connecté
    
    
    
        tab_recl.setItems(olist);
        descriptionCol.setCellFactory(TextFieldTableCell.<ReclamationForGUI> forTableColumn());
        descriptionCol.setOnEditCommit((CellEditEvent<ReclamationForGUI, String> event) -> {
            TablePosition<ReclamationForGUI, String> pos = event.getTablePosition();
 
            String newDescription = event.getNewValue();
 
            int row = pos.getRow();
            ReclamationForGUI o = event.getTableView().getItems().get(row);
 
            o.setDescription(newDescription);
            
            srv.modifierReclamationFor(o);
        }); 
           /*selectCol.setCellFactory(TextFieldTableCell.<ReclamationForGUI> forTableColumn());
        selectCol.setOnEditCommit((CellEditEvent<ReclamationForGUI, String> event) -> {
            TablePosition<ReclamationForGUI, String> pos = event.getTablePosition();
 
            String newSelecteur = event.getNewValue();
 
            int row = pos.getRow();
            ReclamationForGUI o = event.getTableView().getItems().get(row);
 
            o.setSelecteur(newSelecteur);
            
            srv.modifierReclamationFor(o);
        }); */
    
         
    }}

