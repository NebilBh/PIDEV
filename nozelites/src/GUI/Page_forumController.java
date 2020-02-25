/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import services.Publication_services;
import entities.Publication_entities;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Commentaire_services;
import utils.Session;
/**
 * FXML Controller class
 *
 * @author salon2
 */
public class Page_forumController implements Initializable {

    static int idp;
     static int i;
    public static int pub_selectione;
    @FXML
    private ImageView img_annoce;
    @FXML
    private Label lbl_titre;
    @FXML
    private Label id_annonce;
    @FXML
    private Label lbl_prix;
    private Publication_services Service;
    public int idd=ForumController.obsl.get(i).getId();
    public int t;
   
    public int iduser;
    public double xx;
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    @FXML
    private Button commenter;
    @FXML
    private Button button_jaime;
    
    public int numjaime;
    @FXML
    private Button aff_comm;
    Commentaire_services srv;

    @FXML
    private Button signaler;
    @FXML
    private AnchorPane root;
    @FXML
    private VBox vbox;
    @FXML
    private Label lbl_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id_annonce.setVisible(false);
        // TODO
      //  lignecommandeService = new LigneCommandeService();
       

                lbl_titre.setText(ForumController.obsl.get(i).getTitre());
                lbl_prix.setText(ForumController.obsl.get(i).getDescription());
                String titre = ForumController.obsl.get(i).getTitre();
        String description = ForumController.obsl.get(i).getDescription();
        String image = ForumController.obsl.get(i).getImage();
         int nb=ForumController.obsl.get(i).getNb_jaime();
                t = ForumController.obsl.get(i).getId();
              // int idd=ForumController.obsl.get(i).getId();
                String path="file:/"+ForumController.obsl.get(i).getImage();
                Publication_services sr= new Publication_services();       
               
                Image imag = new Image(path);
                img_annoce.setImage(imag);
               // System.out.println(path);
//                AllEvenementsController.par.get(i);
                
                System.out.println("i : "+i);
                
                commenter.setId(Integer.toString(ForumController.obsl.get(i).getId()));
                
                button_jaime.setOnAction((e)->{
                    System.out.println(t);
 
        Publication_services srv  = new Publication_services();
        Publication_entities p = new Publication_entities(titre,description,t,image,1,4,nb);
        srv.ajouterjaime(p);
        System.out.println(p.getNb_jaime());
                });
                aff_comm.setOnAction((e)->{
                   idp=idd;
                System.out.println(idp);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("affichagecomm.fxml"));
                Scene scene = new Scene(root);
                Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } catch (IOException ex) {
                Logger.getLogger(Page_forumController.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
                
                i++;
    }    

    @FXML
    private void commenter(ActionEvent event) {
        /*Session s = new Session();
        s.getIdSession();*/
        pub_selectione = Integer.parseInt(commenter.getId());
       
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Interface_commentaire.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void action_jaime(ActionEvent event) {
        
        String titre = ForumController.obsl.get(i).getTitre();
        String description = ForumController.obsl.get(i).getDescription();
        String image = ForumController.obsl.get(i).getImage();
        
        Publication_services srv  = new Publication_services();
        Publication_entities p = new Publication_entities(titre,description,0,image,1,4,0);
        System.out.println(p);
        p.setId(ForumController.obsl.get(i).getId());
        p.setNb_jaime(ForumController.obsl.get(i).getNb_jaime());
        
        srv.ajouterjaime(p);
        System.out.println(p.getNb_jaime());
        
        
        
    }


   /* @FXML
    private void aff_comm(ActionEvent event) {
         try {
      
              idp=idd;
            Parent root = FXMLLoader.load(getClass().getResource("affichagecomm.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/

    @FXML
    private void jaime(MouseEvent event) 
    {
    }
    

    @FXML
    private void signaler(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Membre_Reclamation_PUB.fxml"));
        root.getChildren().setAll(pane);
    }
    
}