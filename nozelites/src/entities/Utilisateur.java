/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author Nebil
 */
public class Utilisateur {
    private String nom, prenom, mail, login, mdp ;
    
    int age, tel,usrId ;
    
    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String mail, String login, String mdp, int age, int tel, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.login = login;
        this.mdp = mdp;
        
        
        this.age = age;
        this.tel = tel;
        this.usrId = id;
    }
    
    public Utilisateur(String nom, String prenom, String mail, String login, String mdp, int age, int tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.login = login;
        this.mdp = mdp;
        
        
        this.age = age;
        this.tel = tel;
        
    }

    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

   

    

    public void setAge(int age) {
        this.age = age;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    
    //---Getters ---
    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    
    

    public int getAge() {
        return age;
    }

    public int getTel() {
        return tel;
    }

    public int getId() {
        return usrId;
    }
    
    
}
