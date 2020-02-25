/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Membre;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import services.ServiceMembre;
import services.ServicesOffre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class ChasseurTeteAcceuilInterfaceController implements Initializable {

    @FXML
    private Label BoutonAcceuil;
    @FXML
    private Label BoutonProfil;
    @FXML
    private Label BoutonElites;
    @FXML
    private Label BoutonOffre;
    @FXML
    private Button BoutonDeco;
    @FXML
    private AnchorPane root;
    @FXML
    private Label NbrOffresAcceptees;
    @FXML
    private Label NbrOffres;
    
    ServicesOffre srvOffres = new ServicesOffre();
    @FXML
    private Circle ImageTop1;
    @FXML
    private Label NomPrenomAgeTop1;
    @FXML
    private Label ExperienceTop1;
    @FXML
    private Label NbrOffresRecusCeMoisTop1;
    @FXML
    private Label EmailTop1;
    @FXML
    private Label TelephoneTop1;
    @FXML
    private Circle ImageTop2;
    @FXML
    private Label NomPrenomAgeTop2;
    @FXML
    private Label ExperienceTop2;
    @FXML
    private Label NbrOffresRecusCeMoisTop2;
    @FXML
    private Label EmailTop2;
    @FXML
    private Label TelephoneTop2;
    @FXML
    private Circle ImageTop3;
    @FXML
    private Label NomPrenomAgeTop3;
    @FXML
    private Label ExperienceTop3;
    @FXML
    private Label NbrOffresRecusCeMoisTop3;
    @FXML
    private Label EmailTop3;
    @FXML
    private Label TelephoneTop3;
    @FXML
    private TextField searchField;
    @FXML
    private Label nbProfil;
    private int topUn , topDeux,topTrois;
    @FXML
    private Button searchDom;
    @FXML
    private TextField domField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceMembre srvM = new ServiceMembre();
        int count = srvM.nbrMembre();
        nbProfil.setText(Integer.toString(count));
        //NbrOffresAcceptees.setText(srvOffres.nbrOffresAcceptees());
        //NbrOffres.setText(srvOffres.nbrOffres());
        
        LocalDate localDate = LocalDate.now();
        List<Membre> topOfTheMonth = srvOffres.afficherTopMois(localDate.toString().substring(4,4));
        
        
        Image img = new Image("file:///"+topOfTheMonth.get(0).getImage());
        ImagePattern pattern = new ImagePattern(img);
        ImageTop1.setFill(pattern);
        NomPrenomAgeTop1.setText(topOfTheMonth.get(0).getNom()+" "+topOfTheMonth.get(0).getPrenom()+" "+topOfTheMonth.get(0).getAge()+" ans");
        NomPrenomAgeTop1.maxWidth(200);
        ExperienceTop1.setText(topOfTheMonth.get(0).getExp());
        ExperienceTop1.maxWidth(200);
        NbrOffresRecusCeMoisTop1.setText(topOfTheMonth.get(0).getType()+" Offre(s)");
        NbrOffresRecusCeMoisTop1.maxWidth(200);
        EmailTop1.setText(topOfTheMonth.get(0).getMail());
        EmailTop1.maxWidth(200);
        TelephoneTop1.setText(""+topOfTheMonth.get(0).getTel());
        TelephoneTop1.maxWidth(200);
        topUn = topOfTheMonth.get(0).getUsrId();
        
        Image img1 = new Image("file:///"+topOfTheMonth.get(1).getImage());
        ImagePattern pattern1 = new ImagePattern(img1);
        ImageTop2.setFill(pattern1);
        NomPrenomAgeTop2.setText(topOfTheMonth.get(1).getNom()+" "+topOfTheMonth.get(1).getPrenom()+" "+topOfTheMonth.get(1).getAge()+" ans");
        NomPrenomAgeTop2.maxWidth(200);
        ExperienceTop2.setText(topOfTheMonth.get(1).getExp());
        ExperienceTop2.maxWidth(200);
        NbrOffresRecusCeMoisTop2.setText(topOfTheMonth.get(1).getType()+" Offre(s)");
        NbrOffresRecusCeMoisTop2.maxWidth(200);
        EmailTop2.setText(topOfTheMonth.get(1).getMail());
        EmailTop2.maxWidth(200);
        TelephoneTop2.setText(""+topOfTheMonth.get(1).getTel());
        TelephoneTop2.maxWidth(200);
        topDeux = topOfTheMonth.get(1).getUsrId();
        
        Image img2 = new Image("file:///"+topOfTheMonth.get(2).getImage());
        ImagePattern pattern2 = new ImagePattern(img2);
        ImageTop3.setFill(pattern2);
        NomPrenomAgeTop3.setText(topOfTheMonth.get(2).getNom()+" "+topOfTheMonth.get(2).getPrenom()+" "+topOfTheMonth.get(2).getAge()+" ans");
        NomPrenomAgeTop3.maxWidth(200);
        ExperienceTop3.setText(topOfTheMonth.get(2).getExp());
        ExperienceTop3.maxWidth(200);
        NbrOffresRecusCeMoisTop3.setText(topOfTheMonth.get(2).getType()+" Offre(s)");
        NbrOffresRecusCeMoisTop3.maxWidth(200);
        EmailTop3.setText(topOfTheMonth.get(2).getMail());
        EmailTop3.maxWidth(200);
        TelephoneTop3.setText(""+topOfTheMonth.get(2).getTel());
        TelephoneTop3.maxWidth(200);
        topTrois = topOfTheMonth.get(2).getUsrId();
        
        
        
        
    }    

    @FXML
    private void BoutonOffreGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceChasseurOffre.fxml"));
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
    private void BoutonDecoGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConnectionInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void recherche(MouseEvent event) throws IOException {
        ServiceMembre srvM = new ServiceMembre();
        srvM.setRecherche(searchField.getText());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ResultatRechercheChassInterface.fxml"));

        root.getChildren().setAll(pane);
    }

    @FXML
    private void rechercheDomaine(MouseEvent event) throws IOException {
        ServiceMembre srvM = new ServiceMembre();
        srvM.setRecherche(domField.getText());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ChasseurRechercheDomaine.fxml"));

        root.getChildren().setAll(pane);
    }
   

    @FXML
    private void ConsulterUn(ActionEvent event) throws IOException {
        Session s = new Session();
        
        s.setId_select(topUn);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ChassMembreProfilVisitInterface.fxml"));
                        root.getChildren().setAll(pane); 
    }

    @FXML
    private void ConsulterDeux(ActionEvent event) throws IOException {
        Session s = new Session();
        
        s.setId_select(topDeux);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ChassMembreProfilVisitInterface.fxml"));
                        root.getChildren().setAll(pane); 
    }

    @FXML
    private void ConsulterTrois(ActionEvent event) throws IOException {
        Session s = new Session();
        
        s.setId_select(topTrois);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ChassMembreProfilVisitInterface.fxml"));
                        root.getChildren().setAll(pane); 
    }
}
