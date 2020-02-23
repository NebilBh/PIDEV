/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Membre;
import entities.chasseurTalent;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import services.ServiceChasseur;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class ChasseurProfilInterfaceController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    private Label labelProfil;
    @FXML
    private Label labelMail;
    @FXML
    private Button btnSupp;
    @FXML
    private AnchorPane profilChass;
    @FXML
    private Label labelEnt;
    @FXML
    private Label labelTel;
    @FXML
    private Button btnModif;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private Label labelLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = "";
        Session s = new Session();
        System.out.println("id Session : "+s.getIdSession());
        chasseurTalent m = new chasseurTalent();
        ServiceChasseur srvC = new ServiceChasseur();
        try {
            
            // TODO
            
            
            m.setId(s.getIdSession());
            
            ResultSet res = srvC.afficherUsr(m);
            res.next();
            path = res.getString("image");
            m.setNom(res.getString("nom"));
            m.setPrenom(res.getString("prenom"));
            m.setTel(res.getInt("tel"));
            m.setAge(res.getInt("age"));
            m.setEntreprise(res.getString("entreprise"));
            m.setLogin(res.getString("login"));
            m.setMail(res.getString("mail"));
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelProfil.setText(m.getNom()+" "+m.getPrenom()+" "+m.getAge()+" ans");
        labelMail.setText(m.getMail());
        labelEnt.setText(m.getEntreprise());
        labelLogin.setText("#"+m.getLogin());
        labelTel.setText(Integer.toString(m.getTel()));
        Image img = new Image("file:///"+path);
        //imgProfil.setImage(img);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);
    }    

    @FXML
    private void suppCompte(MouseEvent event) throws IOException {
        ServiceChasseur srvM = new ServiceChasseur();
        chasseurTalent m  = new chasseurTalent();
        Session s = new Session();
        
        m.setId(s.getIdSession());
        
        srvM.supprimer(m);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ConnectionInterface.fxml"));
        profilChass.getChildren().setAll(pane); 
    }

    @FXML
    private void modifier(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ModifierChasseurInterface.fxml"));
        profilChass.getChildren().setAll(pane);
    }

    @FXML
    private void acceuil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ChasseurTeteAcceuilInterface.fxml"));
        profilChass.getChildren().setAll(pane);
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
