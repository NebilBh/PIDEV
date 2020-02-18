/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Portfolio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServicePortfolio;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class MembrePortfolioAfficher_interfaceController implements Initializable {

    @FXML
    private TableView<Portfolio> tabPortefolio = new TableView<Portfolio>();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        TableColumn<Portfolio, Integer> idCol //
              = new TableColumn<Portfolio, Integer>("Id");
        
        
        TableColumn<Portfolio, Integer> idmembreCol //
              = new TableColumn<Portfolio, Integer>("Id Membre");
        
        
        TableColumn<Portfolio, String> titreCol //
              = new TableColumn<Portfolio, String>("Titre");
        
        
        TableColumn<Portfolio, String> descriptionCol //
              = new TableColumn<Portfolio, String>("Description");
        
        TableColumn<Portfolio, String> lienCol //
              = new TableColumn<Portfolio, String>("Lien");
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_port"));
        idmembreCol.setCellValueFactory(new PropertyValueFactory<>("id_membre"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        lienCol.setCellValueFactory(new PropertyValueFactory<>("lien"));
        
        tabPortefolio.getColumns().addAll(idCol,idmembreCol,titreCol,descriptionCol,lienCol);
        
        ServicePortfolio srv = new ServicePortfolio();
        ObservableList<Portfolio> olist = FXCollections.observableArrayList(srv.afficher());
        tabPortefolio.setItems(olist);
    }    
    
}
