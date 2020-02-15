/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nozelites;

import entities.Membre;
import entities.Offre;
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
        //launch(args);
        //String nom, String prenom, String mail, String login, String mdp, String Exp, String Formation, int age, int tel, int id
        //String nom, String prenom, String mail, String login, String mdp, int age, int tel,String entreprise
        chasseurTalent c1 = new chasseurTalent("mohsen2","106","moh@gmal","moh123","mdp",26,25698,23,"CECOM");
        
        ServiceChasseur srv = new ServiceChasseur();
        
        
        //srv.ajouter(c1);
        
        srv.supprimer(c1);
        
        
        
        
    }
    
}
