/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nozelites;

import GUI.ParametresParDefaut;
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

import services.ServicePortfolio;

import services.ServicesMessage;

import services.ServicesOffre;

import utils.Session;

import services.ServicesReclamation;

import utils.JavaMail;
import doryan.windowsnotificationapi.fr.Notification;



/**
 *
 * @author Nebil
 */
public class Nozelites extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception {


    
       
       


<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConnectionInterface.fxml"));

//Parent root = FXMLLoader.load(getClass().getResource("/GUI/ReclamationInterface.fxml"));
   // Parent root = FXMLLoader.load(getClass().getResource("/GUI/MembrePortfolioAjouter_interface.fxml"));
   // Parent root = FXMLLoader.load(getClass().getResource("/GUI/MembrePortfolioAfficher_interface.fxml"));
    //Parent root = FXMLLoader.load(getClass().getResource("/GUI/MembrePortfolioAjouter_interface.fxml"));
=======
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/MembreAcceuilInterface.fxml"));
>>>>>>> 64063f71f7659bbbf44e2eb4cda11b7368aa3ff3

       // Parent root = FXMLLoader.load(getClass().getResource("/GUI/ResultatRechercheInterface.fxml"));

//RECLAMATION TESTE
//Parent root = FXMLLoader.load(getClass().getResource("/GUI/afficher_reclamation.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("/GUI/ReclamationPublication.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("/GUI/ReclamationEvent.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("/GUI/ConnectionInterface.fxml"));




        
      
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Nozelites.class.getResource("/GUI/css/ChartGraphique.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        launch(args);

              // ServicesReclamation srv = new ServicesReclamation();
              // Reclamation p = new Reclamation(2,3,"aaaa","groupe");
         //JavaMail.sendMailReclamation("mohamedkheireddine.bairam@esprit.tn");
         
        // srv.afficherGroupeReclamation();


        

       // Session s = new Session();
        
      //  System.out.println("id Session main"+s.getIdSession());

        
        


    }
}
