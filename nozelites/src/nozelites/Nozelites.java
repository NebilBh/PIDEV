/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nozelites;

import entities.Membre;
import entities.Message;
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
import services.ServicesMessage;
import services.ServicesOffre;

/**
 *
 * @author Nebil
 */
public class Nozelites extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/AdminGroupesInterface.fxml"));
        
        
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
        
        ServicesOffre srv = new ServicesOffre();
        //Offre o = new Offre("Offre emploi",3,5,"samsung","Securite","junior","bac+3","texte here");
        //Offre o1 = new Offre(12);
        
        srv.afficherLesOffresEnvoyees(3);
        
        ServicesMessage srv1 = new ServicesMessage();
        Message m = new Message("Objet","Description",6,5);
        Message m1 = new Message(1);
        
        //srv1.afficherLesMessagesEnvoyes(4);
        
        
    }
    
}
