/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Message;
import entities.MessageForGUI;
import java.sql.Array;
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
public class ServicesMessage {
    
    Connection c = ConnexionDB.getInstance().getCnx();
    
    public void envoyerMessage(Message m){ 
        try 
        {
            LocalDate localDate = LocalDate.now();
            Statement st = c.createStatement();
            String req = "insert into message values("+m.getId()+",'"+m.getObjet()+"','"+m.getTexte()+"',"+m.getIdRecepteur()+","+m.getIdEmetteur()+",'"+localDate+"')";
            st.executeUpdate(req);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void supprimerMessage(MessageForGUI m){   
        try 
        {
            PreparedStatement pt = c.prepareStatement("delete from message where idMessage=?");
            pt.setInt(1,m.getId());
            pt.executeUpdate();     
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<MessageForGUI> afficherLesMessagesRecus(int idRecepteur){
       
        List<MessageForGUI> list = new ArrayList<MessageForGUI>();
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("select idMessage, objet, texte, nom, prenom, message.date from message INNER JOIN membre ON message.id_emeteur=membre.idUsr where id_destinataire=?");
            pt.setInt(1, idRecepteur);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                //System.out.println("Message : \nObjet : "+rs.getString(2)+"\nMessage : "+rs.getString(3)+"\nDe : "+rs.getString(4)+" "+rs.getString(5)+"\nLe : "+rs.getString(6)+"\n"); //ordre fel table
                MessageForGUI m = new MessageForGUI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                list.add(m);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesMessage.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return list;
    }
    
    public List<MessageForGUI> afficherLesMessagesEnvoyes(int idEmetteur){
        
        List<MessageForGUI> list = new ArrayList<MessageForGUI>();
        
        try 
        {
            PreparedStatement pt = c.prepareStatement("select idMessage, objet, texte, nom, prenom, message.date from message INNER JOIN membre ON message.id_destinataire=membre.idUsr where id_emeteur=?");
            pt.setInt(1, idEmetteur);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next())
            {
                //System.out.println("Message : \nObjet : "+rs.getString(2)+"\nMessage : "+rs.getString(3)+"\nDe : "+rs.getString(4)+" "+rs.getString(5)+"\nLe : "+rs.getString(6)+"\n"); //ordre fel table
                MessageForGUI m = new MessageForGUI(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                list.add(m);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ServicesMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
