/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeType;
import com.sbix.jnotify.NoticeWindow;
import services.Commentaire_services;
import entities.Commentaire_entities;
import entities.Publication_entities;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Publication_services;

/**
 * FXML Controller class
 *
 * @author salon2
 */
public class Interface_commentaireController implements Initializable {

    @FXML
    private TextField commentaire;
    @FXML
    private Button envoyer;
    @FXML
    private Button retourner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(Page_forumController.pub_selectione); 
    }    


    @FXML
    private void envoyer(ActionEvent event) {
        System.out.println("cccc");
        String commentaire = this.commentaire.getText();
        //String pathh = pathimage;
        String colii;
        Commentaire_services srv  = new Commentaire_services();
        Commentaire_entities p = new Commentaire_entities(0,1,Page_forumController.pub_selectione,commentaire);
        srv.ajoutercommentaire(p);
        new NoticeWindow("ce commentaire a été ajouté", NPosition.TOP_LEFT);
        new NoticeWindow(NoticeType.ERROR_NOTIFICATION,"ce commentaire a été ajouté",NoticeWindow.LONG_DELAY,NPosition.TOP_LEFT);
        
        
        
    }

    @FXML
    private void retourner(ActionEvent event) {
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
