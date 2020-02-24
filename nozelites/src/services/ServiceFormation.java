/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Formation;
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
 * @author Nebil
 */
public class ServiceFormation {
     Connection db = ConnexionDB.getInstance().getCnx();
     


public void ajouter(Formation d,int id_membre){
        try {
            Statement state;
            
            state = db.createStatement();
            
            String qry ="insert into formation values("+0+",'"+d.getTitre()+"',"+id_membre+")";
            
            state.executeUpdate(qry);
            
            System.out.println("Ajout Formation effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


public ResultSet afficher(Formation d){
    String qry ="Select * from formation";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            ResultSet usrList = stmt.executeQuery();
            return usrList;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
}

public void supprimer(Formation d){
    PreparedStatement pstmt ;
        String qry = "delete from formation where id_formation = ?";
        try {
            
            pstmt = db.prepareStatement(qry);
            pstmt.setInt(1,d.getId_formation());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public ResultSet afficherFormationUser(int id_membre) throws SQLException{
    String qry ="Select formation.id_formation, formation.titre FROM formation INNER JOIN membre ON"
            + " formation.id_membre = membre.idUsr where membre.idUsr = ?";
    PreparedStatement stmt= db.prepareStatement(qry);
    stmt.setInt(1,id_membre);
    ResultSet usrList = stmt.executeQuery();
    return usrList;
}
}
