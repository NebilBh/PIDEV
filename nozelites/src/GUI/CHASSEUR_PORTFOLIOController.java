/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.PortfolioForGUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServicePortfolio;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class CHASSEUR_PORTFOLIOController implements Initializable {

 
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
    

    /**
     * Initializes the controller class.
     */
     

      
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceChasseurOffre.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void BoutonDecoGo(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConnectionInterface.fxml"));
        root.getChildren().setAll(pane);
    }
    
    @FXML
    private TableView<PortfolioForGUI> projet = new TableView<PortfolioForGUI>();
    
    ServicePortfolio srv = new ServicePortfolio();
    Session session = new Session();
        
    
        ObservableList<PortfolioForGUI> olist = FXCollections.observableArrayList(srv.afficherLePORTFOLIO(session.getId_select()));
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          projet.setEditable(true);
           TableColumn<PortfolioForGUI, Integer> idCol //
             = new TableColumn<PortfolioForGUI, Integer>("Id");
        
        
    
        
        
       TableColumn<PortfolioForGUI, String> titreCol //
              = new TableColumn<PortfolioForGUI, String>("Titre");
        
        
        TableColumn<PortfolioForGUI, String> descriptionCol //
              = new TableColumn<PortfolioForGUI, String>("Description");
        
        TableColumn<PortfolioForGUI, String> lienCol //
              = new TableColumn<PortfolioForGUI, String>("Lien");
        
        
           TableColumn actionCol = new TableColumn<>("Supprimer");
           
           
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        lienCol.setCellValueFactory(new PropertyValueFactory<>("lien"));
        
        
        
       idCol.setVisible(false);
 
        titreCol.setMinWidth(100);
        descriptionCol.setMinWidth(100);
        lienCol.setMinWidth(100);
         actionCol.setVisible(false);
      
     
        projet.getColumns().addAll(idCol,titreCol,descriptionCol,lienCol,actionCol);
        
        
        projet.setItems(olist);
   
                  }}

