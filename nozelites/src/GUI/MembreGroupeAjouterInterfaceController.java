/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.util.Callback;
import com.sun.prism.impl.Disposer;
import com.sun.prism.impl.Disposer.Record;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import entities.Groupe;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import services.SGroupe;
import services.SGroupeMembre;
import entities.GroupeMembre;
import entities.Membre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ServiceMembre;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class MembreGroupeAjouterInterfaceController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private TextField description;
    @FXML
    private RadioButton groupe_public;
    @FXML
    private RadioButton groupe_ferme;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_retour;
    @FXML
    private AnchorPane root;

    
    private int id_membre = Session.getIdSession();
    ObservableList<Membre> lss;
    List<Integer> membres_invite = new ArrayList<>();
    @FXML
    private TableView<Membre> table_membres;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
    @FXML
    private TextField chercher_membre;

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
        final Button cellButton = new Button("Inviter");
        
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
                	//lss.remove(currentmembre);
                        cellButton.setVisible(false);
                        membres_invite.add(currentmembre.getUsrId());
                        //bloquer membre
                        /*for(Membre m : lss)
                            System.out.println(m.getId());*/
                        
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
        final ToggleGroup group = new ToggleGroup();

        groupe_public.setToggleGroup(group);
        groupe_public.setSelected(true);

        groupe_ferme.setToggleGroup(group);
        
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
        TableColumn colbloquer = new TableColumn("Inviter");
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
        
            ServiceMembre s_mb = new ServiceMembre();
            ResultSet lsss = s_mb.afficher();
            
            try {
                while(lsss.next())
                {System.out.println("cc");
                    if(lsss.getInt(1)!=id_membre)
                        list_m.add(new Membre(lsss.getString(2),lsss.getString(3),lsss.getString(5),
                        lsss.getString(6),lsss.getString(7),lsss.getString(10),lsss.getString(9)
                        ,lsss.getInt(8),lsss.getInt(4),lsss.getInt(1),lsss.getInt(11),lsss.getString(12)));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MembreGroupeInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        lss= FXCollections.observableArrayList(list_m);
        table_membres.setItems(lss);
        table_membres.getColumns().addAll( colId,colNom, colPrenom, colbloquer);
        
        chercher_membre.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            //System.out.println("textfield changed from " + oldValue + " to " + newValue);
            List<entities.Membre> list_m2 = new ArrayList<>();
            for(Membre mii : list_m)
            {
                if(mii.getLogin().contains(chercher_membre.getText())||
                                chercher_membre.getText().equals(""))
                        list_m2.add(mii);
            }
            
            /*SGroupeMembre sgm = new SGroupeMembre();
            
            List<entities.Membre> list2 = s_mb.afficher2();
            for(entities.Membre gm : list2)
            {
                if(gm.getUsrId()!=id_membre)
                {
                    if(gm.getLogin().contains(chercher_membre.getText())||
                                chercher_membre.getText().equals(""))
                        list_m.add(gm);
                }
            }*/

            lss.removeAll();
            lss= FXCollections.observableArrayList(list_m2);
            table_membres.setItems( lss);
        });
    }    

    @FXML
    private void ajouter_groupe(ActionEvent event) throws IOException {
        int etat = 0;
        if(groupe_public.isSelected())etat=1;
        Groupe g = new Groupe(1,titre.getText(),description.getText(),etat);
        SGroupe sg = new SGroupe();
        sg.ajouter_groupe(g);
        
        GroupeMembre gm1 = new GroupeMembre(5,sg.get_last_id(),id_membre,-1,"administrateur");
        SGroupeMembre s_gm = new SGroupeMembre();
        System.out.println("ddd"+sg.get_last_id());
        s_gm.ajouter_groupe_membre(gm1);
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupesInterface.fxml"));
        root.getChildren().setAll(pane);
        
        SGroupe s_g = new SGroupe();
        for(int i : membres_invite)
        {
            GroupeMembre gm = new GroupeMembre(1,/*id_groupe*/s_g.get_last_id(),i,id_membre,"invitation");
            s_gm.ajouter_groupe_membre(gm);
        }
        Notifications notification = Notifications.create()
                .title("groupe ajouté")
                .text("avec succé")
                .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.TOP_LEFT);
        notification.showConfirm();
    }

    @FXML
    private void retour_groupe(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MembreGroupesInterface.fxml"));
        root.getChildren().setAll(pane);
    }
    
}
