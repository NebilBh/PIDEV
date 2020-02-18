/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.GroupeMembre;
import utils.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nadhir
 */
public class SGroupeMembre {
    
    Connection c = ConnexionDB.getInstance().getCnx();
    
    public List<GroupeMembre> afficher_groupes_membres()
    {
        List<GroupeMembre> list = new ArrayList<GroupeMembre>();
        try {
            PreparedStatement pt = c.prepareStatement("select * from Groupe_Membre");
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                System.out.println("GroupeMembre : [ id_gm "+rs.getInt(1)+" id_groupe : "+rs.getInt(2)+" id_membre : "+rs.getInt(3)+" invite : "+rs.getInt(4)+" etat : "+rs.getString(5)+" ]");
                list.add(new GroupeMembre(rs.getInt(1),rs.getInt(2),rs.getInt(3),+rs.getInt(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void ajouter_groupe_membre(GroupeMembre gm)
    {
        try 
        {
            int last_id = this.get_last_id()+1;
            Statement st = c.createStatement();//statement simple
            String req = "insert into Groupe_Membre values("+last_id+","+gm.getId_groupe()+","+gm.getId_membre()+","+gm.getId_invite()+",'"+gm.getEtat()+"')";
            st.executeUpdate(req);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimer_groupe_membre(GroupeMembre gm)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from Groupe_Membre where id_gm=?");
            pt.setInt(1,gm.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimer_groupe_membre_par_id(int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from Groupe_Membre where id_gm=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifier_groupe_membre(GroupeMembre gm)
    {  
        try 
        {
            PreparedStatement pt 
                    = c.prepareStatement("update Groupe_Membre set etat = ? where id_gm =?");
            pt.setString(1,gm.getEtat());
            pt.setInt(2,gm.getId());
            pt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<GroupeMembre> chercher_groupe_membres_par_id(int id_groupe)
    {
        List<GroupeMembre> list = new ArrayList<GroupeMembre>();
        try {
            PreparedStatement pt = c.prepareStatement("select * from Groupe_Membre where id_groupe = "+id_groupe);
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                System.out.println("GroupeMembre : [ id_gm "+rs.getInt(1)+" id_groupe : "+rs.getInt(2)+" id_membre : "+rs.getInt(3)+" invite : "+rs.getInt(4)+" etat : "+rs.getString(5)+" ]");
                list.add(new GroupeMembre(rs.getInt(1),rs.getInt(2),rs.getInt(3),+rs.getInt(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<GroupeMembre> chercher_groupes_membre_par_id(int id_membre)
    {
        List<GroupeMembre> list = new ArrayList<>();
        try {
            PreparedStatement pt = c.prepareStatement("select * from Groupe_Membre where id_membre = "+id_membre);
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                System.out.println("GroupeMembre : [ id_gm "+rs.getInt(1)+" id_groupe : "+rs.getInt(2)+" id_membre : "+rs.getInt(3)+" invite : "+rs.getInt(4)+" etat : "+rs.getString(5)+" ]");
                list.add(new GroupeMembre(rs.getInt(1),rs.getInt(2),rs.getInt(3),+rs.getInt(4),rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int chercher_groupe_membre(int id_groupe,int id_membre)
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from Groupe_Membre where id_membre = "+id_membre+" and id_groupe = "+id_groupe);
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public boolean membre_est_fondateur(int id_groupe, int id_membre)
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from Groupe_Membre where id_membre = "+id_membre+" and id_groupe = "+id_groupe);
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                if(rs.getString(5).equals("fondateur"))return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int get_last_id()
    {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT MAX(id_gm) AS max_id FROM Groupe_Membre");
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    /*public void modifier_groupe_membre2(int idgm,String etat)
    {
         
        try 
        {
            PreparedStatement pt 
                    = c.prepareStatement("update GroupeMembre set etat = ? where id_gm =?");
            pt.setString(1,etat);
            pt.setInt(2,idgm);
            pt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public void supprimer_groupe_membre2(int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from GroupeMembre where id_gm=?");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SGroupeMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
