/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import services.ServiceFormation;
import services.ServiceMembre;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class ResultatRechercheInterfaceController implements Initializable {

    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    private VBox listUsr;
    @FXML
    private VBox vboxUsr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceMembre srvM = new ServiceMembre();
        ServiceFormation srvF = new ServiceFormation();
        String rech = srvM.getRecherche();
        System.out.println("recherche "+rech);
        
        try {
            ResultSet listUsr = srvM.RechercheNom(rech);
           
            while(listUsr.next()){
                HBox hboxUsr = new HBox();
                hboxUsr.setMaxHeight(105);
                hboxUsr.setMaxWidth(700);
                hboxUsr.setSpacing(20);
                hboxUsr.setAlignment(Pos.CENTER_LEFT);
                hboxUsr.setStyle("-fx-background-color : #e9e9e9");
                
                VBox midBox = new VBox();
                midBox.setSpacing(15);
                midBox.setPrefWidth(200);
                HBox Hformation = new HBox();
                
                ImageView imgProfil = new ImageView(new Image("/GUI/images/logo.jpg"));
                imgProfil.setFitHeight(105);
                imgProfil.setFitWidth(105);
                VBox.setMargin(hboxUsr, new Insets(10,0,0,0));
                
                Label nomPrenom = new Label(listUsr.getString("prenom")+" "+listUsr.getString("nom")+", "+listUsr.getString("age"));
                nomPrenom.setStyle("-fx-font:bold 15pt 'System';");
                
                Label formation = new Label("Formation");
                Label mail = new Label (listUsr.getString("mail"));
                mail.setPrefWidth(120);
                Label Tel = new Label(Integer.toString(listUsr.getInt("tel")));
                Tel.setPrefWidth(100);
                Button consulter = new Button("Consulter");
                HBox.setMargin(consulter,new Insets(0,0,0,20));
                
                
                ResultSet rs = srvF.afficherFormationUser(listUsr.getInt("idUsr"));
            
            while (rs.next()) {
                Label form = new Label(rs.getString("titre"));
                form.setPadding(new Insets(0,5,0,5));
                form.setAlignment(Pos.CENTER);
                form.setStyle("-fx-background-color : #e1ecf4 ;"
                        + "-fx-text-fill: #3973ab; -fx-border-radius : 5px");
                Hformation.setSpacing(5);
                Hformation.getChildren().addAll(form);
           
            }
                Circle cercle = new Circle();
                cercle.setRadius(50);
                
                cercle.setStyle("-fx-background-color : #ffffff");
                
                Image img = new Image("file:///"+listUsr.getString("image"));
        
                ImagePattern pattern = new ImagePattern(img);
                cercle.setFill(pattern);
                
                midBox.getChildren().addAll(nomPrenom,formation,Hformation);
                hboxUsr.getChildren().addAll(cercle,midBox,mail,Tel,consulter);
                vboxUsr.getChildren().addAll(hboxUsr);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultatRechercheInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void acceuil(MouseEvent event) {
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
