/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.Commentaire_services;
import entities.Commentaire_entities;
import entities.Favoris_entities;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.Favoris_services;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author salon2
 */
public class AffichagecommController implements Initializable {

    @FXML
    private TableView<Commentaire_entities> table;
    @FXML
    private Button retourner;
    Session session = new Session();
    
    @FXML
    TableColumn<Commentaire_entities, Integer> column_id_comm = new TableColumn<Commentaire_entities,Integer>("id_comm");
    TableColumn<Commentaire_entities, Integer> column_id_mem = new TableColumn<Commentaire_entities,Integer>("id_memb");
    TableColumn<Commentaire_entities, Integer> column_id_pub = new TableColumn<Commentaire_entities,Integer>("id_pub");
     TableColumn<Commentaire_entities, String> column_comm = new TableColumn<Commentaire_entities,String>("comm");
    Commentaire_services srv  = new Commentaire_services();
    public ObservableList<Commentaire_entities> data = FXCollections.observableArrayList(srv.affichercommentaire2(Session.getIdSession(),Page_forumController.idp));

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_id_comm.setVisible(false);
        column_id_mem.setVisible(false);
        column_id_pub.setVisible(false);
        
        System.out.println(Page_forumController.idp);
                  Commentaire_services srv  = new Commentaire_services();
        
        // srv.affichercommentaire();
         column_id_comm.setMinWidth(100);
         column_id_pub.setMinWidth(100);
         column_id_mem.setMinWidth(100);
         column_id_comm.setCellValueFactory(new PropertyValueFactory<>("id_commentaire"));
         column_id_pub.setCellValueFactory(new PropertyValueFactory<>("id_publication"));
         column_id_mem.setCellValueFactory(new PropertyValueFactory<>("id_membre"));
         column_comm.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
         System.out.println(data);
          table.getColumns().addAll(column_id_comm,column_id_mem,column_id_pub,column_comm);
         table.setItems(data);
        // TableColumn colsupprimer = new TableColumn("Supprimer");
        
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
