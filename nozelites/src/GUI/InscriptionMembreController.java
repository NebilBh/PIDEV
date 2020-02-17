/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entities.Membre;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServiceMembre;

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
    private TextField labelImg;
    @FXML
    private TextField labelForm;
    @FXML
    private Button btnCreate;
    @FXML
    private TextField labelTel;

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
        Membre m = new Membre(labelNom.getText(), labelPrenom.getText(), labelMail.getText(), labelNdc.getText(), labelMdp.getText(),
                labelExp.getText(), labelForm.getText(),Integer.parseInt(labelTel.getText()),Integer.parseInt(labelAge.getText()), 0, labelImg.getText());
         ServiceMembre srvM = new ServiceMembre();
         
         srvM.ajouter(m);
         
        Parent inscriptionM = FXMLLoader.load(getClass().getResource("/GUI/ConnectionInterface.fxml"));
        
        
        Stage fenetre = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Scene scene = new Scene(inscriptionM);
        fenetre.setScene(scene);
        fenetre.show();
    }
    
}
