/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Evennement;
import entities.Membre;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static java.util.Spliterators.iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionDB;
import utils.JavaMail;
import static utils.JavaMail.sendMail;

/**
 *
 * @author syrine
 */

public class ServiceEvennement{
    Connection c=ConnexionDB.getInstance().getCnx(); 
    public void pdf() throws FileNotFoundException
    {
        try {
            String file_name ="C:\\Users\\syrine\\Desktop\\pdf\\Ssyrine.pdf";
            Document document = new Document();
            try {
                //file_name.setReadable(true,false);
                PdfWriter.getInstance(document, new FileOutputStream(file_name));
            } catch (DocumentException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open();
            PreparedStatement pt = c.prepareStatement("select * from Evenement");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) { 
                Paragraph para=new Paragraph("Evenement [ id: " +rs.getInt(2) + " name : " + rs.getString(3) + " lieu: " + rs.getString(4)+" date: " + rs.getDate(5)+"]");
                //System.out.println("garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
                document.add(para);
                document.add(new Paragraph(" "));
            }
            document.close();
        } catch (SQLException ex) {
            Logger.getLogger(Evennement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Evennement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Evennement.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    
     public List<Evennement> rechercherEvent (String auteur){
        
        String requete="select * FROM evenement where (nom LIKE ? )";
      
        String ch="%"+auteur+"%";
        ArrayList<Evennement> myList = new ArrayList();
        try {
            
             PreparedStatement pst = c.prepareStatement(requete);
             pst.setString(1,ch);
              
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Evennement e=new Evennement(rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10));
           
                myList.add(e);
                
                
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }
    
    public void ajouterEvennement(Evennement e) 
    {
        try {
            DateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
            java.util.Date d=dateFormat.parse(e.getHeure());
            java.sql.Time t= new java.sql.Time(d.getTime());

            String requete="insert into Evenement (idc,nom,lieu,date,heure,description,siteWeb,NbParticipant,NbPlace,image,etat) values (?,?,?,?,?,?,?,?,?,?,?)";
            try {
                
                
                
                PreparedStatement pst=c.prepareStatement(requete);
                pst.setInt(1,e.getIdc());
                pst.setString(2, e.getNom());
                pst.setString(3, e.getLieu());
                pst.setString(4, e.getDate());
                pst.setTime(5,t);
                pst.setString(6,e.getDesciption());
                pst.setString(7,e.getSiteWeb());
                pst.setInt(8,e.getNbParticipant());
                pst.setInt(9,e.getNbPlace());
                pst.setString(10, e.getImage());
                pst.setInt(11, 0);
                pst.executeUpdate();
                try {
                    Notification.sendNotification("module evennement", "evennement ajouté ",TrayIcon.MessageType.INFO);
                } catch (AWTException ex) {
                    Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    public List<Evennement> afficherdmd(){
        List<Evennement> arr=new ArrayList<>();
        try {
            PreparedStatement pt=c.prepareStatement("select * from Evenement WHERE etat=? ");
            pt.setInt(1,0);
            ResultSet rs=pt.executeQuery();
            while (rs.next()) {
                int idc=rs.getInt(1);
                int ide=rs.getInt(2);
                String nom=rs.getString(3);
                String lieu=rs.getString(4);
                String date=rs.getString(5);
                String heure=rs.getString(6);
                String description=rs.getString(7);
                String siteweb=rs.getString(8);
                int Nbparticipant=rs.getInt(9);
                int NbPlace=rs.getInt(10);
                String image=rs.getString(11);
                
                Evennement e=new Evennement(idc,ide,nom, lieu,date,heure,description,siteweb,Nbparticipant,NbPlace);
              
                arr.add(e);
               
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
  
         return arr;
    }
    public List<Evennement> afficherMesEvenements(int id){
        List<Evennement> arr=new ArrayList<>();
        try {
            PreparedStatement pt=c.prepareStatement("select * from Evenement WHERE etat=? AND idc=?");
            pt.setInt(1,1);
            pt.setInt(2,id);
            ResultSet rs=pt.executeQuery();
            while (rs.next()) {
                int idc=rs.getInt(1);
                int ide=rs.getInt(2);
                String nom=rs.getString(3);
                String lieu=rs.getString(4);
                String date=rs.getString(5);
                String heure=rs.getString(6);
                String description=rs.getString(7);
                String siteweb=rs.getString(8);
                int Nbparticipant=rs.getInt(9);
                int NbPlace=rs.getInt(10);
                String image=rs.getString(11);
                Evennement e=new Evennement(ide,nom, lieu,date,heure,description,siteweb,Nbparticipant,NbPlace);
                arr.add(e);
               
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
  
         return arr;
    }
    
    

    public List<Evennement> affichertout()
    {
        List<Evennement> arr=new ArrayList<>();
        try {
            
            Statement ste=c.createStatement();
            ResultSet rs=ste.executeQuery("select * from Evenement");
            while (rs.next()) {
                int idc=rs.getInt(1);
                int ide=rs.getInt(2);
                String nom=rs.getString(3);
                String lieu=rs.getString(4);
                String date=rs.getString(5);
                String heure=rs.getString(6);
                String description=rs.getString(7);
                String siteweb=rs.getString(8);
                int Nbparticipant=rs.getInt(9);
                int NbPlace=rs.getInt(10);
                String image=rs.getString(11);
                Evennement e=new Evennement(ide,nom, lieu,date,heure,description,siteweb,Nbparticipant,NbPlace);
                arr.add(e);
               
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
  
         return arr;
        
    }
      public List<Evennement> affichertout2()
    {
        List<Evennement> arr=new ArrayList<>();
        try {
            
             
        PreparedStatement ste=c.prepareStatement("select * from Evenement where etat=?");
        ste.setInt(1, 1);
         ResultSet rs=ste.executeQuery();
        
            while (rs.next()) {
                int idc=rs.getInt(1);
                int ide=rs.getInt(2);
                String nom=rs.getString(3);
                String lieu=rs.getString(4);
                String date=rs.getString(5);
                String heure=rs.getString(6);
                String description=rs.getString(7);
                String siteweb=rs.getString(8);
                int Nbparticipant=rs.getInt(9);
                int NbPlace=rs.getInt(10);
                String image=rs.getString(11);
                Evennement e=new Evennement(idc,ide,nom, lieu,date,heure,description,siteweb,Nbparticipant,NbPlace,image);
                arr.add(e);
                try {
                    pdf();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
  
         return arr;
    }
    
    
   
    public List<Evennement> readAll(){
        List<Evennement> arr=new ArrayList<>();
        try {
            
            Statement ste=c.createStatement();
            ResultSet rs=ste.executeQuery("select * from Evenement");
            while (rs.next()) {
                int idc=rs.getInt(1);
                int ide=rs.getInt(2);
                String nom=rs.getString(3);
                String lieu=rs.getString(4);
                String date=rs.getString(5);
                String heure=rs.getString(6);
                String description=rs.getString(7);
                String siteweb=rs.getString(8);
                int Nbparticipant=rs.getInt(9);
                int NbPlace=rs.getInt(10);
                String image=rs.getString(11);
                Evennement e=new Evennement(nom, lieu);
                arr.add(e);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
  
         return arr;
    }



 
    public void supprimerEvennement(Evennement t)  {
         
         try {
            PreparedStatement pt=c.prepareStatement("delete from Evenement where nom=? ");
            pt.setString(1,t.getNom());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void modifierEvennement(Evennement t) {
        try {
            DateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
            java.util.Date d=dateFormat.parse(t.getHeure());
            java.sql.Time ti= new java.sql.Time(d.getTime());
            //java.sql.Date date=new java.sql.Date(t.getDate().getTime());
            try {
                PreparedStatement pt=c.prepareStatement("update Evenement set nom=?,lieu=?,date=?,heure=?,description=?,siteWeb=?,NbParticipant=?,NbPlace=? where idE=?");
                pt.setString(1,t.getNom());
                pt.setString(2,t.getLieu());
                pt.setString(3, t.getDate());
                pt.setTime(4, ti);
                pt.setString(5,t.getDesciption());
                pt.setString(6,t.getSiteWeb());
                pt.setInt(7, t.getNbParticipant());
                pt.setInt(8, t.getNbPlace());
                //pt.setString(8, t.getImage());
                pt.setInt(9, t.getIdE());
                pt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
      public void AccepterEvenement2(Evennement t,int id) {
     
            try {
                PreparedStatement pt=c.prepareStatement("update Evenement set etat=? where idE=?");
                pt.setInt(1,1);
                pt.setInt(2,t.getIdE());
                pt.executeUpdate();
                 ServiceMembre srv=new ServiceMembre();
             try {
                 sendMail(srv.rechercherM(id).getMail(),"Event :"+t.getNom()+".","your event has been accepted");
             } catch (Exception ex) {
                 Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
             }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
      public void RefuserEvenement(Evennement t)
      {
          try {
                PreparedStatement pt=c.prepareStatement("delete from Evenement where idE=?");
                pt.setInt(1,t.getIdE());
                pt.executeUpdate();
               ServiceMembre srv=new ServiceMembre();
             try {
                 sendMail(srv.rechercherM(t.getIdc()).getMail(),"Event :"+t.getNom()+".","your event has been not accepted");
             } catch (Exception ex) {
                 Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
             }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
       public void AccepterEvenement(Evennement t) {
     
            try {
                PreparedStatement pt=c.prepareStatement("update Evenement set etat=? where idE=?");
                pt.setInt(1,1);
                pt.setInt(2,t.getIdE());
                pt.executeUpdate();
               ServiceMembre srv=new ServiceMembre();
             try {
                 sendMail(srv.rechercherM(t.getIdc()).getMail(),"Event :"+t.getNom()+".","your event has been accepted");
             } catch (Exception ex) {
                 Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
             }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void afficherusertrier() {
        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM `Evenement` ORDER BY NbParticipant Desc ");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getObject(i).toString() + " ");
                }
                System.out.println("\n");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public Evennement RechercherEvennement(Evennement A) throws SQLException {
    Evennement e= new Evennement();
        try{ 
        
        String requete="select * from Evenement where idE="+A.getIdE()+" ";
        PreparedStatement pst=c.prepareStatement(requete);
         ResultSet rs=pst.executeQuery();
         
       
        while(rs.next()){
            e=new Evennement(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getString(11));
           //System.out.println(A);
        } 
        return e;
     } catch (SQLException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return null;
    }
    
    
    public Boolean DisjoindreEvenement(Evennement e,int id)
    {
        try {
            String requete="select * from ListParticipant where idE=? and idm=?";
            PreparedStatement pst=c.prepareStatement(requete);
            pst.setInt(1, e.getIdE());
            pst.setInt(2, id);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
                {
                   
                    e.setNbParticipant(e.getNbParticipant()-1);
                    e.setNbPlace(e.getNbPlace()+1);
                    this.modifierEvennement(e);
                    
                    PreparedStatement pt=c.prepareStatement("delete from listparticipant where idE=? and idm=?");
                        pt.setInt(1,e.getIdE());
                        pt.setInt(2,id);
                        pt.executeUpdate();
                        return true;
                }
         
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
          return false; 
    }
    
    public Boolean RejoindreEvenement(Evennement e,int id)
    {
        if(e.getNbPlace()!=0)
        {
            try {
                String requete="select * from listparticipant where idE=? and idm=?";
                PreparedStatement pst=c.prepareStatement(requete);
                pst.setInt(1, e.getIdE());
                pst.setInt(2, id);
                ResultSet rs=pst.executeQuery();
                
                
                
                if(!rs.next())
                {
<<<<<<< HEAD
                    int x=e.getNbParticipant()+1;
                    e.setNbParticipant(x);
                    e.setNbPlace(e.getNbPlace()-1);
                    this.modifierEvennement(e);
                    try {

                      //  JavaMail.sendMailEvenement(m.getMail());

                        JavaMail.sendMail(m.getMail(),"Bienvenue a"+e.getNom(),"Bienvenue azueaz_eh");

                    } catch (Exception ex) {
                        Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
                    }
=======
                   
>>>>>>> 64063f71f7659bbbf44e2eb4cda11b7368aa3ff3
                    try {
                        
                        PreparedStatement pt=c.prepareStatement("insert into listparticipant (idE,idm,etatp) values (?,?,?)");
                        pt.setInt(1,e.getIdE());
                        pt.setInt(2, id);
                        pt.setInt(3, 0);
                        pt.executeUpdate();
                        return true;
                    } catch (SQLException ex) {
                        Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                }   } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        return false;
    }
    
    public void RefuserParticipant(Evennement e,int id)
    {
         try {
                PreparedStatement pt=c.prepareStatement("delete from listparticipant WHERE idE=? AND idm=?");
                pt.setInt(1,e.getIdE());
                pt.setInt(2,id);
                pt.executeUpdate();
                    ServiceMembre srv=new ServiceMembre();
             try {
                 sendMail(srv.rechercherM(id).getMail(),"Event "+e.getNom(),"your invitation has been not accepted");
             } catch (Exception ex) {
                 Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
             }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    public void AccepterParticipant(Evennement e,int id){
         try {
                PreparedStatement pt=c.prepareStatement("update listparticipant set etatp=? where idE=? AND idm=?");
                pt.setInt(1,1);
                pt.setInt(2,e.getIdE());
                pt.setInt(3,id);
                pt.executeUpdate();
                 int x=e.getNbParticipant()+1;
                 e.setNbParticipant(x);
                 e.setNbPlace(e.getNbPlace()-1);
                    this.modifierEvennement(e);
                    ServiceMembre srv=new ServiceMembre();
             try {
                 sendMail(srv.rechercherM(id).getMail(),"Event "+e.getNom(),"Welcome to our event");
             } catch (Exception ex) {
                 Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
             }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
public boolean RechercherMembre(Evennement e,int id)
{
        try {
            String requete="select * from listparticipant where idE=? and idm=? and etatp=?";
            PreparedStatement pst=c.prepareStatement(requete);
            pst.setInt(1, e.getIdE());
            pst.setInt(2, id);
            pst.setInt(3, 1);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
                return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
}
public boolean RechercherMembre2(Evennement e,int id)
{
        try {
            String requete="select * from listparticipant where idE=? and idm=? ";
            PreparedStatement pst=c.prepareStatement(requete);
            pst.setInt(1, e.getIdE());
            pst.setInt(2, id);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
                return false;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;
}
    public List<Integer> ListParticipantdmd(Evennement e)
    {   List<Integer> arr=new ArrayList<>();
         try {
            
            PreparedStatement ps = c.prepareStatement("select idm from listparticipant WHERE idE=? and etatp=?");
            ps.setInt(1, e.getIdE());
             ps.setInt(2, 0);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int idm=rs.getInt(1);
                arr.add(idm);
            }
            return arr;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
      public List<Integer> ListParticipant(Evennement e)
    {   List<Integer> arr=new ArrayList<>();
         try {
            
            PreparedStatement ps = c.prepareStatement("select idm from listparticipant WHERE idE=? and etatp=?");
            ps.setInt(1, e.getIdE());
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int idm=rs.getInt(1);
                arr.add(idm);
            }
            return arr;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
public List<Membre> AfficherParticipantdmd(Evennement e){
    
    List<Membre> arr=new ArrayList<>();
    List<Integer> liste=ListParticipantdmd(e);
    
        try {
            for(int i=0;i<liste.size();i++)
    {
            PreparedStatement ps = c.prepareStatement("select * from Membre  WHERE idUsr=? ");
             ps.setInt(1, liste.get(i));
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                 int idm=rs.getInt(1);
                String nom=rs.getString(2);
                String prenom=rs.getString(3);
               //int tel=rs.getInt(4);
                String email=rs.getString(5);
               // String login=rs.getString(6);
                //String mdp=rs.getString(7);
                //int age=rs.getInt(8);
                String formation=rs.getString(9);
                String experience=rs.getString(10);
                Membre m=new Membre(experience,formation,nom,prenom,email,idm);
                arr.add(m);
            }}
            return arr;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
}
public List<Membre> AfficherParticipant(Evennement e){
    
    List<Membre> arr=new ArrayList<>();
    List<Integer> liste=ListParticipant(e);
    
        try {
            for(int i=0;i<liste.size();i++)
    {
            PreparedStatement ps = c.prepareStatement("select * from Membre  WHERE idUsr=? ");
             ps.setInt(1, liste.get(i));
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                int idm=rs.getInt(1);
                String nom=rs.getString(2);
                String prenom=rs.getString(3);
               //int tel=rs.getInt(4);
                String email=rs.getString(5);
               // String login=rs.getString(6);
                //String mdp=rs.getString(7);
                //int age=rs.getInt(8);
                String formation=rs.getString(9);
                String experience=rs.getString(10);
                Membre m=new Membre(experience,formation,nom,prenom,email,idm);
                arr.add(m);
            }}
            return arr;
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvennement.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
}
    
}
