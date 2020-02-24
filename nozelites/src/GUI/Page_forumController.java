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
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author salon2
 */
public class Page_forumController implements Initializable {

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
    @FXML
    private VBox vbox;
    private Publication_services Service;
    
    public int t;
   
    public int iduser;
    public double xx;
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    @FXML
    private Label lbl_id;
    @FXML
    private Button commenter;

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
                
                t = ForumController.obsl.get(i).getId();
               
                String path="file:/"+ForumController.obsl.get(i).getImage();
                Publication_services sr= new Publication_services();       
               
                Image imag = new Image(path);
                img_annoce.setImage(imag);
               // System.out.println(path);
//                AllEvenementsController.par.get(i);
                
                System.out.println("i : "+i);
                
                commenter.setId(Integer.toString(ForumController.obsl.get(i).getId()));
                i++;
    }    

    @FXML
    private void commenter(ActionEvent event) {
        
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
    
    
}