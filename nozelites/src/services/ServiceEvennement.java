/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Evennement;
import entities.Membre;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static java.util.Spliterators.iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionDB;
import utils.JavaMail;

/**
 *
 * @author syrine
 */
public class ServiceEvennement {
    Connection c=ConnexionDB.getInstance().getCnx(); 
    public void ajouterEvennement(Evennement e) 
    {
        try {
            DateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
            java.util.Date d=dateFormat.parse(e.getHeure());
            java.sql.Time t= new java.sql.Time(d.getTime());
           java.sql.Date date=new java.sql.Date(e.getDate().getTime());
            String requete="insert into Evenement (idc,nom,lieu,date,heure,description,siteWeb,NbParticipant,NbPlace,image) values (?,?,?,?,?,?,?,?,?,?)";
            try {
                
                
                
                PreparedStatement pst=c.prepareStatement(requete);
                pst.setInt(1,e.getIdc());
                pst.setString(2, e.getNom());
                pst.setString(3, e.getLieu());
                pst.setDate(4, date);
                pst.setTime(5,t);
                pst.setString(6,e.getDesciption());
                pst.setString(7,e.getSiteWeb());
                pst.setInt(8,e.getNbParticipant());
                pst.setInt(9,e.getNbPlace());
                pst.setString(10, e.getImage());
                pst.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }


    
    
   
    public List<Evennement> readAll() throws SQLException{
        List<Evennement> arr=new ArrayList<>();
        try {
            
            Statement ste=c.createStatement();
            ResultSet rs=ste.executeQuery("select * from Evenement");
            while (rs.next()) {
                int idc=rs.getInt(1);
                int ide=rs.getInt(2);
                String nom=rs.getString(3);
                String lieu=rs.getString(4);
                Date date=rs.getDate(5);
                String heure=rs.getString(6);
                String description=rs.getString(7);
                String siteweb=rs.getString(8);
                int Nbparticipant=rs.getInt(9);
                int NbPlace=rs.getInt(10);
                String image=rs.getString(11);
                Evennement e=new Evennement(idc,ide, nom, lieu,date,heure,description,siteweb,Nbparticipant,NbPlace,image);
                arr.add(e);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
  
         return arr;
    }



 
    public void supprimerEvennement(Evennement t) throws SQLException {
         
         try {
            PreparedStatement pt=c.prepareStatement("delete from Evenement where idE=? ");
            pt.setInt(1,t.getIdE());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void modifierEvennement(Evennement t) {
        try {
            DateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
            java.util.Date d=dateFormat.parse(t.getHeure());
            java.sql.Time ti= new java.sql.Time(d.getTime());
            java.sql.Date date=new java.sql.Date(t.getDate().getTime());
            try {
                PreparedStatement pt=c.prepareStatement("update Evenement set nom=?,lieu=?,date=?,heure=?,description=?,siteWeb=?,NbParticipant=?,NbPlace=?,image=? where idE=?");
                pt.setString(1,t.getNom());
                pt.setString(2,t.getLieu());
                pt.setDate(3, date);
                pt.setTime(4, ti);
                pt.setString(5,t.getDesciption());
                pt.setString(6,t.getSiteWeb());
                pt.setInt(7, t.getNbParticipant());
                pt.setInt(8, t.getNbPlace());
                pt.setString(9, t.getImage());
                pt.setInt(10, t.getIdE());
                pt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherusertrier() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `Evenement` ORDER BY NbParticipant Desc ");
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
    
    public Evennement RechercherEvennement(Evennement A) throws SQLException {
    Evennement e= new Evennement();
        try{ 
        
        String requete="select * from Evenement where idE="+A.getIdE()+" ";
        PreparedStatement pst=c.prepareStatement(requete);
         ResultSet rs=pst.executeQuery();
         
       
        while(rs.next()){
            e=new Evennement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getString(11));
           //System.out.println(A);
        } 
        return e;
     } catch (SQLException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return null;
    }
    
    
    public Boolean DisjoindreEvenement(Evennement e,Membre m)
    {
        try {
            String requete="select * from ListParticipant where idE=? and idm=?";
            PreparedStatement pst=c.prepareStatement(requete);
            pst.setInt(1, e.getIdE());
            pst.setInt(2, m.getId());
            ResultSet rs=pst.executeQuery();
            if(rs.next())
                {
                   
                    e.setNbParticipant(e.getNbParticipant()-1);
                    e.setNbPlace(e.getNbPlace()+1);
                    this.modifierEvennement(e);
                    
                    PreparedStatement pt=c.prepareStatement("delete from listparticipant where idE=? and idm=?");
                        pt.setInt(1,e.getIdE());
                        pt.setInt(2, m.getId());
                        pt.executeUpdate();
                        return true;
                }
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
          return false; 
    }
    
    public Boolean RejoindreEvenement(Evennement e,Membre m)
    {
        if(e.getNbPlace()!=0)
        {
            try {
                String requete="select * from listparticipant where idE=? and idm=?";
                PreparedStatement pst=c.prepareStatement(requete);
                pst.setInt(1, e.getIdE());
                pst.setInt(2, m.getId());
                ResultSet rs=pst.executeQuery();
                
                
                
                if(!rs.next())
                {
                    int x=e.getNbParticipant()+1;
                    e.setNbParticipant(x);
                    e.setNbPlace(e.getNbPlace()-1);
                    this.modifierEvennement(e);
                    try {
                        JavaMail.sendMailEvenement(m.getMail());
                    } catch (Exception ex) {
                        Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        
                        PreparedStatement pt=c.prepareStatement("insert into listparticipant (idE,idm) values (?,?)");
                        pt.setInt(1,e.getIdE());
                        pt.setInt(2, m.getId());
                        pt.executeUpdate();
                        return true;
                    } catch (SQLException ex) {
                        Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                }   } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        return false;
    }
public boolean RechercherMembre(Evennement e,Membre m)
{
        try {
            String requete="select * from listparticipant where idE=? and idm=?";
            PreparedStatement pst=c.prepareStatement(requete);
            pst.setInt(1, e.getIdE());
            pst.setInt(2, m.getId());
            ResultSet rs=pst.executeQuery();
            if(rs.next())
                return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
}
    public List<Integer> ListParticipant(Evennement e)
    {   List<Integer> arr=new ArrayList<>();
         try {
            
            PreparedStatement ps = c.prepareStatement("select idm from listparticipant WHERE idE=?");
            ps.setInt(1, e.getIdE());
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int idm=rs.getInt(1);
                arr.add(idm);
            }
            return arr;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
public List<Membre> AfficherParticipant(Evennement e){
    
    List<Membre> arr=new ArrayList<>();
    List<Integer> liste=ListParticipant(e);
    
        try {
            for(int i=0;i<liste.size();i++)
    {
            PreparedStatement ps = c.prepareStatement("select * from Membre  WHERE idUsr=?");
             ps.setInt(1, liste.get(i));
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                int idm=rs.getInt(1);
                String nom=rs.getString(2);
                String prenom=rs.getString(3);
                int tel=rs.getInt(4);
                String email=rs.getString(5);
                String login=rs.getString(6);
                String mdp=rs.getString(7);
                int age=rs.getInt(8);
                String formation=rs.getString(9);
                String experience=rs.getString(10);
                 Membre m=new Membre(nom, prenom, email, login, mdp, experience, formation, age, tel, age);
             
                arr.add(m);
            }}
            return arr;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
}
    
}
