/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer.Record;
import entities.Reclamation;
import entities.ReclamationForGUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.ServicesReclamation;
import utils.JavaMail;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class Afficher_reclamationController implements Initializable {

    @FXML
    private TextField chercher;
    @FXML
    private Button goToReclamation;
    @FXML
    private Button goRECL;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private AnchorPane root;

    @FXML
    private void chercher(ActionEvent event) {
    }

    @FXML
    private void goToRecl(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreReclamation.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void goRECL(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("afficher_reclamation.fxml"));
        root.getChildren().setAll(pane);
    }
 @FXML
    private void acceuil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreAcceuilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void profil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreProfilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void portfolio(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembrePortfolioAfficher_interface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void groupes(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupesInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceEvenement.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void inbox(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceMembreInboxRecus.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void deconnexion(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConnectionInterface.fxml"));

        root.getChildren().setAll(pane);
        Session s = new Session();
        s.setSession(0);
    }
   /////
    
    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Supprimer ?");
       
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	ReclamationForGUI currentOffre = (ReclamationForGUI) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentOffre);
                        
                        //remove from DB
                        srv.supprimerReclamation(currentOffre);
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
            setGraphic(cellButton);
            }
            else{
            setGraphic(null);
            }
        }
    }
  
    
    

@FXML
    private TableView<ReclamationForGUI> tab_recl = new TableView<ReclamationForGUI>();
    
    
    
    
     ServicesReclamation srv = new ServicesReclamation();
     Session session = new Session();
    
        ObservableList<ReclamationForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresEnvoyees(session.getIdSession()));

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tab_recl.setEditable(true);
        
      /*  TableColumn<Reclamation, Integer> idCol //
              = new TableColumn<Reclamation, Integer>("Id");
        
        
        TableColumn<Reclamation, Integer> idmembreCol //
              = new TableColumn<Reclamation, Integer>("id_emeteur");
        
        
        TableColumn<Reclamation, String> titreCol //
              = new TableColumn<Reclamation, String>("id_cible");
        
        
        TableColumn<Reclamation, String> descriptionCol //
              = new TableColumn<Reclamation, String>("description");
        
        TableColumn<Reclamation, String> lienCol //
              = new TableColumn<Reclamation, String>("etat");
        
          TableColumn<Reclamation, String> selectCol //
              = new TableColumn<Reclamation, String>("selecteur");
          
            TableColumn<Reclamation, String> dateCol //
              = new TableColumn<Reclamation, String>("date");
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_port"));
        idmembreCol.setCellValueFactory(new PropertyValueFactory<>("id_membre"));
        titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        lienCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>("selecteur"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        tab_recl.getColumns().addAll(idCol,idmembreCol,titreCol,descriptionCol,lienCol,selectCol,dateCol);
        
        ServicesReclamation srv = new ServicesReclamation();
        ObservableList<Reclamation> olist = FXCollections.observableArrayList(srv.afficherReclamation());
        tab_recl.setItems(olist);*/
      

      TableColumn<ReclamationForGUI, Integer> idCol //
              = new TableColumn<ReclamationForGUI, Integer>("Id");
        
        
       // TableColumn<ReclamationForGUI, String> nomCol //
          //    = new TableColumn<ReclamationForGUI, String>("nom");
        
        
      //  TableColumn<ReclamationForGUI, String> prenomCol //
            //  = new TableColumn<ReclamationForGUI, String>("prenom");
        
        
       // TableColumn<ReclamationForGUI, String> mailCol //
           //   = new TableColumn<ReclamationForGUI, String>("mail");
        
        TableColumn<ReclamationForGUI, String> descriptionCol //
              = new TableColumn<ReclamationForGUI, String>("description");
        
          TableColumn<ReclamationForGUI, String> etatCol //
              = new TableColumn<ReclamationForGUI, String>("etat");
          
            TableColumn<ReclamationForGUI, String> selectCol //
              = new TableColumn<ReclamationForGUI, String>("selecteur");
            
            TableColumn<ReclamationForGUI, String> dateCol //
              = new TableColumn<ReclamationForGUI, String>("date");
            
            TableColumn actionCol = new TableColumn<>("Supprimer");
             TableColumn traiterCol = new TableColumn<>("Traiter");
            
            
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       // nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
       // prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       // mailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>("selecteur"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        actionCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
 
        });
     
        
        
        idCol.setVisible(false);
      //  nomCol.setMinWidth(100);
       // prenomCol.setMinWidth(100);
       // mailCol.setMinWidth(100);
        descriptionCol.setMinWidth(100);
        etatCol.setMinWidth(100);
        selectCol.setMinWidth(100);
        dateCol.setMinWidth(100);
        actionCol.setMinWidth(100);
     
        
        tab_recl.getColumns().addAll(idCol,descriptionCol,etatCol,selectCol,dateCol,actionCol);
        
     //aaaaaaaaa//   ServicesReclamation srv = new ServicesReclamation();
      // aaaa/// ObservableList<ReclamationForGUI> olist = FXCollections.observableArrayList(srv.afficherLesOffresEnvoyees(2));
   //Id du chasseur connecté
    //Id du chasseur connecté
    
    
    
        tab_recl.setItems(olist);
        descriptionCol.setCellFactory(TextFieldTableCell.<ReclamationForGUI> forTableColumn());
        descriptionCol.setOnEditCommit((CellEditEvent<ReclamationForGUI, String> event) -> {
            TablePosition<ReclamationForGUI, String> pos = event.getTablePosition();
 
            String newDescription = event.getNewValue();
 
            int row = pos.getRow();
            ReclamationForGUI o = event.getTableView().getItems().get(row);
 
            o.setDescription(newDescription);
            
            srv.modifierReclamationFor(o);
        }); 
           selectCol.setCellFactory(TextFieldTableCell.<ReclamationForGUI> forTableColumn());
        selectCol.setOnEditCommit((CellEditEvent<ReclamationForGUI, String> event) -> {
            TablePosition<ReclamationForGUI, String> pos = event.getTablePosition();
 
            String newSelecteur = event.getNewValue();
 
            int row = pos.getRow();
            ReclamationForGUI o = event.getTableView().getItems().get(row);
 
            o.setSelecteur(newSelecteur);
            
            srv.modifierReclamationFor(o);
        }); 
    
        
       
           
               //Adding the Button to the cell
        actionCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
            
             
           
        
            public void chercher(ActionEvent event)
                    {
              
                   //idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       // nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
       // prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       // mailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        selectCol.setCellValueFactory(new PropertyValueFactory<>("selecteur"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
              
           
        ServicesReclamation srv=new ServicesReclamation();
        
       olist= FXCollections.observableArrayList(srv.rechercher5(chercher.getText()));
        
         tab_recl.setItems(olist);
          }
        });
        
         
           
        
            
        
           
    }
         
           
    
    }


   

 
        

        
        
        
    



    

