/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplowaitnotify;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class EjemploWaitNotify {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        
        Hilo[] h = new Hilo[5];
        Compartido c = new Compartido(0);
        
        for (int i = 0; i < 5; i++) {
            h[i] = new Hilo(i, c);
        }
        
        for (int i = 0; i < 5; i++) {
            h[i].start();
        }
        
        for (int i = 1; i < 5; i++) {
            try {
                h[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(EjemploWaitNotify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Fin de main");
        synchronized(c){
            c.notify();
        }
        
        synchronized(c){
            c.notify();
        }
        
    
    }//Fin main
   
}
