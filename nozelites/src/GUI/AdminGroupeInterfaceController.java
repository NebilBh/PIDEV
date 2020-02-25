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
import services.SGroupeMembre;
import entities.Membre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.ServiceMembre;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class AdminGroupeInterfaceController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label etat;
    
    public static Groupe gr ;
    //public static int id_groupe = 1;
    public static int id_membre = 1;
    @FXML
    private AnchorPane root;
    
    
    //ObservableList<Membre> lss;
    ObservableList<Membre> lss_i;
    @FXML
    private TableView<Membre> table_membres;
    @FXML
    private VBox menu;
    @FXML
    private Button pub;

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
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));
        root.getChildren().setAll(pane);*/
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BackEvenement.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void offres(MouseEvent event) throws IOException {
        /*AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceAminOffre.fxml"));
        root.getChildren().setAll(pane);*/
    }

    @FXML
    private void pub(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminPublicationInterface.fxml"));
                            root.getChildren().setAll(pane);
    }
    
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
                	lss_i.remove(currentmembre);
                        //bloquer membre
                        GroupeMembre gm = new GroupeMembre(0,2,1,3,"bloqué");
                        SGroupeMembre s_gm = new SGroupeMembre();
                        GroupeMembre id_gm = s_gm.chercher_groupe_membre(gr.getId(),currentmembre.getUsrId());
                        gm.setId(id_gm.getId());
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
    
    /*private class ButtonCell2 extends TableCell<Disposer.Record, Boolean> {
        final Button cellButton = new Button("Inviter");
        
        ButtonCell2()
        {
            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) 
                {
                    // get Selected Item
                	Membre currentmembre = (Membre) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                	//remove selected item from the table list
                	//lss.remove(currentmembre);
                        cellButton.setVisible(false);
                        //bloquer membre
                        GroupeMembre gm1 = new GroupeMembre(5,id_groupe,currentmembre.getId(),id_membre,"administrateur");
                        SGroupeMembre s_gm = new SGroupeMembre();
                        s_gm.ajouter_groupe_membre(gm1);
                        
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
    }*/

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
        
        
        /*TableColumn colId = new TableColumn("id");
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
                Logger.getLogger(AdminGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        lss= FXCollections.observableArrayList(list_m);
        table_invitation.setItems(lss);
        table_invitation.getColumns().addAll( colNom, colPrenom,coletat, colbloquer);*/
        
        
        
        
        
        
        
        TableColumn colId_i = new TableColumn("id");
        colId_i.setMinWidth(100);
        colId_i.setCellValueFactory(
                new PropertyValueFactory<Membre, String>("id"));
        TableColumn colNom_i = new TableColumn("Nom");
        colNom_i.setMinWidth(100);
        colNom_i.setCellValueFactory(
                new PropertyValueFactory<Membre, String>("nom"));
        TableColumn colPrenom_i = new TableColumn("Prenom");
        colPrenom_i.setMinWidth(100);
        colPrenom_i.setCellValueFactory(
                new PropertyValueFactory<Membre, String>("prenom"));
        TableColumn colEtat_i = new TableColumn("Etat");
        colEtat_i.setMinWidth(100);
        colEtat_i.setCellValueFactory(
                new PropertyValueFactory<Membre, String>("mail"));
        TableColumn colbloquer_i = new TableColumn("Bloquer");
        colbloquer_i.setMinWidth(100);
        colbloquer_i.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        colbloquer_i.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }
        
        });
        
        List<entities.Membre> list_mb = new ArrayList<>();
        
            SGroupeMembre sgm = new SGroupeMembre();
            List<GroupeMembre> membres_groupe = sgm.chercher_groupe_membres_par_id(gr.getId());
            ServiceMembre s_mb = new ServiceMembre();
            for(GroupeMembre gmi : membres_groupe)
                if(!gmi.getEtat().equals("bloqué")&&!gmi.getEtat().equals("invitation"))
            {
                Membre usr = new Membre("","",gmi.getEtat(),"","","","",0,0,gmi.getId_membre(),0,"");
                ResultSet lsss = s_mb.afficherUsr(usr);
            
                try {
                    while(lsss.next())
                    {
                            list_mb.add(new Membre(lsss.getString(2),lsss.getString(3),gmi.getEtat(),
                            lsss.getString(6),lsss.getString(7),lsss.getString(10),lsss.getString(9)
                            ,lsss.getInt(8),lsss.getInt(4),lsss.getInt(1),lsss.getInt(11),lsss.getString(12)));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
            
            
        
        
        lss_i= FXCollections.observableArrayList(list_mb);
        table_membres.setItems(lss_i);
        table_membres.getColumns().addAll( colId_i,colNom_i, colPrenom_i,colEtat_i, colbloquer_i);
        colId_i.setVisible(false);
        
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminGroupesInterface.fxml"));        
        root.getChildren().setAll(pane);    
    }
    
}
