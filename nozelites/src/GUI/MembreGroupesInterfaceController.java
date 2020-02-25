/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.prism.impl.Disposer;
import entities.Groupe;
import entities.GroupeMembre;
import entities.GroupeMembreInvite;
import entities.Membre;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.SGroupe;
import services.SGroupeMembre;
import services.ServiceMembre;
import com.sun.prism.impl.Disposer.Record;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import utils.Session;

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
    private int id_membre = Session.getIdSession();
    ObservableList<entities.Groupe> lss;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<entities.GroupeMembreInvite> table_invitations;
    ObservableList<entities.GroupeMembreInvite> ls_invitations;//membre(nom,prenom) + groupe(titre,description)
    @FXML
    private ComboBox<?> combobox_recherche;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;

    @FXML
    private void ajouter_groupe(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupeAjouterInterface.fxml"));        
        root.getChildren().setAll(pane);  
        
    }

    @FXML
    private void chercher(InputMethodEvent event) {
        System.out.println("fff");
    }

    @FXML
    private void map(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("APIGoogleMaps.fxml"));
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
                        SGroupeMembre s_gm = new SGroupeMembre();
                        GroupeMembre gm = s_gm.chercher_groupe_membre(currentgroupe.getId(), id_membre);
                        if(gm!=null && gm.getEtat().equals("administrateur"))
                        {
                            lss.remove(currentgroupe);
                            //remove from DB
                            SGroupe s_g = new SGroupe();
                            s_g.supprimer_groupe(currentgroupe);
                        }
                        else 
                        {
                            Notifications notification = Notifications.create()
                            .title("erreur")
                            .text("Membre standard ne peut pas supprimer un groupe")
                            .graphic(null)
                            .hideAfter(Duration.seconds(10))
                            .position(Pos.TOP_RIGHT);
                            notification.showConfirm();
                        }
                        try {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupeSInterface.fxml"));
                            root.getChildren().setAll(pane);
                        } catch (IOException ex) {
                            Logger.getLogger(MembreGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                	
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
                	GroupeMembreInvite currentgroupe = (GroupeMembreInvite) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                	//remove selected item from the table list
                	ls_invitations.remove(currentgroupe);
                        //remove from DB
                        SGroupeMembre s_gm = new SGroupeMembre();
                        GroupeMembre gm = new GroupeMembre(currentgroupe.getId_gmi(),0,0,0,"membre");
                        s_gm.modifier_groupe_membre(gm);
                        //srv.supprimerOffre(currentOffre);
                        try {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupeSInterface.fxml"));
                            root.getChildren().setAll(pane);
                        } catch (IOException ex) {
                            Logger.getLogger(MembreGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
                	GroupeMembreInvite currentgroupe = (GroupeMembreInvite) ButtonCell3.this.getTableView().getItems().get(ButtonCell3.this.getIndex());
                	//remove selected item from the table list
                	ls_invitations.remove(currentgroupe);
                        //remove from DB
                        SGroupeMembre s_gm = new SGroupeMembre();
                        GroupeMembre gm = new GroupeMembre(currentgroupe.getId_gmi(),0,0,0,"membre");
                        s_gm.supprimer_groupe_membre(gm);
                        //srv.supprimerOffre(currentOffre);
                        try {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupeSInterface.fxml"));
                            root.getChildren().setAll(pane);
                        } catch (IOException ex) {
                            Logger.getLogger(MembreGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
    
    private class ButtonCell4 extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Voir");
        
        ButtonCell4()
        {
            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) 
                {//afficher groupe
                    // get Selected Item
                	Groupe currentgroupe = (Groupe) ButtonCell4.this.getTableView().getItems().get(ButtonCell4.this.getIndex());
                	//remove selected item from the table list
                	
                    try {
                        MembreGroupeInterfaceController.gr = currentgroupe;
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MembreGroupeInterface.fxml"));
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
        List<entities.Groupe> list_g = new ArrayList<>();
        
        SGroupeMembre sgm = new SGroupeMembre();
        List<entities.GroupeMembre> list = sgm.chercher_groupes_membre_par_id(id_membre);
        SGroupe sg = new SGroupe();
        for(entities.GroupeMembre gm : list)
        {
            List<entities.Groupe> lsg = sg.chercher_groupe_par_id(gm.getId_groupe());
            for(entities.Groupe g : lsg)
            {
                System.out.println(gm.getEtat()+"ccc"+g);
                if(!gm.getEtat().equals("invitation")&&!gm.getEtat().equals("bloqué"))list_g.add(g);
            }
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
                return new ButtonCell4();
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
        table_groupes.getColumns().addAll(colVoir,colId, colTitre, colDescription, colEtat,colsupprimer);
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
                new PropertyValueFactory<entities.Groupe, Integer>("id_gmi"));
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
        TableColumn colIrefuser = new TableColumn("Refuser");
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
        SGroupeMembre s_gm = new SGroupeMembre();
        List<GroupeMembre> list_gm = s_gm.chercher_groupes_membre_par_id(id_membre);
        for(GroupeMembre gmi : list_gm)
        {
            if(gmi.getEtat().equals("invitation"))
            {
                SGroupe s_g = new SGroupe();
                List<Groupe> groupe_invitation = s_g.chercher_groupe_par_id(gmi.getId_groupe());
                Groupe gg = new Groupe();
                for(Groupe gi : groupe_invitation)
                {
                    gg=gi;
                    break;
                }
           
                try {
                    ServiceMembre s_mb = new ServiceMembre();
                    Membre Membre_usr = new Membre("","","","","","","",0,0,gmi.getId_invite(),0,"");
                    ResultSet lsss = s_mb.afficherUsr(Membre_usr);
                    while(lsss.next())
                    {
                        GroupeMembreInvite gminv = new GroupeMembreInvite(gmi.getId(), lsss.getString(2),lsss.getString(3), gg.getTitre(), gg.getDescription());
                        list_i.add(gminv);
                        break;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AdminGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    gmi.getId_invite();
                }
        }
        
        //--------------------------------------------------------------------------------------
        ls_invitations= FXCollections.observableArrayList(list_i);
        table_invitations.setItems(ls_invitations);
        table_invitations.getColumns().addAll(colInom,colIpprenom, colItitre, colIdescription, colIaccepter,colIrefuser);
        
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
        
        
        
        
        
        
        
        inpuitChercher.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            //System.out.println("textfield changed from " + oldValue + " to " + newValue);
            list_g.clear();
        
            List<entities.GroupeMembre> list2 = sgm.chercher_groupes_membre_par_id(id_membre);
            for(entities.GroupeMembre gm : list2)
            {
                List<entities.Groupe> lsg = sg.chercher_groupe_par_id(gm.getId_groupe());
                for(entities.Groupe g : lsg)
                {
                    if(!gm.getEtat().equals("invitation")&&!gm.getEtat().equals("bloqué"))
                        if(g.getTitre().contains(inpuitChercher.getText())||
                                inpuitChercher.getText().equals(""))
                        list_g.add(g);
                }
            }

            lss.removeAll();
            lss= FXCollections.observableArrayList(list_g);
            table_groupes.setItems( lss);
        });
        
        
        
        
        
 
    }    

    @FXML
    private void chercher_groupe(ActionEvent event) {
        System.out.println("tsss");
        
        List<entities.Groupe> list_g = new ArrayList<>();
        
        SGroupeMembre sgm = new SGroupeMembre();
        List<entities.GroupeMembre> list = sgm.chercher_groupes_membre_par_id(id_membre);
        SGroupe sg = new SGroupe();
        for(entities.GroupeMembre gm : list)
        {
            List<entities.Groupe> lsg = sg.chercher_groupe_par_id(gm.getId_groupe());
            for(entities.Groupe g : lsg)
            {
                System.out.println(gm.getEtat()+"ccc"+g);
                if(!gm.getEtat().equals("invitation")&&!gm.getEtat().equals("bloqué"))
                    if(inpuitChercher.getText().equals(g.getTitre())||
                            inpuitChercher.getText().equals(""))
                    list_g.add(g);
            }
        }
        
        lss.removeAll();
        lss= FXCollections.observableArrayList(list_g);
        table_groupes.setItems( lss);
    }
    
}
