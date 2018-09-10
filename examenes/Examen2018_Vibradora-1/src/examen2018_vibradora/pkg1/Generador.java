/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_vibradora.pkg1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Generador {
    public static void main(String[] args) {
        // TODO code application logic here
        int numtornillos = 10;
        Thread[] Tornillos = new Thread[numtornillos];
        Vibradora V = new Vibradora();
        
        
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
        
        int idInox = 0;
        int idHierro = 0;
        
        for (int i = 0; i < Tornillos.length; i++) {
            if(rand.nextInt(10) < 4){
                Hierro H = new Hierro(idHierro, V);
                Tornillos[i] = new Thread(H);
                
                idHierro++;
            }
            else{
                Inoxidable I = new Inoxidable(idInox, V);
                Tornillos[i] = new Thread(I);
                
                idInox++;
            }
            
            Tornillos[i].start();
            
            try {
                sleep(rand.nextInt(2000) + 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (Thread Tornillo : Tornillos) {
            try {
                Tornillo.join();
            }catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
}
