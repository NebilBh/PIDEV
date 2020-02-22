/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Diplome;
import entities.Membre;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import services.ServiceDiplome;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class ModifierMembreInterfaceController implements Initializable {

    @FXML
    private Circle circle;
    private Label labelProfil;
    
    private Label labelForm;
    private Label labelMail;
    private Label labelExp;
    @FXML
    private TextField fieldNom;
    @FXML
    private TextField fieldAge;
    @FXML
    private Button btnConfirmer;
    @FXML
    private TextField fieldMail;
    @FXML
    private TextField fieldExp;
    
    @FXML
    private TextField fieldPrenom;
    @FXML
    private TextField fieldForm;
    
    @FXML
    private TextField fieldTel;
    private String lienImg;
    private String ndc;
    private String mdp;
    Session s = new Session();
    @FXML
    private AnchorPane modifWindow;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = "";
        
        System.out.println("id Session : "+s.getIdSession());
        Membre m = new Membre();
        ServiceMembre srvm = new ServiceMembre();
        ServiceDiplome srvD = new ServiceDiplome();
        /*if(!labelDip3.getText().equals("Label")){
            btnAjouter.setVisible(false);
            }*/
        
        try {
            ResultSet listD = srvD.afficherDiplomeUser(s.getIdSession());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
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
            m.setMdp(res.getString("mdp"));
            m.setLogin(res.getString("login"));
            this.lienImg = res.getString("image");
            
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mdp = m.getMdp();
        ndc = m.getLogin();
        fieldNom.setText(m.getNom());
        fieldPrenom.setText(m.getPrenom());
        fieldAge.setText(Integer.toString(m.getAge()));
        fieldExp.setText(m.getExp());
        fieldMail.setText(m.getMail());
        fieldForm.setText(m.getFormation());
        fieldTel.setText(Integer.toString(m.getTel()));
        Image img = new Image("file:///"+path);
        //imgProfil.setImage(img);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);
    }    

    @FXML
    private void importImg(MouseEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(//
               //
              new FileChooser.ExtensionFilter("JPG", "*.jpg"), //
              new FileChooser.ExtensionFilter("PNG", "*.png"));
        
        
        String path = fileChooser.showOpenDialog(null).getAbsolutePath();
        
        path = path.replace('\\','/');
        
        Image img = new Image("file:///"+path);
        
        
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);
        this.lienImg = path;
    }

    @FXML
    private void modifCompte(MouseEvent event) throws IOException {
        
        ServiceMembre srvM = new ServiceMembre();
        Membre oldMembre = new Membre();
        Membre m = new Membre(fieldNom.getText(), fieldPrenom.getText(), fieldMail.getText(),this.ndc,this.mdp,
                fieldExp.getText(), fieldForm.getText(),Integer.parseInt(fieldAge.getText()),Integer.parseInt(fieldTel.getText()), 0, this.lienImg);  
        oldMembre.setId(this.s.getIdSession());
        srvM.modifier(oldMembre, m);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembreProfilInterface.fxml"));
        modifWindow.getChildren().setAll(pane);
    }

    
    
}
