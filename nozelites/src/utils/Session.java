/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Nebil
 */
public class Session {
    
    private static int idSession;
    private static int id_select;

    public static void setId_select(int id_select) {
        Session.id_select = id_select;
    }

    public static int getId_select() {
        return id_select;
    }
    
    public Session (){
    }
    
    public void setSession(int id){
        this.idSession = id;
    }

    public static int getIdSession() {
        return idSession;
    }
    
    
}
