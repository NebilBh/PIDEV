/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Favoris_entities;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.Favoris_services;


/**
 * FXML Controller class
 *
 * @author salon2
 */
public class Interface_favoris_affichageController implements Initializable {
    @FXML
    private TableView<Favoris_entities> table;
    TableColumn<Favoris_entities, Integer> column_id_fav = new TableColumn<Favoris_entities,Integer>("id_fav");
    TableColumn<Favoris_entities, Integer> column_id_pub = new TableColumn<Favoris_entities,Integer>("id_pub");
    TableColumn<Favoris_entities, Integer> column_id_membre = new TableColumn<Favoris_entities,Integer>("id_membre");
    
            Favoris_services srv  = new Favoris_services();
    public ObservableList<Favoris_entities> data = FXCollections.observableArrayList(srv.afficherfavoris());
    @FXML
    private Button retourner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
         table.getColumns().addAll(column_id_fav,column_id_pub,column_id_membre);
                  Favoris_services srv  = new Favoris_services();
        
         srv.afficherfavoris();
         column_id_fav.setMinWidth(100);
         column_id_pub.setMinWidth(100);
         column_id_membre.setMinWidth(100);
         column_id_fav.setCellValueFactory(new PropertyValueFactory<>("id_fav"));
         column_id_pub.setCellValueFactory(new PropertyValueFactory<>("id_pub"));
         column_id_membre.setCellValueFactory(new PropertyValueFactory<>("id_membre"));
         table.setItems(data);
         TableColumn colsupprimer = new TableColumn("Supprimer");
    }    

    @FXML
    private void retourner(ActionEvent event) {
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
    
}
