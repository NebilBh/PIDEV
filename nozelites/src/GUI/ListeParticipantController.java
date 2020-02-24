/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.ModifierEvenementController.even;
import static GUI.ModifierEvenementController.indice;
import static GUI.ModifierEvenementController.obsl;
import entities.Evennement;
import entities.Membre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import services.ServiceEvennement;
import services.ServiceMembre;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class ListeParticipantController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private FlowPane flow;
    
    private ServiceEvennement service;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherCards(even);
    }    
     public void AfficherCards(Evennement ev) {

         service = new ServiceEvennement();
       
        PageMembreEvController.i =0;
       // even = new Evennement();
        even=ev;
        indice = 0;
        Node[] nodes = new Node[AllEvenementsController.par.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("PageMembreEv.fxml"));
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
     
    }
    
}
