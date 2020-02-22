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
    
    public Session (){
    }
    
    public void setSession(int id){
        this.idSession = id;
    }

    public static int getIdSession() {
        return idSession;
    }
    
    
}
