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
    private final boolean isConnect;
    
    /**
     * Konstruktor
     * @param from
     * @param host
     * @param password 
     * @param messenger 
     * @param threadName 
     */
    public RunnableEmail(String from, String host, String password, Map<String, String> messenger, String threadName)
    {
        this.emailSender = new EmailSender(host, "587", from, password, "smtp");
        this.messenger = messenger;
        this.iter = this.messenger.entrySet().iterator();
        this.isConnect = this.emailSender.prepareSendMail(this.messenger.size());
        System.out.println("Thread " + threadName + " Dibuka");
        this.threadname = threadName;
    }
    
    /**
     * Mengirimkan Email
     */
    public void sendAllEmail()
    {
        if(this.isConnect)
        {
            int failEmail = 0;

            while(this.iter.hasNext()) 
            {
                Map.Entry<String,String> entry = (Map.Entry<String,String>) iter.next();
                String receiver = entry.getKey();
                String message = entry.getValue();

               // System.out.println("\nThread " + this.threadname  +"\nMengirim ke : " + receiver);

                boolean sendOneEmail = this.emailSender.sendOneEmail(receiver, message);

                if(sendOneEmail)
                {
                    System.out.println("Thread " + this.threadname + " : Email ke " + receiver + " Terkirim");
                }
                else
                {
                    System.out.println("Thread " + this.threadname + " : Email ke " + receiver + " Gagal Terkirim");
                    failEmail++;
                }
            }
            if(!this.iter.hasNext())
            {
                int successEmail = this.messenger.size() - failEmail;
                // pemberitahuan
                System.out.println("\nThread " + this.threadname + " Ditutup");
                System.out.println("Thread " + this.threadname + "- Terkirim \t\t: " + successEmail + " Email");
                System.out.println("Thread " + this.threadname + "- Gagal Terkirim \t: " + failEmail + " Email");
                // tututp koneksi email
                this.emailSender.closeConnection();
                // tutup thread
                Thread.interrupted();
            }
        }
        else
            System.out.println("Gagal Mengirimkan " + this.messenger.size() + " Email Karena Masalah Koneksi");
    }
    
    /**
     * 
     * Run
     */
    @Override
    public void run()
    {
        this.sendAllEmail();
    }

}
