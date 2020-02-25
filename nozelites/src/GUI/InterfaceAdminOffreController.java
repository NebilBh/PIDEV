/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.OffreForGUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServicesOffre;
import static utils.JavaMail.sendMail;
/**
 * FXML Controller class
 *
 * @author Wael Berrachid
 */
public class InterfaceAdminOffreController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField rechercherOffre;
    @FXML
    private TableView<OffreForGUI> tabOffres = new TableView<OffreForGUI>();
    
    ServicesOffre srv = new ServicesOffre();
    ObservableList<OffreForGUI> olist = FXCollections.observableArrayList(srv.afficherOffre());
    @FXML
    private Button sendMailAuMeilleur;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rechercherOffre.textProperty().addListener((observable, oldValue, newValue) -> {
        //System.out.println("textfield changed from " + oldValue + " to " + newValue);
        olist = FXCollections.observableArrayList(srv.afficherOffre());
        ObservableList<OffreForGUI> olistRech = FXCollections.observableArrayList();
        olistRech.clear();

        for (int i=0;i<olist.size();i++){
            if(olist.get(i).getType().contains(newValue) || olist.get(i).getEntreprise().contains(newValue) || olist.get(i).getDate().contains(newValue) || olist.get(i).getEtat().contains(newValue))
            {
                olistRech.add(olist.get(i));
            }
        }
  
        tabOffres.setItems(olistRech);
        });
        
        TableColumn<OffreForGUI, String> typeCol //
              = new TableColumn<OffreForGUI, String>("Type");
    
        TableColumn<OffreForGUI, String> entrepriseCol//
                  = new TableColumn<OffreForGUI, String>("Entreprise");
        
        TableColumn<OffreForGUI, String> dateCol //
                  = new TableColumn<OffreForGUI, String>("Date");
        
        TableColumn<OffreForGUI, String> etatCol //
                  = new TableColumn<OffreForGUI, String>("Etat");
        
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        entrepriseCol.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        typeCol.setMinWidth(100);
        entrepriseCol.setMinWidth(100);
        dateCol.setMinWidth(100);
        etatCol.setMinWidth(100);
        
        tabOffres.getColumns().addAll(typeCol,entrepriseCol,dateCol,etatCol);
        
        tabOffres.setItems(olist);
        
    }    

    @FXML
    private void acceuil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminAcceuilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void reclamations(MouseEvent event) throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminAcceuilInterface.fxml"));
        root.getChildren().setAll(pane);*/
    }

    @FXML
    private void groupes(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void membres(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminMembreInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void chasseurs(MouseEvent event) throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);*/
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);*/
    }

    @FXML
    private void offres(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceAdminOffre.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void sendMailMeilleur(ActionEvent event) throws Exception {
        sendMail(srv.getMailMeilleur(),"Félicitation","Vous êtes l'élite des élites.\nMerci d'avoir utilisé NOZELITES.\nA très vite!\n\nToute l'équipe NOZELITES.");
    }
    
}
