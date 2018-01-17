/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.polban.jtk.oop;

import java.util.Iterator;
import java.util.Map;

/**
 * SMTP 587
 * @author mufidjamaluddin
 */
public class RunnableEmail implements Runnable
{
    /**
     * Atribut
     */
    private final EmailSender emailSender;
    private final Map<String, String> messenger;
    private final Iterator iter;
    private final String threadname;
    
    /**
     * Konstruktor
     * @param from
     * @param host
     * @param password 
     * @param messenger 
     */
    public RunnableEmail(String from, String host, String password, Map<String, String> messenger, String threadName)
    {
        this.emailSender = new EmailSender(host, "587", from, password, "smtp");
        this.messenger = messenger;
        this.iter = this.messenger.entrySet().iterator();
        this.emailSender.prepareSendMail(this.messenger.size());
        System.out.println("Thread " + threadName + " Dibuka");
        this.threadname = threadName;
    }
    
    /**
     * 
     * Run
     */
    @Override
    public void run()
    {
        while(this.iter.hasNext()) 
        {
            Map.Entry<String,String> entry = (Map.Entry<String,String>) iter.next();
            String receiver = entry.getKey();
            String message = entry.getValue();
            
            System.out.println("\nThread " + this.threadname  +"\n Mengirim ke : " + receiver + "\nPesan : " + message);
            
            boolean sendOneEmail = this.emailSender.sendOneEmail(receiver, message);
            
            if(sendOneEmail)
                System.out.println("Email ke " + receiver + " Terkirim");
            else
                System.out.println("Email ke " + receiver + " Gagal Terkirim");
        }
        if(!this.iter.hasNext())
        {
            System.out.println("\nThread " + this.threadname + " Ditutup");
            this.emailSender.closeConnection();
            Thread.interrupted();
        }
    }

}
