/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import entities.Evennement;
import entities.Membre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import services.ServiceEvennement;

/**
 * FXML Controller class
 *
 * @author syrine
 */
public class AllEvenementsController implements Initializable, MapComponentInitializedListener {

    /**
     * Initializes the controller class.
     */
     public static ObservableList<Evennement> obsl;
     public static ObservableList<Membre> par;
     public static int indice;
     // public static int idm;
     @FXML
    private FlowPane flow;
    
   @FXML
    private BorderPane container;

     private ServiceEvennement service;
     @FXML
        private Button mesev;
    @FXML
        private Button listeev;
    @FXML
        private Button ajouterev;
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    @FXML
    private AnchorPane root;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AfficherCards();
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
           LatLong joeSmithLocation = new LatLong(47.6197, -122.3231);
        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
        
        ArrayList<Evennement> list = new ArrayList<>();
        list = (ArrayList) service.affichertout2();
        for(int in=0; in<list.size(); in++)
        {
          String n=  list.get(in).getLieu();
          int p=n.indexOf("/");
          String l1=n.substring(0, p);
          String l2=n.substring(p+1);
          int la=Integer.parseInt(l1);
          int lb=Integer.parseInt(l2);
          
          LatLong place = new LatLong(la, lb);
         MarkerOptions Options = new MarkerOptions();
        Options.position(place);
        Marker placeMarker = new Marker(Options);
        map.addMarker( placeMarker );
        }
            /*
        LatLong joeSmithLocation = new LatLong(47.6197, -122.3231);
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);
        Marker joeSmithMarker = new Marker(markerOptions1);
        map.addMarker( joeSmithMarker );
        */
        
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(47.6097, -122.3331))
                //.mapType(MapType.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
                   
        map = mapView.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);
        
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);
        
        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(bobUnderwoodLocation);
        
        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(tomChoiceLocation);
        
        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(fredWilkieLocation);
        
        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
        Marker bobUnderwoodMarker = new Marker(markerOptions3);
        Marker tomChoiceMarker= new Marker(markerOptions4);
        Marker fredWilkieMarker = new Marker(markerOptions5);
        
        map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
        map.addMarker( bobUnderwoodMarker );
        map.addMarker( tomChoiceMarker );
        map.addMarker( fredWilkieMarker );
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);
    }
    
     public void AfficherCards() {

         service = new ServiceEvennement();
        
       //idm=;
       PageEvenementController.i =0;
        ArrayList<Evennement> annonces = new ArrayList<>();
        annonces = (ArrayList) service.affichertout2();
        obsl = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 0;
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("PageEvenement.fxml"));
                flow.getChildren().add(nodes[i]);
                
                
                ArrayList<Membre> part = new ArrayList<>();
                part= (ArrayList) service.AfficherParticipant(annonces.get(i));
                par=  FXCollections.observableArrayList(part);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
     /* panierService = new PanierService();
        int nbr=panierService.LonguerPanier();
        nombre_article.setText("("+nbr+")");*/
        
    }
}
