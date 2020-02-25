/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Membre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceMembre;
import static utils.JavaMail.sendMail;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class InscriptionMembreController implements Initializable {

    @FXML
    private ImageView backLogin;
    @FXML
    private TextField labelAge;
    @FXML
    private TextField labelNom;
    @FXML
    private TextField labelPrenom;
    @FXML
    private TextField labelMail;
    @FXML
    private TextField labelNdc;
    @FXML
    private TextField labelMdp;
    @FXML
    private TextField labelExp;
    @FXML
    private Button btnCreate;
    @FXML
    private TextField labelTel;
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
        //Image img = new Image("file:///C:/Users/Nebil/Desktop/PIDEV/nozelites/src/GUI/images/logo.jpg");
        //imgProfil.setImage(img);
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
    private void createCompte(MouseEvent event) throws IOException, Exception {
        Membre m = new Membre(labelNom.getText(), labelPrenom.getText(), labelMail.getText(), labelNdc.getText(), labelMdp.getText(),
                labelExp.getText(), "0",Integer.parseInt(labelAge.getText()),Integer.parseInt(labelTel.getText()), 0, lienImg);
         ServiceMembre srvM = new ServiceMembre();
         
         srvM.ajouter(m);
         
        Random r = new Random();
        int x = r.nextInt((10001));
        Session s = new Session();
        s.setId_select(x);
        sendMail(labelMail.getText(),"Vérification","Bienvenue sur NOZELITES.\nVoici votre code de vérification : "+x+"\nA très vite!\n\nToute l'équipe NOZELITES.");

        Parent inscriptionM = FXMLLoader.load(getClass().getResource("/GUI/ValidationMail.fxml"));
        
        
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Scene scene = new Scene(inscriptionM);
        fenetre.setScene(scene);
        fenetre.show();
    }

    @FXML
    private void importer(MouseEvent event) {
        
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(//
               //
              new FileChooser.ExtensionFilter("JPG", "*.jpg"), //
              new FileChooser.ExtensionFilter("PNG", "*.png"));
        
        
        String path = fileChooser.showOpenDialog(null).getAbsolutePath();
        
        path = path.replace('\\','/');
        
        System.out.println(path);
        
        Image img = new Image("file:///"+path);
        
        imgProfil.setImage(img);
        
        lienImg = path;
        
        
        
         
        
    }
    
}
