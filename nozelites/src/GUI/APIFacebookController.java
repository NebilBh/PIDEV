/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Album;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author nadhir
 */
public class APIFacebookController implements Initializable {

    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            /*FacebookClient facebookClient = new DefaultFacebookClient(
                Constants.MY_ACCESS_TOKEN);
 
       CustomUser user = facebookClient.fetchObject("me", CustomUser.class,
               Parameter.with("fields",
                       "id, name, email, first_name, last_name"));
 
       System.out.println("First Name= " + user.getFirstName());
       System.out.println("Last Name= " + user.getLastName());
       System.out.println("Full Name= " + user.getFullName());
       System.out.println("Email= " + user.getEmail());*/
        } catch (Exception e) {
        }

        // TODO
    }    

    @FXML
    private void click(ActionEvent event) {
        
    }
    
    
}
