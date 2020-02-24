/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.AllEvenementsController.indice;
import static GUI.AllEvenementsController.obsl;
import static GUI.AllEvenementsController.par;
import com.sun.prism.impl.Disposer.Record;
import entities.Evennement;
import entities.Membre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceEvennement;
import services.ServiceMembre;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class ModifierEvenementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public void recher(ActionEvent event) {
         
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
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        ServiceEvennement srv  = new ServiceEvennement();
       
        
        list=FXCollections.observableArrayList(srv.rechercherEvent(rechercher.getText()));
        
        table.setItems(list);
  
        
   
}
    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Supprimer");
        
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
                        srv.supprimerEvennement(currentOffre);
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
     public static ObservableList<Membre> obsl;
      public static ObservableList<Membre> par;
     public static int indice;
      private ServiceEvennement service;
       public static Evennement even;
    @FXML
    TableView<Evennement> table = new TableView<Evennement>();
  
 
      // Create column UserName (Data type of String).
    @FXML
      TableColumn<Evennement, Integer> idE //
              = new TableColumn<Evennement, Integer>("idE");
    @FXML
      TableColumn<Evennement, String> nom //
              = new TableColumn<Evennement, String>("nom");
    
    @FXML
      TableColumn<Evennement, String> lieu //
              = new TableColumn<Evennement, String>("lieu");
     @FXML
      TableColumn<Evennement, String> date //
              = new TableColumn<Evennement, String>("date");
      @FXML
      TableColumn<Evennement, String> heure //
              = new TableColumn<Evennement, String>("heure");
      @FXML
      TableColumn<Evennement, String> desciption //
              = new TableColumn<Evennement, String>("desciption");
      @FXML
      TableColumn<Evennement, String> siteWeb //
              = new TableColumn<Evennement, String>("siteWeb");
      @FXML
      TableColumn<Evennement, Integer> NbParticipant //
              = new TableColumn<Evennement, Integer>("NbParticipant");
       @FXML
      TableColumn<Evennement, Integer> NbPlace //
              = new TableColumn<Evennement, Integer>("NbPlace");
       @FXML
      TableColumn actionCol = new TableColumn<>("Supprimer");
       @FXML
        private Button mesev;
    @FXML
        private Button listeev;
    @FXML
        private Button ajouterev;
    @FXML
    private TextField rechercher;
     @FXML
    private FlowPane flow;
     
 
    @FXML
     public void MesE(ActionEvent event) {
     
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ModifierEvenement.fxml"));        
            Scene scene = new Scene(root);        
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);        
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     public void ListeE(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AllEvenements.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      public void AjouterE(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddEvenement.fxml"));
            Scene scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();        
            app_stage.setScene(scene);
            app_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
        ServiceEvennement srv  = new ServiceEvennement();

    public ObservableList<Evennement > list = FXCollections.observableArrayList(srv.afficherMesEvenements(25));
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
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
        //ObservableList<Evennement> list=FXCollections.observableArrayList(srv.affichertout());
        table.setItems(list);
      
        
        
        table.getColumns().addAll(nom,lieu,date,heure,desciption,siteWeb,NbParticipant,NbPlace,actionCol);
        
        table.setEditable(true);
       lieu.setCellFactory(TextFieldTableCell.<Evennement> forTableColumn());
        lieu.setOnEditCommit((CellEditEvent<Evennement, String> event) -> {
            TablePosition<Evennement, String> pos = event.getTablePosition();
 
            String newType = event.getNewValue();
 
            int row = pos.getRow();
            Evennement o = event.getTableView().getItems().get(row);
 
            o.setLieu(newType);
            
            srv.modifierEvennement(o);
        });
        
        nom.setCellFactory(TextFieldTableCell.<Evennement> forTableColumn());
        nom.setOnEditCommit((CellEditEvent<Evennement, String> event) -> {
         TablePosition<Evennement, String> pos = event.getTablePosition();
 
            String newType = event.getNewValue();
             int row = pos.getRow();
            Evennement o = event.getTableView().getItems().get(row);
            o.setNom(newType);
             srv.modifierEvennement(o);
        });
        date.setCellFactory(TextFieldTableCell.<Evennement> forTableColumn());
        date.setOnEditCommit((CellEditEvent<Evennement, String> event) -> {
         TablePosition<Evennement, String> pos = event.getTablePosition();
 
            String newType = event.getNewValue();
             int row = pos.getRow();
            Evennement o = event.getTableView().getItems().get(row);
            o.setDate(newType);
             srv.modifierEvennement(o);
        });
        heure.setCellFactory(TextFieldTableCell.<Evennement> forTableColumn());
        heure.setOnEditCommit((CellEditEvent<Evennement, String> event) -> {
         TablePosition<Evennement, String> pos = event.getTablePosition();
 
            String newType = event.getNewValue();
             int row = pos.getRow();
            Evennement o = event.getTableView().getItems().get(row);
            o.setHeure(newType);
             srv.modifierEvennement(o);
        });
        desciption.setCellFactory(TextFieldTableCell.<Evennement> forTableColumn());
        desciption.setOnEditCommit((CellEditEvent<Evennement, String> event) -> {
         TablePosition<Evennement, String> pos = event.getTablePosition();
 
            String newType = event.getNewValue();
             int row = pos.getRow();
            Evennement o = event.getTableView().getItems().get(row);
            o.setDesciption(newType);
             srv.modifierEvennement(o);
        });
        siteWeb.setCellFactory(TextFieldTableCell.<Evennement> forTableColumn());
        siteWeb.setOnEditCommit((CellEditEvent<Evennement, String> event) -> {
         TablePosition<Evennement, String> pos = event.getTablePosition();
 
            String newType = event.getNewValue();
             int row = pos.getRow();
            Evennement o = event.getTableView().getItems().get(row);
            o.setSiteWeb(newType);
             srv.modifierEvennement(o);
        });
     
        actionCol.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
        
       setCellValue();
    }    
    public void setCellValue ()
  {
        
      table.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent event) {
              Evennement e= table.getItems().get(table.getSelectionModel().getSelectedIndex());
              AfficherCards(e);
          }
         
      });
      
  
  }
      public void AfficherCards(Evennement ev) {

         service = new ServiceEvennement();
       
        PageMembreEvController.i =0;
        ArrayList<Membre> annonces = new ArrayList<>();
        annonces = (ArrayList) service.AfficherParticipantdmd(ev);
        obsl = FXCollections.observableArrayList(annonces);
       // even = new Evennement();
        even=ev;
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
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
