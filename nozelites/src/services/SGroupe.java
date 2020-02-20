/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Groupe;
import entities.GroupeMembre;
import utils.ConnexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nadhir
 */
public class SGroupe {
    
    Connection c = ConnexionDB.getInstance().getCnx();
    
    public List<Groupe> afficher_groupes()
    {
        List<Groupe> list = new ArrayList<>();
        try {
            PreparedStatement pt = c.prepareStatement("select * from Groupe");
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                System.out.println("Groupe : [ id_groupe "+rs.getInt(1)+" titre : "+rs.getString(2)+" description : "+rs.getString(3)+" autorisation : "+rs.getInt(4)+" ]");
                list.add(new Groupe(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Groupe> afficher_groupes_trier_par_id()
    {
        List<Groupe> list = new ArrayList<>();
        try {
            PreparedStatement pt = c.prepareStatement("select * from Groupe");
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                System.out.println("Groupe : [ id_groupe "+rs.getInt(1)+" titre : "+rs.getString(2)+" description : "+rs.getString(3)+" autorisation : "+rs.getInt(4)+" ]");
                //list.add(new Groupe(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void ajouter_groupe(Groupe g)
    {
        try 
        {
            int last_id = this.get_last_id()+1;
            Statement st = c.createStatement();//statement simple
            String req = "insert into Groupe values("+last_id+",'"+g.getTitre()+"','"+g.getDescription()+"',"+g.getAutorisation()+")";
            
            st.executeUpdate(req);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimer_groupe(Groupe g)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from Groupe where id_groupe=?");
            pt.setInt(1,g.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifier_groupe(Groupe g)
    {
        try 
        {
            PreparedStatement pt 
                    = c.prepareStatement("update Groupe set titre = ? , description = ? , autorisation = ? where id_groupe =?");
            pt.setString(1,g.getTitre());
            pt.setString(2,g.getDescription());
            pt.setInt(3,g.getAutorisation());
            pt.setInt(4,g.getId());
            pt.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(SGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Groupe> chercher_groupe_par_id(int id)
    {
        List<Groupe> list = new ArrayList<>();
        try {
            PreparedStatement pt = c.prepareStatement("select * from Groupe where id_groupe = "+id);
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                System.out.println("Groupe : [ id_groupe "+rs.getInt(1)+" titre : "+rs.getString(2)+" description : "+rs.getString(3)+" autorisation : "+rs.getInt(4)+" ]");
                list.add(new Groupe(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SGroupe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int get_last_id()
    {
        try {
            PreparedStatement pt = c.prepareStatement("SELECT MAX(id_groupe) AS max_id FROM Groupe");
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
    
    public void creer_groupe(Groupe g,int id_membre)
    {
        this.ajouter_groupe(g);
        SGroupeMembre s_gm = new SGroupeMembre();
        GroupeMembre gm1 = new GroupeMembre(1,this.get_last_id(),id_membre,-1,"fondateur");
        s_gm.ajouter_groupe_membre(gm1);
        
    }
    
    public boolean inviter_membre(int id_groupe,int id_membre,int id_invite)
    {
        SGroupeMembre s_gm = new SGroupeMembre();
        if(s_gm.chercher_groupe_membre(id_groupe, id_membre)!=-1)//tester si le membre n'appartient pas au groupe
        {
            GroupeMembre gm1 = new GroupeMembre(5,id_groupe,id_membre,id_invite,"invitation");
            s_gm.ajouter_groupe_membre(gm1);
            return true;
        }
        return false;
    }
    
    public void accepter_invitation(int id_groupe,int id_membre)
    {
        SGroupeMembre s_gm = new SGroupeMembre();
        int id_gm = s_gm.chercher_groupe_membre(id_groupe, id_membre);
        GroupeMembre gm1 = new GroupeMembre(id_gm,1,1,2,"standard");
        s_gm.modifier_groupe_membre(gm1);
    }
    
    public void retirer_membre(int id_groupe,int id_membre)
    {
        SGroupeMembre s_gm = new SGroupeMembre();
        int id_gm = s_gm.chercher_groupe_membre(id_groupe, id_membre);
        GroupeMembre gm1 = new GroupeMembre(id_gm,1,1,2,"bloqué");
        s_gm.modifier_groupe_membre(gm1);
    }
    public void ajouter_admin(int id_groupe,int id_membre)
    {
        SGroupeMembre s_gm = new SGroupeMembre();
        if(!s_gm.membre_est_fondateur(id_groupe, id_membre))//tester si le membre n'est pas le fondateur
        {
            int id_gm = s_gm.chercher_groupe_membre(id_groupe, id_membre);
            GroupeMembre gm1 = new GroupeMembre(id_gm,1,1,2,"administrateur");
            s_gm.modifier_groupe_membre(gm1); 
        }
    }
    public void retirer_admin(int id_groupe,int id_membre)
    {
        SGroupeMembre s_gm = new SGroupeMembre();
        int id_gm = s_gm.chercher_groupe_membre(id_groupe, id_membre);
        GroupeMembre gm1 = new GroupeMembre(id_gm,1,1,2,"standard");
        if(!s_gm.membre_est_fondateur(id_groupe, id_membre))//tester si le membre n'est pas le fondateur
            s_gm.modifier_groupe_membre(gm1);
        
    }
    
}
