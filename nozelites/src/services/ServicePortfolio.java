/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import utils.ConnexionDB;
import entities.Portfolio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nebil
 */
public class ServicePortfolio {
    Connection db = ConnexionDB.getInstance().getCnx();
    
    public void ajouter(Portfolio p,int userId){
        Statement state;
        try {
            state = db.createStatement();
            String qry ="insert into portfolio values("+p.getId_port()+","+p.getImage()+","+userId+")";
        
            state.executeUpdate(qry);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePortfolio.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void afficher(){
        String qry ="Select * from Portfolio";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            ResultSet usrList = stmt.executeQuery();
            while(usrList.next()) {
                System.out.println("User : [id : "+usrList.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePortfolio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
