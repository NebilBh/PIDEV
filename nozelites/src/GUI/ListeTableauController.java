/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evennement;
import entities.Membre;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.ServiceEvennement;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class ListeTableauController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;

    /**
     * Initializes the controller class.
     */
    @FXML
     public void MesE(ActionEvent event) {
     
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    @FXML
     public void ListeE(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AllEvenements.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    @FXML
      public void AjouterE(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    @FXML
      public void Back(ActionEvent event){
          try {
            Parent root = FXMLLoader.load(getClass().getResource("AllEvenements.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      @FXML
        private Button mesev;
    @FXML
        private Button listeev;
    @FXML
        private Button ajouterev;
    @FXML
        private Button back;
    
     @FXML
    TableView<Membre> table = new TableView<Membre>();
     TableColumn<Membre, Integer> idUsr //
              = new TableColumn<Membre, Integer>("idUsr");
      TableColumn<Membre, String> nom //
              = new TableColumn<Membre, String>("nom");
       TableColumn<Membre, String> prenom //
              = new TableColumn<Membre, String>("prenom");
       TableColumn<Membre, String> mail //
              = new TableColumn<Membre, String>("mail");
       TableColumn<Membre, String>  formation//
              = new TableColumn<Membre, String>("formation");
        TableColumn<Membre, String> Experience//
              = new TableColumn<Membre, String>("Experience");
        
    ServiceEvennement srv  = new ServiceEvennement();
    public ObservableList<Membre > list = FXCollections.observableArrayList(srv.AfficherParticipant(PageEvenementController.ev));
     //Membre(experience,formation,nom,prenom,email,idm);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         idUsr.setCellValueFactory(new PropertyValueFactory<>("idUsr"));
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
         formation.setCellValueFactory(new PropertyValueFactory<>("formation"));
         Experience.setCellValueFactory(new PropertyValueFactory<>("exp"));
         
         table.setItems(list);

        nom.setMinWidth(100);
        prenom.setMinWidth(100);
        mail.setMinWidth(160);
        formation.setMinWidth(100);
        Experience.setMinWidth(100);
        table.getColumns().addAll(nom,prenom,mail,formation,Experience);
        
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
    
}
