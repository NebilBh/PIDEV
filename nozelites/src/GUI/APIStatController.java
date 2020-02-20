/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class APIStatController implements Initializable {

    @FXML
    private PieChart pieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<PieChart.Data> pie = FXCollections.observableArrayList(
                new PieChart.Data("Cars",13),
                new PieChart.Data("Cars",13),
                new PieChart.Data("Cars",13)
        );
        pieChart.setData(pie);
    }    
    
}
