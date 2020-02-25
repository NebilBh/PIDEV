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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServiceMembre;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class ConnectionAdminInterfaceController implements Initializable {

    @FXML
    private AnchorPane authen;
    @FXML
    private TextField fieldLogin;
    @FXML
    private PasswordField fieldMdp;
    @FXML
    private Button cnxButton;
    @FXML
    private Label erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        erreur.setVisible(false);
        
    }    


    @FXML
    private void connect(MouseEvent event) throws IOException, SQLException {
        String login = fieldLogin.getText();
        String mdp = fieldMdp.getText();
        
        ServiceMembre srvM = new ServiceMembre();
        ResultSet resultatMembre = srvM.authenAdmin(login, mdp);
        
        boolean hasResultM = false;
        hasResultM = resultatMembre.next();
        if (hasResultM){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/AdminAcceuilInterface.fxml"));
                authen.getChildren().setAll(pane); 
        }
        else{
            erreur.setVisible(true);
        }
    }

    @FXML
    private void loginMembre(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ConnectionInterface.fxml"));
                authen.getChildren().setAll(pane); 
    }
    
}
