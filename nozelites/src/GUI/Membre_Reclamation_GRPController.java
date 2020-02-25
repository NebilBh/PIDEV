/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Reclamation;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import services.ServicesReclamation;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class Membre_Reclamation_GRPController implements Initializable {

    @FXML
    private TextArea txtdescr;
    @FXML
    private ComboBox txtselect;
    @FXML
    private Button goRECL;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private Button goToReclamation;
    @FXML
    private AnchorPane root;
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         txtselect.getItems().addAll("groupe");
    }    

    @FXML
 void ajouter(ActionEvent event) throws AWTException, MalformedURLException {

    
       
   
        String description = txtdescr.getText();
          String selecteur = txtselect.getSelectionModel().getSelectedItem().toString();
       
    
       // String typee = type.getSelectionModel().getSelectedItem().toString();
        
        
        ServicesReclamation srv  = new ServicesReclamation();
        //Reclamation r = new Reclamation(1,2,3,description,selecteur);
        Session session = new Session();
        Reclamation r =new Reclamation(session.getIdSession(),MembreGroupeInterfaceController.gr.getId(),description,selecteur);
        srv.ajouterReclamation(r);
        Notification.sendNotification("Admin vous avez recu une reclamation", "RECLAMATION ",TrayIcon.MessageType.INFO);
        
    }
     
     /*public void ajouterReclamation( Reclamation r){
        try {
            String description = txtdescr.getText();
             String selecteur = txtselect.getText();
            
           // dt = new java.util.Date();
            //sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            String Req_Add="INSERT INTO reclamation(id_Recl,id_emeteur,id_cible,description,etat,selecteur,date) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pt = c.prepareStatement(Req_Add);  
            pt.setInt(1, r.getIdRecl());
            pt.setInt(2, r.getId_emeteur());
            pt.setInt(3, r.getId_cible());
            pt.setString(4, description);
            pt.setBoolean(5, false);
            pt.setString(6, selecteur);
            pt.setString(7, r.getDate());
            pt.executeUpdate();
            
        } catch (SQLException ex) {
         Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }*/

    @FXML
    private void goRECL(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("afficher_reclamation.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void acceuil(MouseEvent event) throws IOException {
       
    }
    @FXML
    private void profil(MouseEvent event) throws IOException {
    
    }

    @FXML
    private void portfolio(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembrePortfolioAfficher_interface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void groupes(MouseEvent event) throws IOException {
       
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {
      
    }

    @FXML
    private void inbox(MouseEvent event) throws IOException {
       
    }

    @FXML
    private void deconnexion(MouseEvent event) {
    }

    @FXML
    private void goToRecl(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreReclamation.fxml"));
        root.getChildren().setAll(pane);
        
        
    }
    
    
}
