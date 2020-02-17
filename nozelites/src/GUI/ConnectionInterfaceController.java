/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class ConnectionInterfaceController implements Initializable {

    @FXML
    private TextField fieldLogin;
    @FXML
    private TextField fieldMdp;
    @FXML
    private Button cnxButton;

    @FXML
    private void connect(MouseEvent event) throws IOException, SQLException {
        
        String login = fieldLogin.getText();
        String mdp = fieldMdp.getText();
        ServiceMembre srvM = new ServiceMembre();
        Session session = new Session();
        ResultSet resultat = srvM.authen(login, mdp);
        
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/InterfacePageAcceuil.fxml"));
        Scene scene = new Scene(root);
        
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        
       
        
        boolean hasResult = false;
       
            hasResult = resultat.next();
            if(hasResult){
                session.setSession(resultat.getInt(1)); 
                System.out.println("Connexion");
                fenetre.setScene(scene);
                fenetre.show();
                 
            }
            else{
            System.out.println("Connexion echoué");
            }
       
        
    }

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}
