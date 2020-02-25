/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import entities.Portfolio;
import entities.PortfolioForGUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.ServicePortfolio;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author KHAIRI
 */
public class PORTFOLIO_DU_PROFILController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
  

    /**
     * Initializes the controller class.
     */
  
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

   
     private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Supprimer ?");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	PortfolioForGUI currentOffre = (PortfolioForGUI) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	olist.remove(currentOffre);
                        //remove from DB
                        srv.supprimerReclamationPort(currentOffre);
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
        actionCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        
        
       idCol.setVisible(false);
 
        titreCol.setMinWidth(100);
        descriptionCol.setMinWidth(100);
        lienCol.setMinWidth(100);
         actionCol.setMinWidth(100);
      
     
        projet.getColumns().addAll(idCol,titreCol,descriptionCol,lienCol,actionCol);
        
        
        projet.setItems(olist);
          actionCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
    

  });
                  }}