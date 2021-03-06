/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.Evennement;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceEvennement;
import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeWindow;
import com.sbix.jnotify.NoticeType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import utils.Session;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
/**
 * FXML Controller class
 *
 * @author syrine
 */
public class AddEvenementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane root;
    @FXML
    private TextField nom;
    @FXML
    private TextField lieu;
    @FXML
    private TextField lieu2;
    @FXML
    private DatePicker date;
    @FXML
    private TextField hh;
     @FXML
    private TextField mm;
    @FXML
    private TextField siteweb;
    @FXML
    private TextField description;
    @FXML
    private TextField nbplace;
    @FXML 
    private Button ajouter;
    @FXML
        private Button mesev;
    @FXML
        private Button listeev;
    @FXML
        private Button ajouterev;
    @FXML 
    private ImageView imgv;
    public String pathh;
    
    private String absolutePathPhotoAnnonce;
    @FXML
    private HBox btn_deconnection;
    @FXML
    private ImageView notifications;
     Session session = new Session();
    
     @FXML 
    private void importer(ActionEvent event) {
        //file = fileChooser.showOpenDialog();
        //FileChooser fc = new FileChooser();
        
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        //File f =fc.showOpenDialog(null);
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(//
               //
              new FileChooser.ExtensionFilter("JPG", "*.jpg"), //
              new FileChooser.ExtensionFilter("PNG", "*.png"));
           String path = fileChooser.showOpenDialog(null).getAbsolutePath();
        pathh=path;
        path = path.replace('\\','/');
        
        System.out.println(path);
        Image img = new Image("file:///"+path);
        
        imgv.setImage(img);
        
        //Image img = new Image(f.getAbsolutePath());
        //if (f != null){
        //   l.setText("selected file" + f.getAbsolutePath());
           //imgv.setImage(img);
        //}
        
        
   }
   
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
    @FXML
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
    @FXML
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
     
    @FXML 
    public void AjouterEvenement(ActionEvent event){
       String name=nom.getText();
       String lie=lieu.getText()+"/"+lieu2.getText(); 
       String dat=date.getValue().toString();
       String heure=hh.getText()+":"+mm.getText()+":00";
       String site=siteweb.getText();
       String desc=description.getText();
       int nbp=Integer.parseInt(nbplace.getText());
      // String img=image.getStyle();
       
        //java.util.Date date_util = new java.util.Date(12/12/2012);
       // java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
       //if(!nom.getText().equals("") && !lieu.getText().equals("") && !hh.getText().equals("") && !siteweb.getText().equals("") && !description.getText().equals("") && !nbplace.getText().equals("")){
       int comp = 0;
       try {
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date datee=new java.util.Date();
            java.util.Date d1=sdf.parse(dat);
             System.out.println(datee);
             System.out.println(d1);
            System.out.println(d1.compareTo(datee));
            comp=d1.compareTo(datee);
        } catch (ParseException ex) {
            Logger.getLogger(AddEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(comp);
       if(comp!=-1){
       if(hh.getText().length()==2 && mm.getText().length()==2){
        Evennement e=new Evennement(Session.getIdSession(), name, lie, dat, heure, desc, site, 0, nbp, pathh);
        ServiceEvennement srv=new ServiceEvennement();
        srv.ajouterEvennement(e);
           try {
               Notification.sendNotification("module evennement", "evennement ajouté ",TrayIcon.MessageType.INFO);
           } catch (AWTException ex) {
               Logger.getLogger(AddEvenementController.class.getName()).log(Level.SEVERE, null, ex);
           } catch (MalformedURLException ex) {
               Logger.getLogger(AddEvenementController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else {
          new NoticeWindow(NoticeType.ERROR_NOTIFICATION, "verifier l'heure", NoticeWindow.LONG_DELAY,NPosition.CENTER);
       }}else{
            new NoticeWindow(NoticeType.ERROR_NOTIFICATION, "verifier la date", NoticeWindow.LONG_DELAY,NPosition.CENTER);
       }
           
                
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
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
    
}
