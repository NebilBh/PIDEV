/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Membre;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class MembreProfilInterfaceController implements Initializable {

    @FXML
    private ImageView imgProfil;
    @FXML
    private Label labelProfil;
    @FXML
    private Label labelDip1;
    @FXML
    private Label labelDip2;
    @FXML
    private Label labelDip3;
    @FXML
    private Label labelForm;
    @FXML
    private Label labelMail;
    @FXML
    private Label labelExp;
    @FXML
    private Button btnOffre;
    @FXML
    private Circle circle;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String path = "";
            Session s = new Session();
            System.out.println("id Session : "+s.getIdSession());
            Membre m = new Membre();
            ServiceMembre srvm = new ServiceMembre();
            
        try {
            
            // TODO
            
            
            m.setId(s.getIdSession());
            
            ResultSet res = srvm.afficherUsr(m);
            res.next();
            path = res.getString(12);
            m.setNom(res.getString("nom"));
            m.setPrenom(res.getString("prenom"));
            m.setTel(res.getInt("tel"));
            m.setAge(res.getInt("age"));
            m.setFormation(res.getString("Formation"));
            m.setExp(res.getString("Experience"));
            m.setMail(res.getString("mail"));
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelProfil.setText(m.getNom()+" "+m.getPrenom()+" "+m.getAge()+" ans");
        labelMail.setText(m.getMail());
        labelForm.setText(m.getFormation());
        labelExp.setText(m.getExp());
        Image img = new Image(path);
        //imgProfil.setImage(img);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);
        
    }    
    
}
