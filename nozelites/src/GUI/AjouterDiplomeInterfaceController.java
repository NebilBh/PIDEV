/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Diplome;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServiceDiplome;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class AjouterDiplomeInterfaceController implements Initializable {

    @FXML
    private ImageView backLogin;
    @FXML
    private TextField labelOrg;
    @FXML
    private Button btnCreate;
    @FXML
    private TextField labelDomaine;
    @FXML
    private AnchorPane diplome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    @FXML
    private void backStart(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembreProfilInterface.fxml"));
                diplome.getChildren().setAll(pane); 
    }

    @FXML
    private void ajouter(MouseEvent event) throws IOException {
        Diplome dip = new Diplome(0,labelOrg.getText(), labelDomaine.getText());
        ServiceDiplome srvD = new ServiceDiplome();
        Session s = new Session();
        
        srvD.ajouter(dip,s.getIdSession());
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembreProfilInterface.fxml"));
                diplome.getChildren().setAll(pane); 
        
    }
    
}
