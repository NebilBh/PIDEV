/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import entities.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionDB;

/**
 *
 * @author Wael Berrachid
 */
public class ServicesOffre {
    
    Connection c = ConnexionDB.getInstance().getCnx();
    
    public void ajouterOffre(Offre o){ 
        try 
        {
            LocalDate localDate = LocalDate.now();
            Statement st = c.createStatement();
            String req = "insert into offre values("+o.getId()+",'"+o.getType()+"',"+o.getIdEmetteur()+","+o.getIdRecepteur()+",'"+o.getEntreprise()+"','"+o.getDomaine()+"','"+o.getPoste()+"','"+o.getRequis()+"','"+o.getDescription()+"','"+localDate+"')";
            st.executeUpdate(req);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifierOffre(Offre o, String type, String entreprise, String domaine, String poste, String requis, String description){
        try 
        {
            PreparedStatement pt = c.prepareStatement("update offre set type=?,entreprise=?,domaine=?,poste=?,requis=?,description=? where id=?");
            pt.setString(1,type); //ordre fel requete
            pt.setString(2,entreprise);
            pt.setString(3,domaine);
            pt.setString(4,poste);
            pt.setString(5,requis);
            pt.setString(6,description);
            pt.setInt(7,o.getId());
            pt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherOffre(){
        try 
        {
            PreparedStatement pt = c.prepareStatement("select * from offre");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("Offre : "+rs.getString(2)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9)); //ordre fel table
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherUneOffre(Offre o){
        try 
        {
            PreparedStatement pt = c.prepareStatement("select * from offre where id=?");
            pt.setInt(1, o.getId());
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("Offre : "+rs.getString(2)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7)+" "+rs.getString(8)+" "+rs.getString(9)); //ordre fel table
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerOffre(Offre o){   
        try 
        {
            PreparedStatement pt = c.prepareStatement("delete from offre where id=?");
            pt.setInt(1,o.getId());
            pt.executeUpdate();     
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
