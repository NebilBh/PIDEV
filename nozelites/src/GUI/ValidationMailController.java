/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class ValidationMailController implements Initializable {

    @FXML
    private AnchorPane authen;
    @FXML
    private TextField fieldLogin;
    @FXML
    private Button cnxButton;
    @FXML
    private Label erreur;
    
    ServiceMembre srvM = new ServiceMembre();
    
    Session s = new Session();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connect(MouseEvent event) throws IOException {
        //System.out.println("ffff"+s.getId_select()+" "+Integer.parseInt(fieldLogin.getText()));
        if(s.getId_select() == Integer.parseInt(fieldLogin.getText()))
        {
            srvM.activerMembre();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("ConnectionInterface.fxml"));
            authen.getChildren().setAll(pane);
        }
    }
    
}
