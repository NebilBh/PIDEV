/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import utils.ConnexionDB;
import entities.Portfolio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nebil
 */
public class ServicePortfolio {
    Connection db = ConnexionDB.getInstance().getCnx();
    
    public void ajouter(Portfolio p){
    /*    Statement state;
        try {
            state = db.createStatement();
            String qry ="insert into portfolio values("+p.getId_port()+","+p.getId_membre()+",'"+p.getTitre()+"','"+p.getDescription()+"','"+p.getLien()+"')";
        
            state.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePortfolio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
      try 
        {
            Statement st = db.createStatement();
            String req ="insert into portfolio values("+p.getId_port()+","+p.getId_membre()+",'"+p.getTitre()+"','"+p.getDescription()+"','"+p.getLien()+"')";

            st.executeUpdate(req);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     /*public void modifierPortfolio(Portfolio p, String titre,String description,String lien){
        try 
        {
            PreparedStatement pt = db.prepareStatement("update portfolio set titre=?,description=?,lien=?  where id_port=?");
            pt.setString(1, titre); //ordre fel requete
             pt.setString(2, description);
            pt.setInt(3,p.getId_port());
            pt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
        */
    
      public void modifierPortfolio(Portfolio o){
        try 
        {
            PreparedStatement pt = db.prepareStatement("update portfolio set titre=?,description=?,lien=? where id_port=?");
             pt.setString(1,o.getTitre());
               pt.setString(2,o.getDescription());
               pt.setString(3,o.getLien());
            
            pt.setInt(4,o.getId_port());
            pt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void supprimer(Portfolio p){
        PreparedStatement pstmt ;
        String qry = "delete from portfolio where id_port = ?";
        try {
            pstmt = db.prepareStatement(qry);
            pstmt.setInt(1,p.getId_port());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePortfolio.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public List<Portfolio> afficher(){
        List<Portfolio> myList = new ArrayList<Portfolio>();
        String qry ="select * from portfolio";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
               Portfolio p =new Portfolio();
               p.setId_port(rs.getInt(1));
                 p.setId_membre(rs.getInt(2));
                   p.setTitre(rs.getString(3));
                     p.setDescription(rs.getString(4));
                       p.setLien(rs.getString(5));
                myList.add(p);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePortfolio.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(myList);
      
    return myList;
    
    }
}
