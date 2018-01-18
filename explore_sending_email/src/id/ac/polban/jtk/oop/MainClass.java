/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.polban.jtk.oop;

import java.util.ArrayList;
import java.util.List;
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
       String message = TemplateEmail.getEmailContent(receiver);
       messenger.put(receiver, message);
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
        * ke 1000 user
        */     
       List<String> listEmail = new ArrayList<>();
       listEmail.add("mufid.jamaluddin.tif16@polban.ac.id"); // 1
       listEmail.add("gerryagustian97@gmail.com"); // 2
       listEmail.add("mufid.jamaluddin@gmail.com"); // 3
       listEmail.add("web.dianait@gmail.com"); // 4
       listEmail.add("azuregenzo@gmail.com"); // 5
       listEmail.add("jeremia.geraldi.tif16@polban.ac.id"); // 6
       listEmail.add("kivlan.aziz.tif16@polban.ac.id"); // 7
       listEmail.add("naufal.muntaaza.tif16@polban.ac.id"); // 8
       listEmail.add("said.alfakhri.tif16@polban.ac.id"); // 9
       listEmail.add("reza.dwi.tif16@polban.ac.id"); // 10
       
      // for(int i=0; i<990; i++)
      //      listEmail.add(i + "@auauauauauua");
       
       /**
        * 5 Core
        */
       TreeMap<String, String> messengerOne = new TreeMap<>();
       TreeMap<String, String> messengerTwo = new TreeMap<>();
       TreeMap<String, String> messengerThree = new TreeMap<>();
       TreeMap<String, String> messengerFour = new TreeMap<>();
       TreeMap<String, String> messengerFive = new TreeMap<>();
       
       int count = 0;
       for(String receiver : listEmail)
       {
           count++;
           int kel = count % 5;
           
           switch(kel)
           {
               case 0:
                   addMessageTo(messengerOne, receiver);
                   break;
               case 1:
                   addMessageTo(messengerTwo, receiver);
                   break;
               case 2:
                   addMessageTo(messengerThree, receiver);
                   break;
               case 3:
                   addMessageTo(messengerFour, receiver);
                   break;
               case 4:
                   addMessageTo(messengerFive, receiver);
                   break;
           }
       }
       
       /**
        * Thread
        */
       RunnableEmail runnable[] = new RunnableEmail[5];
       runnable[0] = new RunnableEmail(from, host, password, messengerOne, "1");
       runnable[1] = new RunnableEmail(from, host, password, messengerTwo, "2");
       runnable[2] = new RunnableEmail(from, host, password, messengerThree, "3");
       runnable[3] = new RunnableEmail(from, host, password, messengerFour, "4");
       runnable[4] = new RunnableEmail(from, host, password, messengerFive, "5");
       
       Thread thread[] = new Thread[5];
       
       for(int i=0; i<5; i++)
            thread[i] = new Thread(runnable[i]);
       
       for(int i=0; i<5; i++)
            thread[i].start();
       
   }
}
