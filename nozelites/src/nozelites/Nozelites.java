/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nozelites;

import entities.Membre;
import entities.Offre;
import entities.Reclamation;
import entities.chasseurTalent;
import utils.ConnexionDB;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ServiceChasseur;
import services.ServiceMembre;
import services.ServicesOffre;
import services.ServicesReclamation;

/**
 *
 * @author Nebil
 */
public class Nozelites extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
      
         
         
     Reclamation p = new Reclamation(12,2,3,"aaaaaa",0,"publication");
     
        
        ServicesReclamation srv = new ServicesReclamation();
        
         srv.ajouterReclamation(p);
     //  srv.modifierPersonne(p, "publication","deploiment");
        //JavaMail.sendMailReclamation("syrine.borjini@esprit.tn");
        srv.afficherReclamation(); 
        
        
    }
    
}
