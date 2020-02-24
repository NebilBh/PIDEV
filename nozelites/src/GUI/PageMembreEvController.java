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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import services.ServiceDiplome;
import services.ServiceEvennement;
import services.ServiceMembre;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class PageMembreEvController implements Initializable {
   static int i;
   public int t;
   public Membre e;
   public int id;
   
    @FXML
    private Label labelProfil;
    private Label labelDip1;
    private Label labelDip2;
    private Label labelDip3;
    @FXML
    private Label labelForm;
    @FXML
    private Label labelMail;
    @FXML
    private Label labelExp;
    @FXML
    private Button btnOffre;
    @FXML
    private Circle circle;
    @FXML
    private Button btnAjouter;
    @FXML
    private AnchorPane profilMembre;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModifier;
    @FXML
    private Label labelTel;
    @FXML
    private TableView<Diplome> tableDip;
    @FXML
    private TableColumn<Diplome, String> col_domaine;
    @FXML
    private TableColumn<Diplome, String> col_org;
    
    private ObservableList<Diplome>data;
    @FXML
    private TableColumn<Diplome, Diplome> col_supp;
    @FXML
    private TableColumn<Diplome, String> col_id;
    @FXML
    private Button accepter;
    @FXML
    private Button refuser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id=ModifierEvenementController.obsl.get(i).getId();
        //id=ModifierEvenementController.idm;
         labelProfil.setText(ModifierEvenementController.obsl.get(i).getNom()+" "+ModifierEvenementController.obsl.get(i).getPrenom());
         labelMail.setText(ModifierEvenementController.obsl.get(i).getMail());
         labelForm.setText(ModifierEvenementController.obsl.get(i).getFormation());
         labelExp.setText(ModifierEvenementController.obsl.get(i).getExp());
         t=ModifierEvenementController.obsl.get(i).getId();
         e=ModifierEvenementController.obsl.get(i);
        /* Image img = new Image("file:///"+//path);
        //imgProfil.setImage(img);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);*/
        accepter.setOnAction((event) -> {
                  ServiceEvennement srv= new ServiceEvennement();
                  srv.AccepterParticipant(ModifierEvenementController.even, id); 
                try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        refuser.setOnAction((event) -> {
                  ServiceEvennement srv= new ServiceEvennement();
                  srv.RefuserParticipant(ModifierEvenementController.even, id); 
                try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        i++;
        
       
        
    }    
    
}
