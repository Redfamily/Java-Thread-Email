/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package explore_sending_email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author AGS
 */
public class Explore_sending_email {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //from diisi email pengirim
       String from = "gerryagustian97@gmail.com"; //contoh, ganti dengan email masing2
       
       //password diisi password email pengirim
       String password = "azuregenzo"; //contoh, ganti dengan password masing2
       String message = "test";
       
       //to diisi email2 recipient
       //String[] to = {"azuregenzo@gmail.com", "gerry.agustian.tif16@polban.ac.id"};
       String[] to = new String [5];
       for(int i = 0; i < 5; i++){
           to[i] = "azuregenzo@gmail.com";
       }
       System.out.println(Arrays.toString(to));
       if(emailSender.sendMail(from, password, message, to)){
           System.out.println("email sent");
       }
       else{
           System.out.println("failed");
       }
   }
}
