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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import services.ServiceDiplome;
import services.ServiceFormation;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class ModifierMembreInterfaceController implements Initializable {

    @FXML
    private Circle circle;
    private Label labelProfil;
    
    private Label labelForm;
    private Label labelMail;
    private Label labelExp;
    private TextField fieldNom;
    @FXML
    private TextField fieldAge;
    @FXML
    private Button btnConfirmer;
    @FXML
    private TextField fieldMail;
    @FXML
    private TextField fieldExp;
    
    private TextField fieldPrenom;
    private TextField fieldForm;
    
    @FXML
    private TextField fieldTel;
    private String lienImg;
    private String ndc;
    private String mdp;
    Session s = new Session();
    @FXML
    private AnchorPane modifWindow;
    @FXML
    private Label labelPrenom;
    @FXML
    private Label labelNom;
    @FXML
    private TextField fieldFormation;
    @FXML
    private TableView<Formation> tableForm;
    @FXML
    private TableColumn<Formation, String> formation;
    @FXML
    private TableColumn<Formation, Integer> id_formation;
    
    private ObservableList<Formation>data;
    @FXML
    private TableColumn<Formation, Formation> col_supp;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = "";
        
        System.out.println("id Session : "+s.getIdSession());
        Membre m = new Membre();
        ServiceMembre srvm = new ServiceMembre();
        ServiceDiplome srvD = new ServiceDiplome();
        ServiceFormation srvF = new ServiceFormation();
        id_formation.setVisible(false);
        data = FXCollections.observableArrayList();
            
        try {
            
            // TODO  
            m.setUsrId(s.getIdSession());
            
            ResultSet res = srvm.afficherUsr(m);
            res.next();
            path = res.getString(12);
            m.setNom(res.getString("nom"));
            m.setPrenom(res.getString("prenom"));
            m.setTel(res.getInt("tel"));
            m.setAge(res.getInt("age"));
            
            m.setExp(res.getString("Experience"));
            m.setMail(res.getString("mail"));
            m.setMdp(res.getString("mdp"));
            m.setLogin(res.getString("login"));
            this.lienImg = res.getString("image");
            
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(m.getPrenom());
        mdp = m.getMdp();
        ndc = m.getLogin();
        labelNom.setText(m.getNom());
        labelPrenom.setText(m.getPrenom());
        fieldAge.setText(Integer.toString(m.getAge()));
        fieldExp.setText(m.getExp());
        fieldMail.setText(m.getMail());
        
        fieldTel.setText(Integer.toString(m.getTel()));
        //affichage Image
        Image img = new Image("file:///"+path);
        
        
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);
        
        //----Affichage formation 
        
        
        
        try {
            ResultSet listForm = srvF.afficherFormationUser(s.getIdSession());
           
            while(listForm.next()){
                
                data.add(new Formation(listForm.getString("titre"),listForm.getInt("id_formation"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierMembreInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        id_formation.setCellValueFactory(new PropertyValueFactory<>("id_formation"));
        formation.setCellValueFactory(new PropertyValueFactory<>("titre"));
        
        col_supp.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            
        col_supp.setCellFactory(param -> new TableCell<Formation, Formation>() {
            private final Button deleteButton = new Button("Supprimer");

            @Override
            protected void updateItem(Formation form, boolean empty) {
                super.updateItem(form, empty);

                if (form == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    data.remove(form);
                    srvF.supprimer(form);
                        });
            }
        });
        
        tableForm.setItems(null);
        tableForm.setItems(data);
    }    

    @FXML
    private void importImg(MouseEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(//
               //
              new FileChooser.ExtensionFilter("JPG", "*.jpg"), //
              new FileChooser.ExtensionFilter("PNG", "*.png"));
        
        
        String path = fileChooser.showOpenDialog(null).getAbsolutePath();
        
        path = path.replace('\\','/');
        
        Image img = new Image("file:///"+path);
        
        
        ImagePattern pattern = new ImagePattern(img);
        circle.setFill(pattern);
        this.lienImg = path;
    }

    @FXML
    private void modifCompte(MouseEvent event) throws IOException {
        
        ServiceMembre srvM = new ServiceMembre();
        Membre oldMembre = new Membre();
        Membre m = new Membre(labelNom.getText(), labelPrenom.getText(), fieldMail.getText(),this.ndc,this.mdp,
                fieldExp.getText(),"0",Integer.parseInt(fieldAge.getText()),Integer.parseInt(fieldTel.getText()), 0, this.lienImg);  
        oldMembre.setUsrId(this.s.getIdSession());
        srvM.modifier(oldMembre, m);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembreProfilInterface.fxml"));
        modifWindow.getChildren().setAll(pane);
    }

    @FXML
    private void ajoutForm(MouseEvent event) {
        
        ServiceFormation srvF = new ServiceFormation();
        Formation f = new Formation();
       f.setTitre(fieldFormation.getText());
       srvF.ajouter(f,s.getIdSession());
       data = FXCollections.observableArrayList();
       try {
            ResultSet listForm = srvF.afficherFormationUser(s.getIdSession());
           
            while(listForm.next()){
                
                data.add(new Formation(listForm.getString("titre"),listForm.getInt("id_formation"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifierMembreInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        tableForm.setItems(null);
        tableForm.setItems(data);
    }

    
    
}
