/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import entities.Groupe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.SGroupe;
import services.SGroupeMembre;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class MembreGroupesInterfaceController implements Initializable {

    @FXML
    private TableView<entities.Groupe> table_groupes;
    @FXML
    private TextField inpuitChercher;
    private int id_membre = 1;
    ObservableList<entities.Groupe> lss;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<entities.GroupeMembreInvite> table_invitations;
    ObservableList<entities.GroupeMembreInvite> ls_invitations;//membre(nom,prenom) + groupe(titre,description)

    @FXML
    private void ajouter_groupe(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupeAjouterInterface.fxml"));
        root.getChildren().setAll(pane);
        
    }
    
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("supprimer");
        
        ButtonCell(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	Groupe currentgroupe = (Groupe) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	lss.remove(currentgroupe);
                        //remove from DB
                        SGroupe s_g = new SGroupe();
                        s_g.supprimer_groupe(currentgroupe);
                        //srv.supprimerOffre(currentOffre);
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
        final Button cellButton = new Button("accepter");
        
        ButtonCell2(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	Groupe currentgroupe = (Groupe) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                	//remove selected item from the table list
                	lss.remove(currentgroupe);
                        //remove from DB
                        SGroupe s_g = new SGroupe();
                        s_g.supprimer_groupe(currentgroupe);
                        //srv.supprimerOffre(currentOffre);
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
    
    private class ButtonCell3 extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("refuser");
        
        ButtonCell3(){
            
        	//Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                	Groupe currentgroupe = (Groupe) ButtonCell3.this.getTableView().getItems().get(ButtonCell3.this.getIndex());
                	//remove selected item from the table list
                	lss.remove(currentgroupe);
                        //remove from DB
                        SGroupe s_g = new SGroupe();
                        s_g.supprimer_groupe(currentgroupe);
                        //srv.supprimerOffre(currentOffre);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<entities.Groupe> list_g = new ArrayList<>();
        
        SGroupeMembre sgm = new SGroupeMembre();
        List<entities.GroupeMembre> list = sgm.chercher_groupes_membre_par_id(id_membre);
        SGroupe sg = new SGroupe();
        for(entities.GroupeMembre gm : list)
        {
            List<entities.Groupe> lsg = sg.chercher_groupe_par_id(gm.getId_groupe());
            for(entities.Groupe g : lsg)
            {
                System.out.println("ccc"+g);
                list_g.add(g);
            }
        }
        //table colonnes
        TableColumn colId = new TableColumn("id");
        colId.setMinWidth(100);
        colId.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, String>("id"));
        TableColumn colTitre = new TableColumn("Titre");
        colTitre.setMinWidth(100);
        colTitre.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, String>("titre"));
        TableColumn colDescription = new TableColumn("Description");
        colDescription.setMinWidth(100);
        colDescription.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, String>("description"));
        TableColumn colEtat = new TableColumn("Etat");
        colEtat.setMinWidth(100);
        colEtat.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, String>("etat"));
        TableColumn colsupprimer = new TableColumn("Supprimer");
        colEtat.setMinWidth(100);
        colsupprimer.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        //add value
        lss= FXCollections.observableArrayList(list_g);
        table_groupes.setItems( lss);
        table_groupes.getColumns().addAll(colId, colTitre, colDescription, colEtat,colsupprimer);
        
      // trie
      colId.setSortType(TableColumn.SortType.DESCENDING);
      colTitre.setSortType(TableColumn.SortType.DESCENDING);
      colDescription.setSortType(TableColumn.SortType.DESCENDING);
      colEtat.setSortable(false);
      
      // Editable
        table_groupes.setEditable(true);
        colTitre.setCellFactory(TextFieldTableCell.<entities.Groupe> forTableColumn());
        colTitre.setOnEditCommit( t -> {
        int index_row = ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getTablePosition().getRow();
        int index_col = ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getTablePosition().getColumn();
        Groupe groupe = ((TableColumn.CellEditEvent<Groupe, Object>) t).getTableView().getItems().get(index_row);
        String new_val = (String)((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getNewValue();
        //System.out.println("---"+new_val+" --"+index_col+"---");
        groupe.setTitre((String) ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getNewValue());
        //modifier
        SGroupe s_g = new SGroupe();
        s_g.modifier_groupe(groupe);
       
        });
        colDescription.setCellFactory(TextFieldTableCell.<entities.Groupe> forTableColumn());
        colDescription.setOnEditCommit( t -> {
        int index_row = ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getTablePosition().getRow();
        int index_col = ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getTablePosition().getColumn();
        Groupe groupe = ((TableColumn.CellEditEvent<Groupe, Object>) t).getTableView().getItems().get(index_row);
        String new_val = (String)((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getNewValue();
        //System.out.println("---"+new_val+" --"+index_col+"---");
        groupe.setDescription((String) ((TableColumn.CellEditEvent<entities.Groupe, Object>) t).getNewValue());
        //modifier
        SGroupe s_g = new SGroupe();
        s_g.modifier_groupe(groupe);

        });
        
        colsupprimer.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        //accepter invitation
        //table colonnes
        TableColumn colIid = new TableColumn("Id");
        colIid.setMinWidth(100);
        colIid.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, Integer>("id"));
        TableColumn colInom = new TableColumn("Nom");
        colInom.setMinWidth(100);
        colInom.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, String>("nom"));
        TableColumn colItitre = new TableColumn("Titre");
        colItitre.setMinWidth(100);
        colItitre.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, String>("titre"));
        TableColumn colIpprenom = new TableColumn("Prenom");
        colIpprenom.setMinWidth(100);
        colIpprenom.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, String>("prenom"));
        TableColumn colIdescription = new TableColumn("Description");
        colIdescription.setMinWidth(100);
        colIdescription.setCellValueFactory(
                new PropertyValueFactory<entities.Groupe, String>("description"));
        TableColumn colIaccepter = new TableColumn("Accepter");
        colIaccepter.setMinWidth(100);
        colIaccepter.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        TableColumn colIrefuser = new TableColumn("Accepter");
        colIrefuser.setMinWidth(100);
        colIrefuser.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        //add value
        List<entities.GroupeMembreInvite> list_i = new ArrayList<>();
        //--------------------------------------------------------------------------------------
        ls_invitations= FXCollections.observableArrayList(list_i);
        table_invitations.setItems(ls_invitations);
        table_invitations.getColumns().addAll(colIid,colInom,colIpprenom, colItitre, colIdescription, colIaccepter,colIrefuser);
        
        colIaccepter.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell2();
            }
        
        });
        
        colIrefuser.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell3();
            }
        
        });
        
        
        
        
        
        
        
        
        
        
        
        
        
 
    }    

    @FXML
    private void chercher_groupe(ActionEvent event) {
        
    }
    
}
