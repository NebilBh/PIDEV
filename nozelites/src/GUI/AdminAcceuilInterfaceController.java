/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class AdminAcceuilInterfaceController implements Initializable {

    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);*/
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
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);*/
    }
    
}
