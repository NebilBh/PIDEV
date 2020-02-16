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
public class Portfolio {
    private String image;
    private String projet ;
    private int id_port;
    
    public void Portfolio(){   
    }

    public Portfolio(String image, String projet, int id_port) {
        this.image = image;
        this.projet = projet;
        this.id_port = id_port;
    }

    public Portfolio(String image, String projet) {
        this.image = image;
        this.projet = projet;
    }

    public String getImage() {
        return image;
    }

    public String getProjet() {
        return projet;
    }

    public int getId_port() {
        return id_port;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public void setId_port(int id_port) {
        this.id_port = id_port;
    }
    
    
}
