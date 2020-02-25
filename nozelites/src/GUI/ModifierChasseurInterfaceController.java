/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import services.ServiceChasseur;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class ModifierChasseurInterfaceController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    private Label fieldNom;
    @FXML
    private Label fieldPrenom;
    @FXML
    private TextField fieldAge;
    @FXML
    private Button btnConfirmer;
    @FXML
    private TextField fieldEnt;
    @FXML
    private TextField fieldMail;
    @FXML
    private TextField fieldTel;
    private String mdp;
    private String ndc;
    private Session s = new Session();
    private String lienImg;
    private AnchorPane modifWindow;
    @FXML
    private Label BoutonAcceuil1;
    @FXML
    private Label BoutonProfil1;
    @FXML
    private Label BoutonElites1;
    @FXML
    private Label BoutonOffre1;
    @FXML
    private Button BoutonDeco1;
    @FXML
    private AnchorPane root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = "";
        
        System.out.println("id Session : "+s.getIdSession());
        chasseurTalent m = new chasseurTalent();
        ServiceChasseur srvm = new ServiceChasseur();
        
        /*if(!labelDip3.getText().equals("Label")){
            btnAjouter.setVisible(false);
            }*/
        
        
            
        try {
            
            // TODO
            
            
            m.setUsrId(s.getIdSession());
            
            ResultSet res = srvm.afficherUsr(m);
            res.next();
            path = res.getString("image");
            m.setNom(res.getString("nom"));
            m.setPrenom(res.getString("prenom"));
            m.setTel(res.getInt("tel"));
            m.setAge(res.getInt("age"));
            m.setEntreprise(res.getString("entreprise"));
            
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
        fieldEnt.setText(m.getEntreprise());
        fieldMail.setText(m.getMail());
        
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
        ServiceChasseur srvM = new ServiceChasseur();
       //String nom, String prenom, String mail, String login, String mdp, int age, int tel,String entreprise,String image
        chasseurTalent c = new chasseurTalent(fieldNom.getText(),fieldPrenom.getText(),fieldMail.getText(),this.ndc,this.mdp,
                Integer.parseInt(fieldAge.getText()), Integer.parseInt(fieldTel.getText()), fieldEnt.getText(), lienImg);
        chasseurTalent oldchasseur = new chasseurTalent();
        
        oldchasseur.setUsrId(s.getIdSession());
        srvM.modifier(oldchasseur, c);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ChasseurProfilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonAcceuilGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ChasseurTeteAcceuilInterface.fxml"));
        root.getChildren().setAll(pane);
        
    }

    @FXML
    private void BoutonProfilGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ChasseurProfilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonElitesGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AfficherElitesInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonOffreGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceChasseurOffre.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonDecoGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConnectionInterface.fxml"));
        root.getChildren().setAll(pane);
    }
    
}
