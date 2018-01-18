/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.polban.jtk.oop;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Gerry Agustian, Mufid Jamaluddin
 */
public class EmailSender 
{
    /**
     * Atribut
     */
    private final Properties props;
    private final Session session;
    private final MimeMessage mimeMessage;
    private final String host;
    private final String from;
    private final String password;
    private int jmlMessage;
    private Transport transport;
    
    /**
     * Instansiasi berdasarkan email pengirim
     * @param host
     * @param port
     * @param from
     * @param password 
     * @param typeTransport 
     */
    public EmailSender(String host, String port, String from, String password, String typeTransport)
    {
        this.host = host;
        this.from = from;
        this.password = password;
        
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        
        this.props = properties;
        
        this.session = Session.getDefaultInstance(props, null);
        
        this.mimeMessage = new MimeMessage(session);
        
        try 
        {
            this.transport = session.getTransport(typeTransport);
        } 
        catch (NoSuchProviderException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            this.transport = null;
        }
    }
    
    /**
     * Mengirim email berdasarkan map penerima dan isi pesan
     * @param messenger
     * @return 
     */
    public boolean sendBanyakMail(Map<String, String> messenger) 
    {
        /**
         * Penerima
         */
        Set<String> receivers;
        receivers = messenger.keySet();
        
     
        try 
        {
            this.mimeMessage.setFrom(new InternetAddress(from));
        } 
        catch (AddressException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                 
        InternetAddress[] toAddress = new InternetAddress[messenger.size()];

        int count = 0;
        for(String receiver : receivers)
        {
            try 
            {
                toAddress[count] = new InternetAddress(receiver);
                count++;
            } 
            catch (AddressException ex) 
            {
                Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

      /*  try 
        {
          //  this.transport.connect(this.host, this.from, this.password);
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     */       
        count = 0;
        for(String receiver : receivers)
        {
            try 
            {
                this.mimeMessage.setRecipient(Message.RecipientType.TO, toAddress[count]);
                this.mimeMessage.setSubject(String.join(" ","Hi", receiver));
                this.mimeMessage.setContent(messenger.get(receiver), "text/html");
                // message.setContent("<h1>sending html mail check</h1>","text/html" );  
                this.transport.sendMessage(this.mimeMessage, this.mimeMessage.getRecipients(Message.RecipientType.TO));

            } 
            catch (MessagingException ex) 
            {
                Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try 
        {
            //mimeMessage.setSubject("coba javamail api");
            /*mimeMessage.setText(message);
            
            *Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());*/
            this.transport.close();
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return true;

    }
    
    /**
     * Mengirim email berdasarkan map penerima dan isi pesan
     * @param jmlMessenger
     * @return 
     */
    public boolean prepareSendMail(int jmlMessenger) 
    {
        this.jmlMessage = jmlMessenger;
        
        try 
        {
            this.mimeMessage.setFrom(new InternetAddress(from));
        } 
        catch (AddressException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                 
        try 
        {
            this.transport.connect(this.host, this.from, this.password);
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
         
    }
    
    /**
     * Mengirim Satu Email
     * @param receiver
     * @param message
     * @return 
     */
    public boolean sendOneEmail(String receiver, String message)
    {
        try 
        {
            InternetAddress toAddress = new InternetAddress(receiver);
            
            this.mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
            this.mimeMessage.setSubject(String.join(" ","Hi", receiver));
            this.mimeMessage.setContent(message, "text/html");
            // message.setContent("<h1>sending html mail check</h1>","text/html" );  
            this.transport.sendMessage(this.mimeMessage, this.mimeMessage.getRecipients(Message.RecipientType.TO));
                
            return true;
        } 
        catch (AddressException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    /**
     * Tutup Koneksi
     * @return 
     */
    public boolean closeConnection()
    {
        try 
        {
            this.transport.close();
            return true;
        } 
        catch (MessagingException ex) 
        {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
            
}
