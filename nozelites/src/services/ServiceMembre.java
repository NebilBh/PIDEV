/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Membre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
    
    public void ajouter(Membre user){
        try {
            Statement state;
            
            state = db.createStatement();
            
            String qry ="insert into membre values("+5+",'"+user.getNom()+"','"+user.getPrenom()+"',"+user.getTel()+",'"+user.getMail()+"',"
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
    
    public void afficher(){
        String qry ="Select * from membre";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            ResultSet usrList = stmt.executeQuery();
            while(usrList.next()) {
                System.out.println("User : [id : "+usrList.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            stmt.setInt(11,user.getId());
            stmt.executeUpdate();
            System.out.println("succes");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMembre.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public void confirmationMail(Membre user) throws Exception{
        
        JavaMail.sendMail(user.getMail(),"Confirmation email"+user.getNom(),"Monsieur"+user.getNom()+" "+user.getPrenom()+" veuillez confirmer votre email \n");
                    
    }
    public void statistiqueMois(String mois){ //date jj-mm-aaaa
        
    }
    
    public void statisqueAnnee(String annee){
        
    }
    
}
