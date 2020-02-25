/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.OffreForGUI;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServicesOffre;
import entities.Groupe;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.SGroupe;
import services.ServiceMembre;


/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class AdminAcceuilInterfaceController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML

    private BarChart<String, Integer> StatOffres;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    
    int[] monthCounter = new int[12];
    
    ServicesOffre srvOffre = new ServicesOffre();
    
    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    
    @FXML
    private VBox menu;
    @FXML
    private PieChart pieChart_groupe;
    @FXML
    private NumberAxis yAxisCompte;
    @FXML
    private CategoryAxis xAxisCompte;
    @FXML
    private BarChart<String, Integer> StatCompte;
    
    int[] monthCounterCompte = new int[12];
    
    ServiceMembre srvMembre = new ServiceMembre();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
        
        monthCounter = srvOffre.getStatOffre();
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        
        StatOffres.getData().add(series);
        
        
        xAxisCompte.setCategories(monthNames);
        monthCounterCompte = srvMembre.getStatCompte();
        XYChart.Series<String, Integer> seriesCompte = new XYChart.Series<>();
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounterCompte.length; i++) {
            seriesCompte.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounterCompte[i]));
        } 
        StatCompte.getData().add(seriesCompte);
        

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
        pieChart_groupe.setData(pie);

    }    
    
    @FXML
    private void acceuil(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminAcceuilInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void reclamations(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ReclamationDesMembres.fxml"));
        root.getChildren().setAll(pane);
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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminChasseurInterface.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void evenements(MouseEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("BackEvenement.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void offres(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InterfaceAdminOffre.fxml"));
        root.getChildren().setAll(pane);
    }

    @FXML
    private void decoGo(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ConnectionAdminInterface.fxml"));
        root.getChildren().setAll(pane);
    }
    
}
