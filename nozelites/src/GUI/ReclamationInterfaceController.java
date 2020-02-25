/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class ReclamationInterfaceController implements Initializable {

    private AnchorPane menuRecl;
    @FXML
    private Button goToReclamation;
    @FXML
    private Button goRECL;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        // TODO
    }    

    @FXML
    private void goToRecl(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreReclamation.fxml"));
        root.getChildren().setAll(pane);
    }
    
     @FXML
    private void goRECL(ActionEvent event) throws IOException{
 
    AnchorPane pane = FXMLLoader.load(getClass().getResource("afficher_reclamation.fxml"));
        root.getChildren().setAll(pane);
        
        
        
    }

    @FXML
    private void acceuil(MouseEvent event) {
    }

    @FXML
    private void profil(MouseEvent event) {
    }

    @FXML
    private void portfolio(MouseEvent event) {
    }

    @FXML
    private void groupes(MouseEvent event) {
    }

    @FXML
    private void evenements(MouseEvent event) {
    }

    @FXML
    private void inbox(MouseEvent event) {
    }

    @FXML
    private void deconnexion(MouseEvent event) {
    }

    
    
}
