/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Reclamation;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ServicesReclamation;

import utils.ConnexionDB;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class MembreReclamationController implements Initializable {
  //Connection c = ConnexionDB.getInstance().getCnx();
     @FXML
    private TextArea txtdescr;

    
    @FXML
    private ComboBox txtselect;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        txtselect.getItems().addAll("groupe","membre","evenement","publication");
        // TODO
    }  
    
   
    
        @FXML
     void ajouter(ActionEvent event) throws AWTException, MalformedURLException {

    
       
     // txtselect.getItems().addAll("membre","groupe","evenement","publication");
        String description = txtdescr.getText();
          String selecteur = txtselect.getSelectionModel().getSelectedItem().toString();
       
    
       // String typee = type.getSelectionModel().getSelectedItem().toString();
        
        
        ServicesReclamation srv  = new ServicesReclamation();
        //Reclamation r = new Reclamation(1,2,3,description,selecteur);
        Session session = new Session();
        Reclamation r =new Reclamation(session.getIdSession(),3,description,selecteur);
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
    
}
