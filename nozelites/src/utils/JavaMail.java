/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author syrine
 */
public class JavaMail {

    /*public static void sendMailEvenement(String recepient) throws Exception{
        System.out.println("in progress");
        
    Properties properties = new Properties();
    properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable","true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");
    String myAccount="nozelitesa3@gmail.com";
    String password="nozelites123456";
    
    Session session=Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(myAccount, password);
        }      
});
   Message message=prepareMessageEvenement(session,myAccount,recepient);
        Transport.send(message);
        System.out.println("sent successfully");
        
    
  
    }
*/
       
    
    public static void sendMail(String recepient,String objet,String texte) throws Exception{

        System.out.println("in progress");

        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myAccount="nozelitesa3@gmail.com";
        String password="nozelites123456";

        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(myAccount, password);
            }      
        });

       Message message=prepareMessage(session,myAccount,recepient,objet,texte);
            Transport.send(message);
            System.out.println("sent successfully");   
    }
    
    
    private static Message prepareMessage(Session session,String myAccount,String recepient,String objet,String texte)
    {
        try {
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

            message.setSubject("Reclamation Recu");
            message.setText("Votre Réclamation est en cours de traitement");

            message.setSubject(objet);
            message.setText(texte);

            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
          return null;
    }
    
}
