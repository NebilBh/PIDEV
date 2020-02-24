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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceChasseur;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class InscriptionChasseurController implements Initializable {

    @FXML
    private ImageView backLogin;
    @FXML
    private TextField labelNom;
    @FXML
    private TextField labelPrenom;
    @FXML
    private TextField labelAge;
    @FXML
    private TextField labelMail;
    @FXML
    private TextField labelTel;
    @FXML
    private TextField labelNdc;
    @FXML
    private PasswordField labelMdp;
    private TextField labelImg;
    @FXML
    private Button btnCreate;
    @FXML
    private TextField labelEnt;
    @FXML
    private Button btnImport;
    @FXML
    private ImageView imgProfil;
    
    private String lienImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backStart(MouseEvent event) throws IOException {
        Parent inscriptionM = FXMLLoader.load(getClass().getResource("/GUI/ConnectionInterface.fxml"));
        
        
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Scene scene = new Scene(inscriptionM);
        fenetre.setScene(scene);
        fenetre.show();
    }

    @FXML
    private void createCompte(MouseEvent event) throws IOException {
        chasseurTalent m = new chasseurTalent(labelNom.getText(), labelPrenom.getText(), labelMail.getText(), labelNdc.getText(), labelMdp.getText(),
                Integer.parseInt(labelAge.getText()),Integer.parseInt(labelTel.getText()),labelEnt.getText(), lienImg);
         ServiceChasseur srvC = new ServiceChasseur();
         
         srvC.ajouter(m);
         
        Parent inscriptionM = FXMLLoader.load(getClass().getResource("/GUI/ConnectionInterface.fxml"));
        
        
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Scene scene = new Scene(inscriptionM);
        fenetre.setScene(scene);
        fenetre.show();
    }

    @FXML
    private void Importer(MouseEvent event) {
        
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(//
              
              new FileChooser.ExtensionFilter("JPG", "*.jpg"), 
              new FileChooser.ExtensionFilter("PNG", "*.png"));
        
        
        String path = fileChooser.showOpenDialog(null).getAbsolutePath();
        
        path = path.replace('\\','/');
        
        System.out.println(path);
        
        Image img = new Image("file:///"+path);
        
        imgProfil.setImage(img);
        
        lienImg = path;
    }
    
}
