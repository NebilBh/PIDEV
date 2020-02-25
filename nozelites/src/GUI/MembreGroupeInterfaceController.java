/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Publication_entities;
import entities.Groupe;
import entities.GroupeMembre;
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
import services.ServiceMembre;
import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import services.SGroupe;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author AFRIC OPTIC
 */
public class MembreGroupeInterfaceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private Label etat;
    @FXML
    private TableView<Membre> table_membres;
    @FXML
    private TableView<Publication_entities> tables_publications;
    @FXML
    private TableView<Membre> table_invitation;
    
    public static Groupe gr ;
    public static int id_membre = Session.getIdSession();
    ObservableList<Membre> lss;
    ObservableList<Membre> lss_i;
    @FXML

    private Button pub;

    @FXML
    private void pub(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                            root.getChildren().setAll(pane);
    }
                            
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private Button reclamer_btn;

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

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Membre_Reclamation_GRP.fxml"));        
        root.getChildren().setAll(pane);
    }

    /**
     * Initializes the controller class.
     */
    
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
                        GroupeMembre gm = new GroupeMembre(gr.getId(),2,1,3,"bloqué");
                        SGroupeMembre s_gm = new SGroupeMembre();
                        GroupeMembre id_gm = s_gm.chercher_groupe_membre(gm.getId(),currentmembre.getUsrId());
                        s_gm.modifier_groupe_membre(gm);
                        //srv.supprimerOffre(currentOffre);
                        try {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupeInterface.fxml"));
                            root.getChildren().setAll(pane);
                        } catch (IOException ex) {
                            Logger.getLogger(MembreGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private class ButtonCell2 extends TableCell<Disposer.Record, Boolean> {
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
                        SGroupeMembre s_gm = new SGroupeMembre();
                        GroupeMembre gm = new GroupeMembre(1,gr.getId(),currentmembre.getUsrId(),id_membre,"invitation");
                        s_gm.ajouter_groupe_membre(gm);
                        try {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupeInterface.fxml"));
                            root.getChildren().setAll(pane);
                        } catch (IOException ex) {
                            Logger.getLogger(MembreGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
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
                if(gmi.getEtat().equals("administrateur")||gmi.getEtat().equals("membre"))
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
        colId.setVisible(false);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        List<entities.Membre> list_i = new ArrayList<>();
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
        TableColumn colInviter = new TableColumn("Inviter");
        colInviter.setMinWidth(100);
        colInviter.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        colInviter.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new MembreGroupeInterfaceController.ButtonCell2();
            }
        
        });
        
        
        
            ServiceMembre s_mb = new ServiceMembre();
            ResultSet lsss = s_mb.afficher();
            SGroupeMembre sgm = new SGroupeMembre();
            
            try {
                while(lsss.next())
                {
                    boolean existe = true;
                    for(Membre mii : list_m)
                        if(lsss.getInt(1)==mii.getUsrId() || sgm.chercher_groupe_membre(gr.getId(),lsss.getInt(1))!=null)existe=false;
                    if(existe)
                        list_i.add(new Membre(lsss.getString(2),lsss.getString(3),lsss.getString(5),
                        lsss.getString(6),lsss.getString(7),lsss.getString(10),lsss.getString(9)
                        ,lsss.getInt(8),lsss.getInt(4),lsss.getInt(1),lsss.getInt(11),lsss.getString(12)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MembreGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        lss_i= FXCollections.observableArrayList(list_i);
        table_invitation.setItems(lss_i);
        table_invitation.getColumns().addAll(colId_i, colNom_i, colPrenom_i, colInviter);
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupesInterface.fxml"));        
        root.getChildren().setAll(pane);
    }
    
}
