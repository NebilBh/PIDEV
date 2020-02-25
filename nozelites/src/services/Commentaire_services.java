/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire_entities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    public List<Commentaire_entities> affichercommentaire(){
        ArrayList<Commentaire_entities> myList = new ArrayList();
        try 
        {
            PreparedStatement pt = c.prepareStatement("select * from commentaire");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                Commentaire_entities p = new Commentaire_entities();
               p.setId_commentaire(rs.getInt(1));
               p.setId_membre(rs.getInt(2));
               p.setId_publication(rs.getInt(3));
               p.setCommentaire(rs.getString(4));
               
              
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
    public List<Commentaire_entities> affichercommentaire2(int id,int idp){
        ArrayList<Commentaire_entities> myList = new ArrayList();
        try 
        {
            PreparedStatement pt = c.prepareStatement("select * from commentaire where id_membre=? AND id_publication=?");
            pt.setInt(1,id);
            pt.setInt(2,idp);
            System.out.println(idp);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                Commentaire_entities p = new Commentaire_entities();
               p.setId_commentaire(rs.getInt(1));
               p.setId_membre(rs.getInt(2));
               p.setId_publication(rs.getInt(3));
               p.setCommentaire(rs.getString(4));
               
              
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
