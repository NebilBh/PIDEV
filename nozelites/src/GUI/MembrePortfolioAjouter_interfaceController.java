/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Portfolio;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import services.ServicePortfolio;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class MembrePortfolioAjouter_interfaceController implements Initializable {

    
    
    @FXML
    private TextField txttitre;

    @FXML
    private TextArea txtdescr;

    @FXML
    private TextField txtlien;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private AnchorPane root;

  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
      @FXML
    void ajouter(ActionEvent event) {

        String titre = txttitre.getText();
        String description = txtdescr.getText();
        String lien = txtlien.getText();
    
       // String typee = type.getSelectionModel().getSelectedItem().toString();
        ServicePortfolio srv  = new ServicePortfolio();
        
        Session session = new Session();
        Portfolio p = new Portfolio(session.getIdSession(),titre,description,lien);
        srv.ajouter(p);
        
    }

  @FXML
    private void acceuil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreAcceuilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void profil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreProfilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void portfolio(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembrePortfolioAfficher_interface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void groupes(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupesInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceEvenement.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void inbox(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConnectionInterface.fxml"));

        root.getChildren().setAll(pane);
        Session s = new Session();
        s.setSession(0);
    }
    @FXML
    private void voirportfolio(ActionEvent event) throws IOException {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembrePortfolioAfficher_interface.fxml"));
                root.getChildren().setAll(pane); 
    }

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembrePortfolioAjouter_interface.fxml"));
                root.getChildren().setAll(pane); 
    }
}
    
    

