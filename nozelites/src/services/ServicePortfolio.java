/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import utils.ConnexionDB;
import entities.Portfolio;
import entities.PortfolioForGUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
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
    
      public void modifierPortfolio(PortfolioForGUI o){
        try 
        {
            PreparedStatement pt = db.prepareStatement("update portfolio set titre=?,description=?,lien=? where id_port=?");
             pt.setString(1,o.getTitre());
               pt.setString(2,o.getDescription());
               pt.setString(3,o.getLien());
            pt.setInt(4,o.getId());
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
    
  /*  public List<Portfolio> afficher(){
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
    
    }*/
    
    public List<Portfolio> afficher(){
           List<Portfolio> list = new ArrayList<Portfolio>();

        
        try 
        {
            PreparedStatement pt = db.prepareStatement("select * from portfolio");
                                                                                                           
           // pt.setInt(1, id_Emetteur);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                //System.out.println("Offre : \nType : "+rs.getString(2)+"\nEntreprise : "+rs.getString(3)+"\nDomaine : "+rs.getString(4)+"\nPoste : "+rs.getString(5)+"\nRequis : "+rs.getString(6)+"\nDescription : "+rs.getString(7)+"\nLe : "+rs.getString(8)+"\nEtat : "+rs.getString(9)+"\nA : "+rs.getString(10)+" "+rs.getString(11)+"\n"); //ordre fel table
                Portfolio o = new Portfolio(rs.getString(3),rs.getString(4),rs.getInt(1),rs.getInt(2),rs.getString(5));
                list.add(o);
            }
           
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return list;
    }
    
    public List<PortfolioForGUI> afficherLePORTFOLIO(int id_Emetteur){
        
        List<PortfolioForGUI> list = new ArrayList<PortfolioForGUI>();
        
        try 
        {
            PreparedStatement pt = db.prepareStatement("select id_port,titre,description,lien from portfolio where id_membre=?"
                    + "                                                                                         ");
            pt.setInt(1, id_Emetteur);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                //System.out.println("Offre : \nType : "+rs.getString(2)+"\nEntreprise : "+rs.getString(3)+"\nDomaine : "+rs.getString(4)+"\nPoste : "+rs.getString(5)+"\nRequis : "+rs.getString(6)+"\nDescription : "+rs.getString(7)+"\nLe : "+rs.getString(8)+"\nEtat : "+rs.getString(9)+"\nA : "+rs.getString(10)+" "+rs.getString(11)+"\n"); //ordre fel table
                PortfolioForGUI o = new PortfolioForGUI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                list.add(o);
            }
           
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return list;
    }
    public void supprimerReclamationPort(PortfolioForGUI o){   
        try 
        {
            PreparedStatement pt = db.prepareStatement("delete from portfolio where id_port=?");
            pt.setInt(1,o.getId());
            pt.executeUpdate();     
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
