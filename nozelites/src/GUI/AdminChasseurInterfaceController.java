/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.chasseurTalent;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServiceChasseur;
/**
 * FXML Controller class
 *
 * @author Nebil
 */
public class AdminChasseurInterfaceController implements Initializable {

    @FXML
    private TableView<chasseurTalent> tableMembre;
    @FXML
    private TableColumn<chasseurTalent, Integer> col_id;
    @FXML
    private TableColumn<chasseurTalent, String> col_nom;
    @FXML
    private TableColumn<chasseurTalent, String> col_prenom;
    @FXML
    private TableColumn<chasseurTalent, String> colMail;
    @FXML
    private TableColumn<chasseurTalent, Integer> col_tel;
    @FXML
    private TableColumn<chasseurTalent, String> col_log;
    @FXML
    private TableColumn<chasseurTalent, Integer> col_age;
    @FXML
    private TableColumn<chasseurTalent, String> col_ent;
    
    @FXML
    private TableColumn<chasseurTalent, chasseurTalent> col_bloq;
    @FXML
    private TextField fieldSearch;
    @FXML
    private AnchorPane root;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<chasseurTalent>data;
        data = FXCollections.observableArrayList();
        ServiceChasseur srvD = new ServiceChasseur();
        
        fieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        //System.out.println("textfield changed from " + oldValue + " to " + newValue);

        ObservableList<chasseurTalent> dataRech = FXCollections.observableArrayList();
        dataRech.clear();

        for (int i=0;i<data.size();i++){
            if(data.get(i).getNom().contains(newValue) || data.get(i).getPrenom().contains(newValue) || Integer.toString(data.get(i).getAge()).contains(newValue) || data.get(i).getEntreprise().contains(newValue) ||data.get(i).getMail().contains(newValue) || data.get(i).getLogin().contains(newValue) || Integer.toString(data.get(i).getTel()).contains(newValue))
            {
                dataRech.add(data.get(i));
            }
        }
  
        tableMembre.setItems(dataRech);
        });

        
        
        try {
            ResultSet listD = srvD.afficher();
            while(listD.next()){
                //String nom, String prenom, String mail, String login, String mdp, String Exp, String Formation, int age, int tel, int id,int type,String image
                data.add(new chasseurTalent(listD.getString("nom"),listD.getString("prenom"), listD.getString("mail"),listD.getString("login"),listD.getString("mdp"),
                listD.getInt("age"),listD.getInt("tel"),listD.getInt(1),listD.getString("entreprise")
                ,listD.getString("image"))); 
                System.out.println(listD.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MembreProfilInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        col_log.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_ent.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("usrId"));
        col_id.setVisible(false);
        
        
        col_bloq.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        col_bloq.setCellFactory(param -> new TableCell<chasseurTalent, chasseurTalent>() {
     
            Button deleteButton = new Button("bloquer");
            
            
            
            @Override
            protected void updateItem(chasseurTalent m, boolean empty) {
                super.updateItem(m, empty);

                if (m == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> {
                    data.remove(m);
                    srvD.supprimer(m);
                    
                        });
            }
        });
        
        tableMembre.setItems(null);
        tableMembre.setItems(data);
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminMembreInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void chasseurs(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminChasseurInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void offres(MouseEvent event) {
    }

    @FXML
    private void evenements(MouseEvent event) {
    }
    
}
