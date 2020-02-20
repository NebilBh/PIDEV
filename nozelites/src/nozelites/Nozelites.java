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


/**
 *
 * @author Nebil
 */
public class Nozelites extends Application {
    
    ParametresParDefaut p = new ParametresParDefaut();
    
    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/GUI/MembreGroupesInterface.fxml"));



        
        

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Nozelites.class.getResource("/GUI/css/ChartGraphique.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        

        Session s = new Session();
        
        System.out.println("id Session main"+s.getIdSession());
        
        //srv1.afficherLesMessagesEnvoyes(4);
        
        

    }
    
}
