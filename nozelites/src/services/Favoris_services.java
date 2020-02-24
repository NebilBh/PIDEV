/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Favoris_entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

/**
 *
 * @author salon2
 */
public class Favoris_services {
        Connection c = ConnexionBD.getInstance().getCon();

     public void ajouterfavoris(Favoris_entities f){
        try 
        {
             Statement st = c.createStatement();
             String req = "insert into favoris2 values ("+f.getId_fav()+","+f.getId_pub()+","+f.getId_membre()+")";
             st.executeUpdate(req);
        }
            catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public void ajouterfavoris2(Favoris_entities f1){
        try 
        {
             Statement st = c.createStatement();
             String req = "insert into favoris2 values ("+f1.getId_fav()+","+f1.getId_pub()+","+f1.getId_membre()+")";
             st.executeUpdate(req);
        }
            catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          public List<Favoris_entities> afficherfavoris(){
        ArrayList<Favoris_entities> myList = new ArrayList();
        try 
        {
            PreparedStatement pt = c.prepareStatement("select * from favoris2");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                Favoris_entities p = new Favoris_entities();
               p.setId_fav(rs.getInt(1));
               p.setId_pub(rs.getInt(2));
               p.setId_membre(rs.getInt(3));
              
               myList.add(p);
               
 
                
               // System.out.println("publication : "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5)+" "+rs.getInt(6)); //ordre fel table
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    }
}
