/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import entities.Evennement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceEvennement;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class AdminEvenementController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private void acceuil(MouseEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminAcceuilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void reclamations(MouseEvent event) {
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
    private void chasseurs(MouseEvent event) {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);*/
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminEvenement.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void offres(MouseEvent event) {
         /*AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);*/
    }

    /**
     * Initializes the controller class.
     */
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("accepter");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	Evennement currentOffre = (Evennement) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	list.remove(currentOffre);
                        ServiceEvennement srv  = new ServiceEvennement();
                        System.out.println(currentOffre.getNom());
                        srv.AccepterEvenement(currentOffre);
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
    private class ButtonCell2 extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton2 = new Button("refuser");
        
        ButtonCell2(){
            
        	//Action when the button is pressed
            cellButton2.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	Evennement currentOffre = (Evennement) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                	list.remove(currentOffre);
                        ServiceEvennement srv  = new ServiceEvennement();
                        System.out.println(currentOffre.getNom());
                        srv.RefuserEvenement(currentOffre);
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
            setGraphic(cellButton2);
            }
            else{
            setGraphic(null);
            }
        }
    }
    @FXML
    public void dmd(ActionEvent event) {
     
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminEvenement.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    @FXML
     public void ListeEv(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BackEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
        
    @FXML
    TableView<Evennement> table = new TableView<Evennement>();
  
 
      // Create column UserName (Data type of String).
    TableColumn<Evennement, Integer> idc //
              = new TableColumn<Evennement, Integer>("idc");
    TableColumn<Evennement, Integer> idE //
              = new TableColumn<Evennement, Integer>("idE");
    TableColumn<Evennement, String> nom //
              = new TableColumn<Evennement, String>("nom");
    
    TableColumn<Evennement, String> lieu //
              = new TableColumn<Evennement, String>("lieu");
     TableColumn<Evennement, String> date //
              = new TableColumn<Evennement, String>("date");
      TableColumn<Evennement, String> heure //
              = new TableColumn<Evennement, String>("heure");
      TableColumn<Evennement, String> desciption //
              = new TableColumn<Evennement, String>("desciption");
      TableColumn<Evennement, String> siteWeb //
              = new TableColumn<Evennement, String>("siteWeb");
      TableColumn<Evennement, Integer> NbParticipant //
              = new TableColumn<Evennement, Integer>("NbParticipant");
       TableColumn<Evennement, Integer> NbPlace //
              = new TableColumn<Evennement, Integer>("NbPlace");
       TableColumn actionCol = new TableColumn<>("accepter");
        TableColumn actionCol2 = new TableColumn<>("refuser");
    ServiceEvennement srv  = new ServiceEvennement();

    public ObservableList<Evennement > list = FXCollections.observableArrayList(srv.afficherdmd());
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idc.setCellValueFactory(new PropertyValueFactory<>("idc"));
        idE.setCellValueFactory(new PropertyValueFactory<>("idE"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        desciption.setCellValueFactory(new PropertyValueFactory<>("desciption"));
        siteWeb.setCellValueFactory(new PropertyValueFactory<>("siteWeb"));
        NbParticipant.setCellValueFactory(new PropertyValueFactory<>("NbParticipant"));
        NbPlace.setCellValueFactory(new PropertyValueFactory<>("NbPlace"));
        actionCol.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        ServiceEvennement srv  = new ServiceEvennement();
        
        
        nom.setMinWidth(100);
        lieu.setMinWidth(70);
        date.setMinWidth(100);
        heure.setMinWidth(100);
        desciption.setMinWidth(100);
        siteWeb.setMinWidth(100);
        NbParticipant.setMinWidth(100);
        NbPlace.setMinWidth(70);
        actionCol.setMinWidth(100);
        actionCol2.setMinWidth(100);
        //ObservableList<Evennement> list=FXCollections.observableArrayList(srv.affichertout());
        table.setItems(list);
      
        
        
        table.getColumns().addAll(nom,lieu,date,heure,desciption,siteWeb,NbParticipant,NbPlace,actionCol,actionCol2);
        
        table.setEditable(true);
       actionCol.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new AdminEvenementController.ButtonCell();
            }
        
        }
       );
       actionCol2.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new AdminEvenementController.ButtonCell2();
            }
        
        }
       );
     
      
    }    
    
}
