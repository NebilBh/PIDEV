/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evennement;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceEvennement;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class PageEvenementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    static int i;
    public  Evennement e;
    public  static Evennement ev;
     public  int id;
    @FXML
    private ImageView img_annoce;
    @FXML
    private Label lbl_titre;
    @FXML
    private Label id_annonce;
    @FXML

    private Button consulter;
    @FXML
    private Label lbl_prix;
    @FXML
    private Button likes;
    @FXML
    private Button signaler;
    @FXML
    private Button panier;
    @FXML
    private VBox vbox;
    @FXML
    private Button rejoindre;
     @FXML
    private Button demande;
    @FXML
    private Button disjoindre;
    private ServiceEvennement Service;
    @FXML
    private Button details;
    
    public int t;
   
    public int iduser;
    public double xx;
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    @FXML
    private Label lbl_id;
    @FXML
    private Label lbl_date;
     Session session = new Session();
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         id_annonce.setVisible(false);
        // TODO
      //  lignecommandeService = new LigneCommandeService();
       

                lbl_titre.setText(AllEvenementsController.obsl.get(i).getNom());
                lbl_prix.setText(AllEvenementsController.obsl.get(i).getLieu());
                lbl_id.setText(Integer.toString(AllEvenementsController.obsl.get(i).getNbParticipant()));
                lbl_date.setText(AllEvenementsController.obsl.get(i).getDate());
                t = AllEvenementsController.obsl.get(i).getIdE();
                e= AllEvenementsController.obsl.get(i);
               id=Session.getIdSession();
                String path="file:/"+AllEvenementsController.obsl.get(i).getImage();
                 ServiceEvennement sr= new ServiceEvennement();
                 if(sr.RechercherMembre(e, id)){
                    rejoindre.setVisible(false);
                    demande.setVisible(false);
                    disjoindre.setVisible(true);
                 }else{if(sr.RechercherMembre2(e, id)){
                     disjoindre.setVisible(false);     
                  demande.setVisible(false);
                  rejoindre.setVisible(true);   
                     }
                 else
                 {
                  disjoindre.setVisible(false);
                    rejoindre.setVisible(false);
                    demande.setVisible(true);
                 }
                 }
             
                rejoindre.setOnAction((event) -> {
                   
                     System.out.println(e);
                     System.out.println(e.getIdc());
                     ServiceEvennement srv= new ServiceEvennement();
                    srv.RejoindreEvenement(e,id);
                     disjoindre.setVisible(false);
                    rejoindre.setVisible(false);
                    demande.setVisible(true);
                });
                disjoindre.setOnAction((event) -> {
                    ServiceEvennement srv= new ServiceEvennement();
                   srv.DisjoindreEvenement(e,id);
                     disjoindre.setVisible(false);     
                  demande.setVisible(false);
                  rejoindre.setVisible(true);   
                });
                 demande.setOnAction((event) -> {
                    ServiceEvennement srv= new ServiceEvennement();
                   srv.DisjoindreEvenement(e,id);
                     disjoindre.setVisible(false);     
                    demande.setVisible(false);
                  rejoindre.setVisible(true);   
                });
               
                Image imag = new Image(path);
                img_annoce.setImage(imag);
//                AllEvenementsController.par.get(i);
                details.setOnAction((event) -> {
                    ev=e;
                try {
            Parent root = FXMLLoader.load(getClass().getResource("ListeTableau.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
                i++;
    }    
    
}
