/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Diplome;
import entities.chasseurTalent;
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
 * @author Nebil
 */
public class ServiceDiplome {
     Connection db = ConnexionDB.getInstance().getCnx();
     


public void ajouter(Diplome d,int id_membre){
        try {
            Statement state;
            
            state = db.createStatement();
            
            String qry ="insert into listediplome values("+0+",'"+d.getOrganisation()+"','"+d.getDomaine()+"',"+id_membre+")";
            
            state.executeUpdate(qry);
            
            System.out.println("Ajout Diplome effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChasseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


public ResultSet afficher(Diplome d){
    String qry ="Select * from listediplome";
        
        try {
            PreparedStatement stmt= db.prepareStatement(qry);
            ResultSet usrList = stmt.executeQuery();
            return usrList;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChasseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
}

public void supprimer(Diplome d){
    PreparedStatement pstmt ;
        String qry = "delete from listediplome where id_diplome = ?";
        try {
            
            pstmt = db.prepareStatement(qry);
            pstmt.setInt(1,d.getId_diplome());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChasseur.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public ResultSet afficherDiplomeUser(int id_membre) throws SQLException{
    String qry ="Select listediplome.domaine, listediplome.organisation FROM listediplome INNER JOIN membre ON"
            + " listediplome.id_membre = membre.idUsr";
    PreparedStatement stmt= db.prepareStatement(qry);
    ResultSet usrList = stmt.executeQuery();
    return usrList;
}
}