/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author salon2
 */
public class Publication_entities {
    private String titre, description,image;
    private int id,id_groupe,id_publicateur;
     public Publication_entities() {
    }
        public Publication_entities(String titre, String description,int id,String image,int id_groupe,int id_publicateur) {
        this.titre = titre;
        this.description = description;
        this.id=id;
        this.image=image;
        this.id_groupe=id_groupe;
        this.id_publicateur=id_publicateur;
       
    }

    public int getId() {
        return id;
    }

    public int getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(int id_groupe) {
        this.id_groupe = id_groupe;
    }

    public void setId_publicateur(int id_publicateur) {
        this.id_publicateur = id_publicateur;
    }

    public int getId_publicateur() {
        return id_publicateur;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        @Override
    public String toString() {
        return "Publication{" + "titre=" + titre + ", description=" + description + '}';
    }
    
}
