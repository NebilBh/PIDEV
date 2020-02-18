/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import services.Publication_services;
import entities.Publication_entities;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author salon2
 */
public class Interface_publicationController implements Initializable {

    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_description;
    @FXML
    private Button Button_Enregistrer;
    @FXML
    private Button vider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    @FXML
     public void vider(){
         System.out.println("vvvv");
        txt_description.clear();
        txt_titre.clear();
    }
    
    

    @FXML
    private void ajouter(javafx.event.ActionEvent event) {
        System.out.println("cccc");
        String titre = txt_titre.getText();
        String description = txt_description.getText();
        String colii;
        Publication_services srv  = new Publication_services();
        Publication_entities p = new Publication_entities(titre,description,1,"xxxx",1,1);
        srv.ajouterPublication(p);
    }

   
}


