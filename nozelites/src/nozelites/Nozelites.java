/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nozelites;

import entities.Membre;
import entities.Message;
import entities.Offre;
import entities.Portfolio;
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
<<<<<<< HEAD
import services.ServicePortfolio;
=======
import services.ServicesMessage;
>>>>>>> 483fa0537ba6bcfb747abf68eb0d91b7b56d9b09
import services.ServicesOffre;
import services.ServicesReclamation;

/**
 *
 * @author Nebil
 */
public class Nozelites extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/afficher_reclamation.fxml"));
=======
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/AdminGroupesInterface.fxml"));
        
>>>>>>> 483fa0537ba6bcfb747abf68eb0d91b7b56d9b09
        
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(Nozelites.class.getResource("/GUI/css/ChartGraphique.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
<<<<<<< HEAD
        
      
        
        //Portfolio r =new Portfolio(1,2,"CV","attestation en microsoft","lien");
        
        //ServicePortfolio srv = new ServicePortfolio();
       //  srv.ajouter(r);*/
        //srv.afficher();
         
       
         
     
      
      
     /* Reclamation p = new Reclamation(201,2,3,"aaaaaa",0,"evenement");
         
     
     
        
        ServicesReclamation srv = new ServicesReclamation();
        
         srv.ajouterReclamation(p);
     //  srv.modifierPersonne(p, "publication","deploiment");
        //JavaMail.sendMailReclamation("syrine.borjini@esprit.tn");
        srv.afficherReclamation(); 
        */
=======
        ServicesOffre srv = new ServicesOffre();
        //Offre o = new Offre("Offre emploi",3,5,"samsung","Securite","junior","bac+3","texte here");
        //Offre o1 = new Offre(12);
        
        srv.afficherLesOffresEnvoyees(3);
        
        ServicesMessage srv1 = new ServicesMessage();
        Message m = new Message("Objet","Description",6,5);
        Message m1 = new Message(1);
        
        //srv1.afficherLesMessagesEnvoyes(4);
        
>>>>>>> 483fa0537ba6bcfb747abf68eb0d91b7b56d9b09
        
    }
    
}
