/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Favoris_entities;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionDB;

/**
 *
 * @author salon2
 */
public class Favoris_services {
        Connection c = ConnexionDB.getInstance().getCnx();

     public void ajouterfavoris(Favoris_entities f){
        try 
        {
             Statement st = c.createStatement();
             String req = "insert into favoris values ("+f.getId_fav()+","+f.getId_pub()+","+f.getId_membre()+")";
             st.executeUpdate(req);
        }
            catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
