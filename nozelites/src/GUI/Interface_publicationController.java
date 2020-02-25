/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import services.Publication_services;
import entities.Publication_entities;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.Favoris_services;
import entities.Favoris_entities;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ConnexionDB;
import GUI.Page_forumController;
import entities.Groupe;
import utils.Session;


/**
 * FXML Controller class
 *
 * @author salon2
 */
public class Interface_publicationController implements Initializable {

    @FXML
    private TextField txt_titre;
    @FXML
    private TextField txt_description;
    @FXML
    private Button Button_Enregistrer;
    @FXML
    private Button vider;
    @FXML
    private Button importer;
    private File file;
    private FileChooser fileChooser;
    @FXML
    private ImageView imgv;
    private String pathimage;
    @FXML
    private Button favoris;
    @FXML
    private Button retourner;
    
    public static int id_selectionne;

    Session session = new Session();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    @FXML
     public void vider(){
         System.out.println("vvvv");
        txt_description.clear();
        txt_titre.clear();
    }
    
    

    @FXML
    private void ajouter(javafx.event.ActionEvent event) throws SQLException {
        System.out.println("cccc");
        String titre = txt_titre.getText();
        String description = txt_description.getText();
        //String pathh = pathimage;
        String colii;
        Publication_services srv  = new Publication_services();
        Publication_entities p = new Publication_entities(titre,description,0,pathimage,MembreGroupeInterfaceController.gr.getId(),session.getIdSession(),0);
        srv.ajouterPublication(p);
        id_selectionne = p.getId();
        System.out.println(id_selectionne);
        
      
      
        
        
    }

    @FXML
    private void importer(javafx.event.ActionEvent event) {
        //file = fileChooser.showOpenDialog();
        //FileChooser fc = new FileChooser();
        
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        //File f =fc.showOpenDialog(null);
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(//
               //
              new FileChooser.ExtensionFilter("JPG", "*.jpg"), //
              new FileChooser.ExtensionFilter("PNG", "*.png"));
           String path = fileChooser.showOpenDialog(null).getAbsolutePath();
        
        path = path.replace('\\','/');
        
        System.out.println(path);
        Image img = new Image("file:///"+path);
        
        imgv.setImage(img);
        
        //Image img = new Image(f.getAbsolutePath());
        //if (f != null){
        //   l.setText("selected file" + f.getAbsolutePath());
           //imgv.setImage(img);
        //}
        pathimage=path;
        
   }

    @FXML
    private void favoris(javafx.event.ActionEvent event) {
        //id_selectionne = Integer.parseInt(favoris.getId());
        System.out.println("cccc");
        //String pathh = pathimage;
        String colii;
        
        Favoris_services srv  = new Favoris_services();
        Favoris_entities p = new Favoris_entities(0,id_selectionne,0);
        srv.ajouterfavoris(p);
        
    }

    @FXML
    private void retourner(javafx.event.ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Interface_publicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}


