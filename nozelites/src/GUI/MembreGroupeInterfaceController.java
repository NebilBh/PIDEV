/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import entities.Groupe;
import entities.GroupeMembre;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.SGroupe;
import services.SGroupeMembre;
import entities.Membre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceMembre;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class MembreGroupeInterfaceController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label etat;
    
    public static Groupe gr ;
    public static int id_groupe = 1;
    @FXML
    private AnchorPane root;
    
    
    ObservableList<Membre> lss;
    @FXML
    private TableView<Membre> table_membres;
    @FXML
    private TableView<entities.Publication_entities> tables_publications;
    
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Bloquer");
        
        ButtonCell()
        {
            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) 
                {
                    // get Selected Item
                	Membre currentmembre = (Membre) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                	//remove selected item from the table list
                	lss.remove(currentmembre);
                        //bloquer membre
                        GroupeMembre gm = new GroupeMembre(id_groupe,2,1,3,"bloqué");
                        SGroupeMembre s_gm = new SGroupeMembre();
                        int id_gm = s_gm.chercher_groupe_membre(gm.getId(),currentmembre.getId());
                        s_gm.modifier_groupe_membre(gm);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        titre.setText("Titre : "+gr.getTitre());
        description.setText("Description : "+gr.getDescription());
        etat.setText("Etat : Publique");
        if(gr.getAutorisation()==0)etat.setText("Etat : Privé");
        
        
        TableColumn colId = new TableColumn("id");
        colId.setMinWidth(100);
        colId.setCellValueFactory(
                new PropertyValueFactory<Membre, String>("id"));
        TableColumn colNom = new TableColumn("Nom");
        colNom.setMinWidth(100);
        colNom.setCellValueFactory(
                new PropertyValueFactory<Membre, String>("nom"));
        TableColumn colPrenom = new TableColumn("Prenom");
        colPrenom.setMinWidth(100);
        colPrenom.setCellValueFactory(
                new PropertyValueFactory<Membre, String>("prenom"));
        TableColumn coletat = new TableColumn("Etat");
        coletat.setMinWidth(100);
        coletat.setCellValueFactory(
                new PropertyValueFactory<Membre, String>("mail"));
        TableColumn colbloquer = new TableColumn("Bloquer");
        colbloquer.setMinWidth(100);
        colbloquer.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        colbloquer.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
        
        List<entities.Membre> list_m = new ArrayList<>();
        SGroupeMembre s_gm = new SGroupeMembre();
        List<entities.GroupeMembre> list_gm = new ArrayList<>();
        list_gm = s_gm.chercher_groupe_membres_par_id(gr.getId());
        for(GroupeMembre gmi : list_gm)
        {
            ServiceMembre s_mb = new ServiceMembre();
            Membre Membre_usr = new Membre("","",gmi.getEtat(),"","","","",0,0,gmi.getId_membre(),0,"");
            ResultSet lsss = s_mb.afficherUsr(Membre_usr);
            try {
                lsss.next();
                String nom_membre = lsss.getString(2);
                String prenom_membre = lsss.getString(3);
                list_m.add(new Membre(lsss.getString(2),lsss.getString(3),gmi.getEtat(),
                        lsss.getString(6),lsss.getString(7),lsss.getString(10),lsss.getString(9)
                        ,lsss.getInt(8),lsss.getInt(4),lsss.getInt(1),lsss.getInt(11),lsss.getString(12)));
            } catch (SQLException ex) {
                Logger.getLogger(MembreGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        lss= FXCollections.observableArrayList(list_m);
        table_membres.setItems(lss);
        table_membres.getColumns().addAll(colId, colNom, colPrenom,coletat, colbloquer);
        
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupesInterface.fxml"));        
        root.getChildren().setAll(pane);    
    }
    
    
}
