/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Diplome;
import entities.Formation;
import entities.Membre;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import services.ServiceDiplome;
import services.ServiceFormation;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class MembreProfilVisitInterfaceController implements Initializable {

    @FXML
    private Label labelProfil;
    private Label labelDip1;
    private Label labelDip2;
    private Label labelDip3;
    private Label labelForm;
    @FXML
    private Label labelMail;
    @FXML
    private Label labelExp;
    @FXML
    private Circle circle;
    @FXML
    private Label labelTel;
    @FXML
    private TableView<Diplome> tableDip;
    @FXML
    private TableColumn<Diplome, String> col_domaine;
    @FXML
    private TableColumn<Diplome, String> col_org;
    
   
    private TableColumn<Diplome, Diplome> col_supp;
    @FXML
    private TableColumn<Diplome, String> col_id;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private HBox listFormation;
    @FXML
    private Label labellogin;
    @FXML
    private AnchorPane root;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Diplome>data;
        data = FXCollections.observableArrayList();
        
        String path = "";
        Session s = new Session();
        System.out.println("id Session : "+s.getId_select());
        Membre m = new Membre();
        Formation f = new Formation();
        ServiceMembre srvm = new ServiceMembre();
        ServiceDiplome srvD = new ServiceDiplome();
        ServiceFormation srvF = new ServiceFormation();
        
        col_id.setVisible(false);
       // ----affichage des diplomes ----
       
        
        try {
            ResultSet listD = srvD.afficherDiplomeUser(s.getId_select());
            while(listD.next()){
                
                data.add(new Diplome(listD.getInt("id_diplome"),listD.getString("domaine"), listD.getString("organisation"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_diplome"));
        col_domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        col_org.setCellValueFactory(new PropertyValueFactory<>("organisation"));
        
        
        
        
        tableDip.setItems(null);
        tableDip.setItems(data);
         
        // ---affichage information User----
        try {
            m.setUsrId(s.getId_select());
            ResultSet res = srvm.afficherUsr(m);
            res.next();
            path = res.getString(12);
            m.setLogin(res.getString("login"));
            m.setNom(res.getString("nom"));
            m.setPrenom(res.getString("prenom"));
            m.setTel(res.getInt("tel"));
            m.setAge(res.getInt("age"));
            m.setExp(res.getString("Experience"));
            m.setMail(res.getString("mail"));
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        labellogin.setText("#"+m.getLogin());
        labelProfil.setText(m.getNom()+" "+m.getPrenom()+" "+m.getAge()+" ans");
        labelMail.setText(m.getMail());
        labelExp.setText(m.getExp());
        labelTel.setText(Integer.toString(m.getTel()));
        //--- affichage photo de profil -----
        System.out.println(path);
        Image img = new Image("file:///"+path);
        //imgProfil.setImage(img);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);
        // ----affichage des formations
        
        ResultSet rs;
        try {
            listFormation.setSpacing(5);
            rs = srvF.afficherFormationUser(s.getId_select());
            
            while (rs.next()) {
                Label formation = new Label(rs.getString("titre"));
                formation.setPadding(new Insets(0,5,0,5));
                formation.setAlignment(Pos.CENTER);
                formation.setStyle("-fx-background-color : #e1ecf4 ;"
                        + "-fx-text-fill: #3973ab; -fx-border-radius : 5px");

                listFormation.getChildren().addAll(formation); 
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    
    @FXML
    private void acceuil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembreAcceuilInterface.fxml"));
                root.getChildren().setAll(pane);  
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

    @FXML
    private void reclamer(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ReclamationInterface.fxml"));
                root.getChildren().setAll(pane); 
                
    }

    @FXML
    private void voirPortfolio(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembreAcceuilInterface.fxml"));
                root.getChildren().setAll(pane); 
    }
    
}
