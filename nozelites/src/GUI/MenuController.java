/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author salon2
 */

public class MenuController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button traitement;
    @FXML
    private Button Retourner;
    @FXML
    private Button commentaire;
    
    Session session = new Session();
    //int i = session.getIdSession();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Interface_publication.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void traitement(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("Interface_publication_affichage.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Retourner(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MembreGroupeInterface.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void commentaire(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Forum.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficher(ActionEvent event) {
           try {
            Parent root = FXMLLoader.load(getClass().getResource("Interface_favoris_affichage.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void aajouter(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Interface_publication.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void ttrait(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("Interface_publication_affichage.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fforum(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Forum.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
