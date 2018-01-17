/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package explore_sending_email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author AGS
 */
public class emailSender {
    public static boolean sendMail(String from, String password, String message, String to[]){
        //use gmail as host server
        String host = "smtp.gmail.com";
        Properties props = System.getProperties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props, null);
        
        MimeMessage mimeMessage = new MimeMessage(session);
        
        try{
            mimeMessage.setFrom(new InternetAddress(from));
            
            //now get the address of recipient assign it to toAddress[]
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for(int i=0; i < to.length; i++){
                toAddress[i] = new InternetAddress(to[i]);
            }
            
            //loop for set the message recipient
            /*for(int i=0; i < toAddress.length; i++){
                mimeMessage.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }*/
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            for(int i = 0; i < toAddress.length; i++){
                mimeMessage.setRecipient(Message.RecipientType.TO, toAddress[i]);
                mimeMessage.setSubject(String.format("subject %d", i));
                mimeMessage.setText(message);
                transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
            }
            //mimeMessage.setSubject("coba javamail api");
            /*mimeMessage.setText(message);
            
            *Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());*/
            transport.close();
            return true;
        }
        catch(MessagingException me){
            me.printStackTrace();
        }
        return false;
    }
}
