/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nebil
 */
public class ConnexionDB {
    private static final String URL ="jdbc:mysql://localhost:3306/nozelites" ; // ATTENTION MODIFICATION DU PORT
    private static final String USR = "root";
    private static final String PWD ="99726135";
    
    private Connection cnx;
    private static ConnexionDB cnxDB;
    
    private ConnexionDB () {
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Connection getCnx() {
        return cnx;
    }
    
    public static ConnexionDB getInstance() {
        if (cnxDB == null) {
            cnxDB = new ConnexionDB();
        }
        else 
        {
            return cnxDB;
        }
        
        return cnxDB;      
        
    }
    
    
    
    
    
}
