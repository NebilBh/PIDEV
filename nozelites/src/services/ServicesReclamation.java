/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionDB;

/**
 *
 * @author KHAIRI
 */
public class ServicesReclamation {
    
    Connection c = ConnexionDB.getInstance().getCnx();
    
     public void ajouterReclamation(Reclamation p){ 
        try 
        {
            Statement st = c.createStatement();
            String req = "insert into reclamation values("+p.getIdRecl()+","+p.getId_emeteur()+","+p.getId_cible()+",'"+p.getDescription()+"',"+p.getEtat()+",'"+p.getSelecteur()+"')";
            st.executeUpdate(req);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifierReclamation(Reclamation p, String type,String description){
        try 
        {
            PreparedStatement pt = c.prepareStatement("update reclamation set selecteur=?,description=?  where idRecl=?");
            pt.setString(1, type); //ordre fel requete
             pt.setString(2, description);
            pt.setInt(3,p.getIdRecl());
            pt.executeUpdate();  
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherReclamation(){
        
    
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("select * from reclamation ");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                    String selecteur = rs.getString(6);
                    int id_cible = rs.getInt(3);
                    int id_membre = rs.getInt(2);
                    if(selecteur.equals("groupe"))
                    {
                         PreparedStatement tt = c.prepareStatement("select reclamation.idRecl , membre.nom , membre.prenom , groupe.titre , groupe.descriptiong from reclamation inner join groupe on groupe.idGroupe = "+id_cible+" inner join membre on membre.idUsr = "+id_membre);
                         ResultSet ff = tt.executeQuery();
                         
                         while(ff.next())
                            System.out.println("reclamation :  "+ff.getInt(1)+" "+ff.getString(2)+" "+ff.getString(3)+" "+ff.getString(4)+" "+ff.getString(5));
                        
                    }
                    else if(selecteur.equals("membre"))
                            {
                                 PreparedStatement uu = c.prepareStatement("select reclamation.idRecl,mb1.nom,mb1.prenom,mb2.nom,mb2.prenom,description,etat from reclamation "+
                                         " inner join membre as mb1 on mb1.idUsr="+id_membre+
                                         " inner join membre as mb2 on mb2.idUsr="+id_cible );
                         ResultSet mm = uu.executeQuery();
                                 while(mm.next())
                            System.out.println("reclamation :  "+mm.getInt(1)+" "+mm.getString(2)+" "+mm.getString(3)+" "+mm.getString(4)+" "+mm.getString(5)+" "+mm.getString(6)+" "+mm.getInt(7));
                        
                    }
                    else if(selecteur.equals("publication"))
                    {
                         PreparedStatement tt = c.prepareStatement("select reclamation.idRecl , membre.nom , membre.prenom , publication.titre , publication.description from reclamation "
                                 + "inner join publication on publication.idPub = "+id_cible+" inner join membre on membre.idUsr = "+id_membre);
                         ResultSet ff = tt.executeQuery();
                         
                         while(ff.next())
                            System.out.println("reclamation :  "+ff.getInt(1)+" "+ff.getString(2)+" "+ff.getString(3)+" "+ff.getString(4)+" "+ff.getString(5));
                        
                    }
                    else//evenement
                    {
                         PreparedStatement tt = c.prepareStatement("select reclamation.idRecl , membre.nom , membre.prenom , evenement.nom , evenement.description from reclamation "
                                 + "inner join evenement on evemenet.idE = "+id_cible+" inner join membre on membre.idUsr = "+id_membre);
                         ResultSet ff = tt.executeQuery();
                         
                         while(ff.next())
                            System.out.println("reclamation :  "+ff.getInt(1)+" "+ff.getString(2)+" "+ff.getString(3)+" "+ff.getString(4)+" "+ff.getString(5));
                        
                    }
                    
                    
                    
                                   
                            
            }
        }
        
        
            
        
         
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerPersonne(Reclamation p){   
        try 
        {
            PreparedStatement pt = c.prepareStatement("delete from reclamation where idRecl=?");
            pt.setInt(1,p.getIdRecl());
            pt.executeUpdate();     
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* public void afficherPersonne(){
        
    
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("select idRecl,nom,prenom,type,description,etat from reclamation inner join membre on idUsr=id_emeteur");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                System.out.println("reclamation :  "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6) ); //ordre fel table
            }
        } 
         
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
}

    

