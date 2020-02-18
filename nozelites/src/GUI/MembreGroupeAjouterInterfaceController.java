/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import entities.Groupe;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import services.SGroupe;
import services.SGroupeMembre;
import entities.GroupeMembre;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class MembreGroupeAjouterInterfaceController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField description;
    @FXML
    private RadioButton groupe_public;
    @FXML
    private RadioButton groupe_ferme;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_retour;
    @FXML
    private AnchorPane root;
    private int id_membre = 1;
    @FXML
    private TableView<?> table_membres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        final ToggleGroup group = new ToggleGroup();

        groupe_public.setToggleGroup(group);
        groupe_public.setSelected(true);

        groupe_ferme.setToggleGroup(group);
    }    

    @FXML
    private void ajouter_groupe(ActionEvent event) throws IOException {
        int etat = 0;
        if(groupe_public.isSelected())etat=1;
        Groupe g = new Groupe(1,titre.getText(),description.getText(),etat);
        SGroupe sg = new SGroupe();
        sg.ajouter_groupe(g);
        
        GroupeMembre gm1 = new GroupeMembre(5,sg.get_last_id(),id_membre,-1,"administrateur");
        SGroupeMembre s_gm = new SGroupeMembre();
        System.out.println("ddd"+sg.get_last_id());
        s_gm.ajouter_groupe_membre(gm1);
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void retour_groupe(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);
    }
    
}
