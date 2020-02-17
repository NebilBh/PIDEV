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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceChasseur;
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
    private AnchorPane authen;
    @FXML
    private Button btnElite;

    @FXML
    private void connect(MouseEvent event) throws IOException, SQLException {
        
        String login = fieldLogin.getText();
        String mdp = fieldMdp.getText();
        ServiceMembre srvM = new ServiceMembre();
        ServiceChasseur srvC = new ServiceChasseur();
        Session session = new Session();
        
        
        Parent AcceuilMembre = FXMLLoader.load(getClass().getResource("/GUI/InterfacePageAcceuil.fxml"));
        Parent AcceuilChass = FXMLLoader.load(getClass().getResource("/GUI/InterfacePageAcceuil.fxml"));
        
        
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        ResultSet resultatChass = srvC.authen(login,mdp);
        ResultSet resultatMembre = srvM.authen(login, mdp);
       
        
        boolean hasResultM = false;
        boolean hasResultC = false;
       
            hasResultM = resultatMembre.next();
            hasResultC = resultatChass.next();
            if(hasResultM){
                session.setSession(resultatMembre.getInt(1)); 
                System.out.println("Connexion 1");
                Scene scene = new Scene(AcceuilMembre);
                fenetre.setScene(scene);
                fenetre.show();     
            }
            else if(hasResultC){
                session.setSession(resultatChass.getInt(1)); 
                System.out.println("Connexion 2");
                Scene scene = new Scene(AcceuilChass);
                fenetre.setScene(scene);
                fenetre.show();   
            
            }
            else{
                System.out.println("connexion echoué");
            }
       
        
    }

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createElite(MouseEvent event) throws IOException {
        Parent inscriptionM = FXMLLoader.load(getClass().getResource("/GUI/InscriptionMembre.fxml"));
        
        
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Scene scene = new Scene(inscriptionM);
        fenetre.setScene(scene);
        fenetre.show();  
    }
    
    
    
}
