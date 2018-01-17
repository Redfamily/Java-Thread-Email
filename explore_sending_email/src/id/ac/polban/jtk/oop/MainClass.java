/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.polban.jtk.oop;

import java.util.TreeMap;


/**
 *
 * @author Gerry Agustian
 */
public class MainClass
{
    /**
     * Menambahkan Pesan
     * @param messenger
     * @param receiver 
     */
    public static void addMessageTo(TreeMap<String, String> messenger, String receiver)
    {
       String generalMessage = "Anda telah mendaftar sebagai user portal Tenaga Kerja di PT Oil dan Gaass by Tugas OOP";
       String message = String.join(" ", "Hai", receiver, "Terimakasih", generalMessage);
       messenger.put(receiver, generalMessage);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       /**
        * Data Pengirim
        */
       String from = "redfamily@feduniverse.com"; //contoh, ganti dengan email masing2
       String host = "mail.feduniverse.com";
       String password = "rajinasli69"; //contoh, ganti dengan password masing2
        
       /**
        * Penerima dan Pesan
        */
       TreeMap<String, String> messengerOne;
       TreeMap<String, String> messengerTwo;
       messengerOne = new TreeMap<>();
       messengerTwo = new TreeMap<>();
            
       addMessageTo(messengerOne, "mufid.jamaluddin.tif16@polban.ac.id");
       addMessageTo(messengerOne, "gerryagustian97@gmail.com");
       addMessageTo(messengerOne, "mufid.jamaluddin@gmail.com");
       addMessageTo(messengerOne, "web.dianait@gmail.com");
       addMessageTo(messengerTwo, "mufid.jamaluddin.tif16@polban.ac.id");
       addMessageTo(messengerTwo, "azuregenzo@gmail.com");
       addMessageTo(messengerTwo, "jeremia.geraldi.tif16@polban.ac.id");
       addMessageTo(messengerOne, "web.dianait@gmail.com");
   
       /**
        * Thread
        */
       RunnableEmail runnableOne = new RunnableEmail(from, host, password, messengerOne, "1");
       RunnableEmail runnableTwo = new RunnableEmail(from, host, password, messengerTwo, "2");
       
       Thread threadOne;
       Thread threadTwo;

       threadOne = new Thread(runnableOne);

       threadTwo = new Thread(runnableTwo);
       
       threadOne.start();
       threadTwo.start();
   }
}
