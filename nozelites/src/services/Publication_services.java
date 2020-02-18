/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;
import entities.Publication_entities;
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
public class Publication_services {
    Connection c = ConnexionDB.getInstance().getCnx();
    public void ajouterPublication(Publication_entities p){
        try 
        {
             Statement st = c.createStatement();
             String req = "insert into publication values ('"+p.getTitre()+"','"+p.getDescription()+"',"+p.getId()+",'"+p.getImage()+"',"+p.getId_groupe()+","+p.getId_publicateur()+")";
             st.executeUpdate(req);
        }
            catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void modifierPublication(Publication_entities p, String titre){
        try 
        {
                PreparedStatement pt = c.prepareStatement("update publication set titre=? where id=?");
            pt.setString(1, titre); //ordre fel requete
            pt.setInt(2,p.getId());
            pt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
         public void modifierPublication2(Publication_entities p, String description){
        try 
        {
                PreparedStatement pt = c.prepareStatement("update publication set titre=? where id=?");
            pt.setString(1, description); //ordre fel requete
            pt.setInt(2,p.getId());
            pt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Publication_entities> afficherPublication(){
        ArrayList<Publication_entities> myList = new ArrayList();
        try 
        {
            PreparedStatement pt = c.prepareStatement("select * from publication");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                Publication_entities p = new Publication_entities();
               p.setTitre(rs.getString(1));
               p.setDescription(rs.getString(2));
               p.setId(rs.getInt(3));
               p.setImage(rs.getString(4));
               p.setId_groupe(rs.getInt(5));
               p.setId_publicateur(rs.getInt(6));
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
    
    public void supprimerPublication(Publication_entities p){   
        try 
        {
            PreparedStatement pt = c.prepareStatement("delete from publication where id=?");
            pt.setInt(1,p.getId());
            pt.executeUpdate();     
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      public Publication_entities rechercherPublication(Publication_entities p, String titre){
        try 
        {
            PreparedStatement pt = c.prepareStatement("select from publication where id="+p.getId());
            ResultSet rs = pt.executeQuery();
            while(rs.next())
                return new Publication_entities();
          
        } 
          catch (SQLException ex) 
        {
            Logger.getLogger(Publication_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     
    }
      public void afficherusertrierparage() {
        try { 
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `publication` ORDER BY titre Desc ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getObject(i).toString() + " ");
                }
                System.out.println("\n");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
      
}
