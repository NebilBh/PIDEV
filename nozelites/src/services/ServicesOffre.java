/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import entities.Membre;
import entities.Offre;
import entities.OffreForGUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionDB;

/**
 *
 * @author Wael Berrachid
 */
public class ServicesOffre {
    
    Connection c = ConnexionDB.getInstance().getCnx();
    
    public int getIdMembre(String email){
        int id = 0;
        try 
        {
            PreparedStatement pt = c.prepareStatement("select idUsr from membre WHERE mail=?");
            pt.setString(1, email);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                id = rs.getInt(1);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
     public String getMailMembre(int id){
        String r = "";
        try 
        {
            PreparedStatement pt = c.prepareStatement("select mail from membre WHERE idUsr=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                r = rs.getString(1);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public List<Membre> afficherTopDomaine(String Domaine){
        
        List<Membre> list = new ArrayList<Membre>();
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("SELECT count(*), mail, Formation, Experience\n" +
                                                        " FROM offre INNER JOIN membre ON offre.IdRecepteur=membre.idUsr\n" +
                                                        " WHERE Domaine=? GROUP BY IdRecepteur ORDER BY count(*) DESC");
            pt.setString(1, Domaine);
            ResultSet rs = pt.executeQuery();
            
            int i = 1;
            
            while(rs.next() && i!=4 )
            {   
                //System.out.println(i+" Nombre d'offres : "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)); //ordre fel table
                Membre m = new Membre("","",rs.getString(2),"","",rs.getString(4),rs.getString(3),0,0,0,rs.getInt(1),"");
                list.add(m);
                i++;
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public List<Membre> afficherTopMois(String Mois){ //Mois must be like -08-
        
        List<Membre> list = new ArrayList<Membre>();
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("SELECT idUsr, count(*), nom, prenom, mail, Experience, age, tel, image\n" +
                                                        " FROM offre INNER JOIN membre ON offre.IdRecepteur=membre.idUsr\n" +
                                                        " WHERE offre.Date LIKE '%"+Mois+"%' GROUP BY IdRecepteur ORDER BY count(*) DESC");
            ResultSet rs = pt.executeQuery();
            
            int i = 1;
            
            while(rs.next() && i!=4 )
            {   
                //System.out.println(i+" Nombre d'offres : "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)); //ordre fel table
                Membre m = new Membre(rs.getString(3),rs.getString(4),rs.getString(5),"","",rs.getString(6),"",rs.getInt(7),rs.getInt(8),rs.getInt(1),rs.getInt(2),rs.getString(9));
                list.add(m);
                i++;
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
    }
    
    public void accepterOffre(OffreForGUI o){
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
    
    public void refuserOffre(OffreForGUI o){
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
    
    public String nbrOffres(){
        String s = "";
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("select count(*) from offre");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                s = rs.getString(1);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return s;
    }
    
    public String nbrOffresAcceptees(){
        String s = "";
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("select count(*) from offre WHERE Etat=?");
            pt.setString(1, "Acceptée");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                s = rs.getString(1);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return s;
    }
    
    public List<OffreForGUI> afficherLesOffresRecus(int idRecepteur){
        
        List<OffreForGUI> list = new ArrayList<OffreForGUI>();
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("select id, offre.Type, offre.Entreprise, Domaine, Poste, Requis, Description, offre.Date, Etat, nom, prenom from offre INNER JOIN chasseur_talent ON offre.IdEmetteur=chasseur_talent.idUsr where IdRecepteur=?");
            pt.setInt(1, idRecepteur);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                //System.out.println("Offre : \nType : "+rs.getString(2)+"\nEntreprise : "+rs.getString(3)+"\nDomaine : "+rs.getString(4)+"\nPoste : "+rs.getString(5)+"\nRequis : "+rs.getString(6)+"\nDescription : "+rs.getString(7)+"\nLe : "+rs.getString(8)+"\nEtat : "+rs.getString(9)+"\nA : "+rs.getString(10)+" "+rs.getString(11)+"\n"); //ordre fel table
                OffreForGUI o = new OffreForGUI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
                list.add(o);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return list;
    }
    
    public List<OffreForGUI> afficherLesOffresEnvoyees(int idEmetteur){
        
        List<OffreForGUI> list = new ArrayList<OffreForGUI>();
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("select id, offre.Type, Entreprise, Domaine, Poste, Requis, Description, offre.Date, Etat, nom, prenom from offre INNER JOIN membre ON offre.IdRecepteur=membre.idUsr where IdEmetteur=?");
            pt.setInt(1, idEmetteur);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                //System.out.println("Offre : \nType : "+rs.getString(2)+"\nEntreprise : "+rs.getString(3)+"\nDomaine : "+rs.getString(4)+"\nPoste : "+rs.getString(5)+"\nRequis : "+rs.getString(6)+"\nDescription : "+rs.getString(7)+"\nLe : "+rs.getString(8)+"\nEtat : "+rs.getString(9)+"\nA : "+rs.getString(10)+" "+rs.getString(11)+"\n"); //ordre fel table
                OffreForGUI o = new OffreForGUI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11));
                list.add(o);
            }
           
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return list;
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
    
    public void modifierOffre(OffreForGUI o){
        try 
        {
            PreparedStatement pt = c.prepareStatement("update offre set type=?,entreprise=?,domaine=?,poste=?,requis=?,description=? where id=?");
            pt.setString(1,o.getType()); //ordre fel requete
            pt.setString(2,o.getEntreprise());
            pt.setString(3,o.getDomaine());
            pt.setString(4,o.getPoste());
            pt.setString(5,o.getRequis());
            pt.setString(6,o.getDescription());
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
    
    public void supprimerOffre(OffreForGUI o){   
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
