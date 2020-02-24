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
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import services.ServiceEvennement;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class AllEvenementsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     public static ObservableList<Evennement> obsl;
     public static ObservableList<Membre> par;
     public static int indice;
     // public static int idm;
     @FXML
    private FlowPane flow;
     @FXML
    private ImageView panier;
    
   @FXML
    private BorderPane container;

    @FXML
     private Label nombre_article;

   
    @FXML
    private Button btn_my;
    @FXML
    private Button btnall;
    
    //private ComboBox<Categorie_Annonce> cmb_cat;
    //private ISignalAnnonceService signalAnnonceService;
    @FXML
    private Button btn_liked;
    @FXML
    private Button btn_viwed;
    @FXML
    private Button btn_my1;
     private ServiceEvennement service;
     @FXML
        private Button mesev;
    @FXML
        private Button listeev;
    @FXML
        private Button ajouterev;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherCards();
    }    
     public void AfficherCards() {

         service = new ServiceEvennement();
        
       //idm=;
       PageEvenementController.i =0;
        ArrayList<Evennement> annonces = new ArrayList<>();
        annonces = (ArrayList) service.affichertout2();
        obsl = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("PageEvenement.fxml"));
                flow.getChildren().add(nodes[i]);
                
                
                ArrayList<Membre> part = new ArrayList<>();
                part= (ArrayList) service.AfficherParticipant(annonces.get(i));
                par=  FXCollections.observableArrayList(part);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
     /* panierService = new PanierService();
        int nbr=panierService.LonguerPanier();
        nombre_article.setText("("+nbr+")");*/
        
    }
}
