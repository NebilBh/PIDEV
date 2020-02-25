/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire_entities;
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
public class Commentaire_services {
    Connection c = ConnexionDB.getInstance().getCnx();
    
    public void ajoutercommentaire(Commentaire_entities k){
        try 
        {System.out.println(k.getId_membre()+" --- ");
             Statement st = c.createStatement();
             String req = "insert into commentaire values ("+k.getId_commentaire()+","+k.getId_membre()+","+k.getId_publication()+",'"+k.getCommentaire()+"')";
             st.executeUpdate(req);
        }
            catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
