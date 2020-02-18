/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Portfolio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServicePortfolio;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class MembrePortfolioAjouter_interfaceController implements Initializable {

    
    
    @FXML
    private TextField txttitre;

    @FXML
    private TextArea txtdescr;

    @FXML
    private TextField txtlien;

  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
      @FXML
    void ajouter(ActionEvent event) {

    
       
        String titre = txttitre.getText();
        String description = txtdescr.getText();
        String lien = txtlien.getText();
    
       // String typee = type.getSelectionModel().getSelectedItem().toString();
        
        
        ServicePortfolio srv  = new ServicePortfolio();
        Portfolio p = new Portfolio(1,2,titre,description,lien);
        srv.ajouter(p);
        
    }}
    
    

