/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author KHAIRI
 */
public class ReclamationForMembre {
    
    int id;
    private String nom,prenom,mail,nomc,prenomc,etat,selecteur,description,date;

    public ReclamationForMembre(int id, String nom, String prenom, String mail, String nomc, String prenomc, String etat, String selecteur, String description, String date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomc = nomc;
        this.prenomc = prenomc;
        this.etat = etat;
        this.selecteur = selecteur;
        this.description = description;
        this.date = date;
    }
    
    
    
}
