/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer.Record;
import entities.Groupe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.SGroupeMembre;
import services.SGroupe;
import entities.GroupeMembreInvite;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class AdminGroupesInterfaceController implements Initializable {

    @FXML
    private TableView<entities.Groupe> table_groupes;
    @FXML
    private TextField inpuitChercher;
    private int id_membre = 1;
    ObservableList<entities.Groupe> lss;
    @FXML
    private AnchorPane root;
    ObservableList<entities.GroupeMembreInvite> ls_invitations;//membre(nom,prenom) + groupe(titre,description)
    @FXML
    private PieChart pieChart;

    
    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("supprimer");
        
        ButtonCell()
        {
            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) 
                {
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
        protected void updateItem(Boolean t, boolean empty) 
        {
            super.updateItem(t, empty);
            if(!empty){
            setGraphic(cellButton);
            }
            else{
            setGraphic(null);
            }
        }
    }
    
    private class ButtonCell2 extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Voir");
        
        ButtonCell2()
        {
            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) 
                {//afficher groupe
                    // get Selected Item
                	Groupe currentgroupe = (Groupe) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                	//remove selected item from the table list
                	
                    try {
                        AdminGroupeInterfaceController.gr = currentgroupe;
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupeInterface.fxml"));
                        root.getChildren().setAll(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(AdminGroupesInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                        
                }
            });
        }
        
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) 
        {
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
        List<Groupe> list_g = new ArrayList<>();
        SGroupe sg = new SGroupe();
        //stat
        List<Groupe> groupes = sg.afficher_groupes();
        int groupe_fermes = 0;
        int groupe_ouverts = 0;
        for(Groupe gi : groupes)
            if(gi.getAutorisation()==0)groupe_fermes++;
            else groupe_ouverts++;
        
        
        ObservableList<PieChart.Data> pie = FXCollections.observableArrayList(
                new PieChart.Data("Ouverts",groupe_ouverts),
                new PieChart.Data("Fermés",groupe_fermes)
        );
        pieChart.setData(pie);
        //fin stat
        
        List<entities.Groupe> lsg = sg.afficher_groupes();
        for(entities.Groupe g : lsg)
        {
            System.out.println("ccc"+g);
            list_g.add(g);
        }
        //table colonnes
        TableColumn colVoir = new TableColumn("Voir");
        colVoir.setMinWidth(100);
        colVoir.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        colVoir.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell2();
            }
        
        });
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
        colsupprimer.setMinWidth(100);
        colsupprimer.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        colsupprimer.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
        //add value
        lss= FXCollections.observableArrayList(list_g);
        table_groupes.setItems( lss);
        table_groupes.getColumns().addAll(colId,colVoir, colTitre, colDescription, colEtat,colsupprimer);
        colId.setVisible(false);
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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
 
    }    

    @FXML
    private void chercher_groupe(ActionEvent event) {
        List<Groupe> list_g = new ArrayList<>();
        SGroupe sg = new SGroupe();
        List<entities.Groupe> lsg = sg.afficher_groupes();
        for(entities.Groupe g : lsg)
        {
            if(inpuitChercher.getText().equals(g.getTitre())||
                            inpuitChercher.getText().equals(""))
            list_g.add(g);
        }
        
        lss.removeAll();
        lss= FXCollections.observableArrayList(list_g);
        table_groupes.setItems( lss);
    }
    
}
