/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Membre;
import entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.ServicesOffre;
import utils.Session;

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
    Session session = new Session();
    @FXML
    private Label EmailTop1;
    @FXML
    private Label FormationTop1;
    @FXML
    private Label ExperienceTop1;
    @FXML
    private Label NbrOffresRecusCeMoisTop1;
    @FXML
    private Label EmailTop2;
    @FXML
    private Label FormationTop2;
    @FXML
    private Label ExperienceTop2;
    @FXML
    private Label NbrOffresRecusCeMoisTop2;
    @FXML
    private Label EmailTop3;
    @FXML
    private Label FormationTop3;
    @FXML
    private Label ExperienceTop3;
    @FXML
    private Label NbrOffresRecusCeMoisTop3;
    
    private String domaineElite = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeField.getItems().addAll("Offre de stage","Offre de travail");
        domaineField.getItems().addAll("Aéronautique Et Espace","Agriculture - Agroalimentaire","Artisanat","Audiovisuel, Cinéma", "Audit, Comptabilité, Gestion", "Automobile", "Banque, Assurance","Bâtiment, Travaux Publics","Biologie, Chimie, Pharmacie","Commerce, Distribution", "Communication", "Création, Métiers D'art", "Culture, Patrimoine", "Défense, Sécurité, Armée", "Documentation, Bibliothèque", "Droit", "Edition, Livre", "Enseignement", "Environnement", "Ferroviaire", "Foires, Salons Et Congrès", "Fonction Publique", "Hôtellerie, Restauration", "Humanitaire", "Immobilier", "Industrie", "Informatique, Télécoms, Web", "Journalisme", "Langues", "Marketing, Publicité", "Médical", "Mode-Textile", "Paramédical", "Propreté Et Services Associés", "Psychologie", "Ressources Humaines", "Sciences Humaines Et Sociales", "Secrétariat", "Social", "Spectacle - Métiers De La Scène", "Sport", "Tourisme", "Transport-Logistique");
        emailField.setText(srv.getMailMembre(session.getId_select()));
        
        boolean isMyComboBoxEmpty = (domaineField.getValue() == null);
        if(isMyComboBoxEmpty)
        {
            domaineElite = "";  
        }
        
        domaineField.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue)->{
           //System.out.println(newValue);
           domaineElite = newValue.toString();
           
           List<Membre> topDomaine = srv.afficherTopDomaine(domaineElite);
           
           EmailTop1.setText("");
           FormationTop1.setText("");
           ExperienceTop1.setText("");
           NbrOffresRecusCeMoisTop1.setText("");
           
           EmailTop2.setText("");
           FormationTop2.setText("");
           ExperienceTop2.setText("");
           NbrOffresRecusCeMoisTop2.setText("");
           
           EmailTop2.setText("");
           FormationTop2.setText("");
           ExperienceTop2.setText("");
           NbrOffresRecusCeMoisTop2.setText("");
           
            if(topDomaine.size()>0){
                EmailTop1.setText(topDomaine.get(0).getMail());
                FormationTop1.setText(topDomaine.get(0).getFormation());
                ExperienceTop1.setText(topDomaine.get(0).getExp());
                NbrOffresRecusCeMoisTop1.setText(topDomaine.get(0).getType()+" Offre(s)");
                
                if(topDomaine.size()>1){
                    EmailTop2.setText(topDomaine.get(1).getMail());
                    FormationTop2.setText(topDomaine.get(1).getFormation());
                    ExperienceTop2.setText(topDomaine.get(1).getExp());
                    NbrOffresRecusCeMoisTop2.setText(topDomaine.get(1).getType()+" Offre(s)");
                    
                    if(topDomaine.size()>2){
                        EmailTop2.setText(topDomaine.get(2).getMail());
                        FormationTop2.setText(topDomaine.get(2).getFormation());
                        ExperienceTop2.setText(topDomaine.get(2).getExp());
                        NbrOffresRecusCeMoisTop2.setText(topDomaine.get(2).getType()+" Offre(s)");
                    }
                }
            }
        }
        ); 
    }    

    @FXML
    private void submitOneOffre(ActionEvent event) throws IOException {
        
        String type = typeField.getSelectionModel().getSelectedItem().toString();
        int idEmetteur = session.getIdSession(); //Id du chasseur connecté
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
