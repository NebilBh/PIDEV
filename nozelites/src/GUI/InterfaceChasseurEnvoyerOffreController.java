/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.ServicesOffre;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class InterfaceChasseurEnvoyerOffreController implements Initializable {

    @FXML
    private Button submitOffre;
    @FXML
    private Button cancelOffre;
    @FXML
    private AnchorPane anchorEnvoyerOffre;
    @FXML
    private TextField entrepriseField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField posteField;
    @FXML
    private ComboBox typeField;
    @FXML
    private ComboBox domaineField;
    @FXML
    private TextField requisField;
    @FXML
    private TextArea descriptionField;
    
    ServicesOffre srv = new ServicesOffre();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeField.getItems().addAll("Offre de stage","Offre de travail");
        domaineField.getItems().addAll("Aéronautique Et Espace","Agriculture - Agroalimentaire","Artisanat","Audiovisuel, Cinéma", "Audit, Comptabilité, Gestion", "Automobile", "Banque, Assurance","Bâtiment, Travaux Publics","Biologie, Chimie, Pharmacie","Commerce, Distribution", "Communication", "Création, Métiers D'art", "Culture, Patrimoine", "Défense, Sécurité, Armée", "Documentation, Bibliothèque", "Droit", "Edition, Livre", "Enseignement", "Environnement", "Ferroviaire", "Foires, Salons Et Congrès", "Fonction Publique", "Hôtellerie, Restauration", "Humanitaire", "Immobilier", "Industrie", "Informatique, Télécoms, Web", "Journalisme", "Langues", "Marketing, Publicité", "Médical", "Mode-Textile", "Paramédical", "Propreté Et Services Associés", "Psychologie", "Ressources Humaines", "Sciences Humaines Et Sociales", "Secrétariat", "Social", "Spectacle - Métiers De La Scène", "Sport", "Tourisme", "Transport-Logistique");
        //emailField.setText(emailDuMembreSelectionné);
    }    

    @FXML
    private void submitOneOffre(ActionEvent event) throws IOException {
        
        String type = typeField.getSelectionModel().getSelectedItem().toString();
        int idEmetteur = 3; //Id du chasseur connecté
        int idRecepteur = srv.getIdMembre(emailField.getText());
        String entreprise = entrepriseField.getText();
        String domaine = domaineField.getSelectionModel().getSelectedItem().toString();
        String poste = posteField.getText();
        String requis = requisField.getText();
        String description = descriptionField.getText();
        
        Offre o = new Offre(type,idEmetteur,idRecepteur,entreprise,domaine,poste,requis,description);
        srv.ajouterOffre(o);
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceChasseurOffre.fxml"));
        anchorEnvoyerOffre.getChildren().setAll(pane);
    }

    @FXML
    private void cancelOneOffre(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceChasseurOffre.fxml"));
        anchorEnvoyerOffre.getChildren().setAll(pane);
    }
    
}
