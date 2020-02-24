/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import services.Publication_services;
import entities.Publication_entities;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author salon2
 */
public class ForumController implements Initializable {

  public static ObservableList<Publication_entities> obsl;
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
     private Publication_services service;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       AfficherCards();
    }    
    public void AfficherCards() {

         service = new  Publication_services();
        
       //idm=;
        System.out.println(service.affichertout());
       Page_forumController.i =0;
        ArrayList<Publication_entities> annonces = new ArrayList<>();
        annonces = (ArrayList) service.affichertout();
        obsl = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("page_forum.fxml"));
                flow.getChildren().add(nodes[i]);
                
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
