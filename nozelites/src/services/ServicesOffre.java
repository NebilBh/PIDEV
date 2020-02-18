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
    
    public void afficherTopDomaine(String Domaine){
        try 
        {
            PreparedStatement pt = c.prepareStatement("SELECT count(*), nom, prenom, mail\n" +
                                                        " FROM offre INNER JOIN membre ON offre.IdRecepteur=membre.idUsr\n" +
                                                        " WHERE Domaine=? GROUP BY IdRecepteur ORDER BY count(*) DESC");
            pt.setString(1, Domaine);
            ResultSet rs = pt.executeQuery();
            
            int i = 1;
            
            while(rs.next() && i!=6 )
            {   
                System.out.println(i+" Nombre d'offres : "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)); //ordre fel table
                i++;
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherTopMois(String Mois){ //Mois must be like -08-
        try 
        {
            PreparedStatement pt = c.prepareStatement("SELECT count(*), nom, prenom, mail\n" +
                                                        " FROM offre INNER JOIN membre ON offre.IdRecepteur=membre.idUsr\n" +
                                                        " WHERE Date LIKE '%"+Mois+"%' GROUP BY IdRecepteur ORDER BY count(*) DESC");
            ResultSet rs = pt.executeQuery();
            
            int i = 1;
            
            while(rs.next() && i!=6 )
            {   
                System.out.println(i+" Nombre d'offres : "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)); //ordre fel table
                i++;
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void accepterOffre(Offre o){
        try 
        {
            PreparedStatement pt = c.prepareStatement("update offre set Etat=? where id=?");
            pt.setString(1, "Acceptée");
            pt.setInt(2,o.getId());
            pt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refuserOffre(Offre o){
        try 
        {
            PreparedStatement pt = c.prepareStatement("update offre set Etat=? where id=?");
            pt.setString(1, "Refusée");
            pt.setInt(2,o.getId());
            pt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherLesOffresRecus(int idRecepteur){
        try 
        {
            PreparedStatement pt = c.prepareStatement("select Type, offre.Entreprise, Domaine, Poste, Requis, Description, Date, Etat, nom, prenom from offre INNER JOIN chasseur_talent ON offre.IdEmetteur=chasseur_talent.idUsr where IdRecepteur=?");
            pt.setInt(1, idRecepteur);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("Offre : \nType : "+rs.getString(1)+"\nEntreprise : "+rs.getString(2)+"\nDomaine : "+rs.getString(3)+"\nPoste : "+rs.getString(4)+"\nRequis : "+rs.getString(5)+"\nDescription : "+rs.getString(6)+"\nLe : "+rs.getString(7)+"\nEtat : "+rs.getString(8)+"\nDe : "+rs.getString(9)+" "+rs.getString(10)+"\n"); //ordre fel table
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void afficherLesOffresEnvoyees(int idEmetteur){
        try 
        {
            PreparedStatement pt = c.prepareStatement("select Type, Entreprise, Domaine, Poste, Requis, Description, Date, Etat, nom, prenom from offre INNER JOIN membre ON offre.IdRecepteur=membre.idUsr where IdEmetteur=?");
            pt.setInt(1, idEmetteur);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("Offre : \nType : "+rs.getString(1)+"\nEntreprise : "+rs.getString(2)+"\nDomaine : "+rs.getString(3)+"\nPoste : "+rs.getString(4)+"\nRequis : "+rs.getString(5)+"\nDescription : "+rs.getString(6)+"\nLe : "+rs.getString(7)+"\nEtat : "+rs.getString(8)+"\nA : "+rs.getString(9)+" "+rs.getString(10)+"\n"); //ordre fel table
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void ajouterOffre(Offre o){ 
        try 
        {
            LocalDate localDate = LocalDate.now();
            Statement st = c.createStatement();
            String req = "insert into offre values("+o.getId()+",'"+o.getType()+"',"+o.getIdEmetteur()+","+o.getIdRecepteur()+",'"+o.getEntreprise()+"','"+o.getDomaine()+"','"+o.getPoste()+"','"+o.getRequis()+"','"+o.getDescription()+"','"+localDate+"','En attente')";
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
                System.out.println("Offre : \nType : "+rs.getString(2)+"\nEntreprise : "+rs.getString(5)+"\nDomaine : "+rs.getString(6)+"\nPoste : "+rs.getString(7)+"\nRequis : "+rs.getString(8)+"\nDescription : "+rs.getString(9)+"\nDate : "+rs.getString(10)+"\n"); //ordre fel table
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
                System.out.println("Offre : \nType : "+rs.getString(2)+"\nEntreprise : "+rs.getString(5)+"\nDomaine : "+rs.getString(6)+"\nPoste : "+rs.getString(7)+"\nRequis : "+rs.getString(8)+"\nDescription : "+rs.getString(9)+"\nDate : "+rs.getString(10)+"\n"); //ordre fel table
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
