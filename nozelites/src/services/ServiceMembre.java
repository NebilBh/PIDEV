/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Groupe;
import entities.Membre;
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
import utils.JavaMail;

/**
 *
 * @author Nebil
 */
public class ServiceMembre {
    Connection db = ConnexionDB.getInstance().getCnx();
    private static String recherche;

    public static String getRecherche() {
        return recherche;
    }

    public static void setRecherche(String recherche) {
        ServiceMembre.recherche = recherche;
    }

   public int nbrMembre(){
       try {
            
       
            String qry ="Select Count(*) from membre";
            
            PreparedStatement stmt= db.prepareStatement(qry);
            ResultSet usrList = stmt.executeQuery();
            usrList.next();
            return usrList.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return 0;
   }
    
    public void ajouter(Membre user){
        try {
            Statement state;
            
            state = db.createStatement();
            
            String qry ="insert into membre values("+0+",'"+user.getNom()+"','"+user.getPrenom()+"',"+user.getTel()+",'"+user.getMail()+"',"
                    + "'"+user.getLogin()+"','"+user.getMdp()+"',"+user.getAge()+",'"+user.getFormation()+"',"
                    +"'"+user.getExp()+"',"+user.getType()+",'"+user.getImage()+"','"+LocalDate.now()+"')";
            state.executeUpdate(qry);
            
            System.out.println("Ajout effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void supprimer(Membre user){
        PreparedStatement pstmt ;
        String qry = "delete from membre where idUsr = ?";
        try {
            
            pstmt = db.prepareStatement(qry);
            pstmt.setInt(1,user.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet afficher(){
        String qry ="Select * from membre";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            ResultSet usrList = stmt.executeQuery();
            /*while(usrList.next()) {
                System.out.println("User : [id : "+usrList.getInt(1));
            }*/
            return usrList;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet afficherUsr(Membre usr){
        String qry ="Select * from membre where idUsr = ?";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            stmt.setInt(1,usr.getId());
            ResultSet usrList = stmt.executeQuery();
            return usrList;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   
    
    public ResultSet RechercheNom(String recherche){
                String qry ="Select * from membre where nom LIKE ? OR prenom LIKE ? ";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            stmt.setString(1,"%"+recherche+"%");
            stmt.setString(2,"%"+recherche+"%");
            ResultSet usrList = stmt.executeQuery();
            return usrList;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public ResultSet RecherchePrenom(String recherche){
        
                String qry ="Select * from membre where prenom LIKE ? ";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            stmt.setString(1,"%"+recherche+"%");
            ResultSet usrList = stmt.executeQuery();
            return usrList;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
  
    }
    public ResultSet authen(String login ,String mdp){
        String qry = "Select * from membre where login = ? AND mdp = ?";
         
        
        try {
            PreparedStatement stmt = db.prepareStatement(qry);
            stmt.setString(1,login);
            stmt.setString(2,mdp);
            ResultSet usrList = stmt.executeQuery();
            
            return usrList;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    public void modifier(Membre user,Membre newM ) {
        PreparedStatement stmt ;
        
        String qry = "update membre set nom = ?, prenom = ?,mail = ?, mdp = ?,login = ?,tel = ?,age = ?,Experience = ?,Formation = ?,type = ?,image = ? where idUsr = ?";
        try {
            stmt = db.prepareStatement(qry);
            stmt.setString(1, newM.getNom());
            stmt.setString(2, newM.getPrenom());
            stmt.setString(3, newM.getMail());
            stmt.setString(4, newM.getMdp());
            stmt.setString(5, newM.getLogin());
            stmt.setInt(6,newM.getTel());
            stmt.setInt(7,newM.getAge());
            stmt.setString(8,newM.getExp());
            stmt.setString(9,newM.getFormation());
            stmt.setInt(10,newM.getType());
            stmt.setString(11,newM.getImage());
            stmt.setInt(12,user.getId());
            stmt.executeUpdate();
            System.out.println("succes");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public void confirmationMail(Membre user) throws Exception{
        
        JavaMail.sendMail(user.getMail(),"Confirmation email"+user.getMail(),"Monsieur"+user.getNom()+" "+user.getPrenom()+" Bienvenue \n");
                    
    }
    public void statistiqueMois(String mois){ //date jj-mm-aaaa
        
    }
    
    public void statisqueAnnee(String annee){
        
    }
    public List<Membre> afficher2(){
       List<Membre> arr=new ArrayList<>();
        String qry ="Select * from membre";
        
        try {
           Statement ste=db.createStatement();
            ResultSet rs=ste.executeQuery(qry);
           
            while(rs.next()) {
                
                String nom=rs.getString(2);
                String prenom=rs.getString(3);
                Membre e=new Membre(nom,prenom);
                arr.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    public Membre rechercherM(int id){
    Membre e=new Membre();
        try{ 
        
        String requete="select * from Membre where idUsr="+id+" ";
        PreparedStatement pst=db.prepareStatement(requete);
         ResultSet rs=pst.executeQuery();
         
       
        while(rs.next()){
            e=new Membre(rs.getString(2),rs.getString(3),rs.getString(5));
                //System.out.println(A);
        } 
        return e;
     } catch (SQLException ex) {
            Logger.getLogger(ConnexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return null;
}
}
