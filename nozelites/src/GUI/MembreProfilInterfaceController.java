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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import services.ServiceDiplome;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class MembreProfilInterfaceController implements Initializable {

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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        
        String path = "";
        Session s = new Session();
        System.out.println("id Session : "+s.getIdSession());
        Membre m = new Membre();
        ServiceMembre srvm = new ServiceMembre();
        ServiceDiplome srvD = new ServiceDiplome();
        
                    col_id.setVisible(false);
       
        try {
            ResultSet listD = srvD.afficherDiplomeUser(s.getIdSession());
            while(listD.next()){
                data.add(new Diplome(listD.getInt("id_diplome"),listD.getString("domaine"), listD.getString("organisation"))); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_diplome"));
        col_domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
        col_org.setCellValueFactory(new PropertyValueFactory<>("organisation"));
        col_supp.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            
        col_supp.setCellFactory(param -> new TableCell<Diplome, Diplome>() {
            private final Button deleteButton = new Button("Supprimer");

            @Override
            protected void updateItem(Diplome diplome, boolean empty) {
                super.updateItem(diplome, empty);

                if (diplome == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    data.remove(diplome);
                    srvD.supprimer(diplome);
                        });
            }
        });
        
        
        
        
        tableDip.setItems(null);
        tableDip.setItems(data);
            
        try {
            m.setId(s.getIdSession());
            
            ResultSet res = srvm.afficherUsr(m);
            res.next();
            path = res.getString(12);
            m.setNom(res.getString("nom"));
            m.setPrenom(res.getString("prenom"));
            m.setTel(res.getInt("tel"));
            m.setAge(res.getInt("age"));
            m.setFormation(res.getString("Formation"));
            m.setExp(res.getString("Experience"));
            m.setMail(res.getString("mail"));
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelProfil.setText(m.getNom()+" "+m.getPrenom()+" "+m.getAge()+" ans");
        labelMail.setText(m.getMail());
        labelForm.setText(m.getFormation());
        labelExp.setText(m.getExp());
        labelTel.setText(Integer.toString(m.getTel()));
        System.out.println(path);
        Image img = new Image("file:///"+path);
        //imgProfil.setImage(img);
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);
        
    }    

    @FXML
    private void ajoutDiplome(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/AjouterDiplomeInterface.fxml"));
                profilMembre.getChildren().setAll(pane);   
    }

    @FXML
    private void suppCompte(MouseEvent event) throws IOException {
        ServiceMembre srvM = new ServiceMembre();
        Membre m  = new Membre();
        Session s = new Session();
        
        m.setId(s.getIdSession());
        
        srvM.supprimer(m);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ConnectionInterface.fxml"));
        profilMembre.getChildren().setAll(pane);  
    }

    @FXML
    private void modifier(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ModifierMembreInterface.fxml"));
        profilMembre.getChildren().setAll(pane);
    }
    
}
