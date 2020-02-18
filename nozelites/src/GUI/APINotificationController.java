/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class APINotificationController implements Initializable {

    @FXML
    private Button btn_test;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void notifier(ActionEvent event) {
        
        Notifications notification = Notifications.create()
                .title("membre ajouté")
                .text("nadhir bouhaouala")
                .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.TOP_LEFT);
                /*.onAction(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        System.out.println("vu");
                    }
                });*/
        notification.showConfirm();
    }
    
}
