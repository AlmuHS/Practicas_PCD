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
public class Hierro implements Runnable{
    int id;
    Vibradora V;
    
    public Hierro(int id, Vibradora V){
        this.id = id;
        this.V = V;
    }
    
    @Override
    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
        
        System.out.println("Hierro " + id + " comenzado");
        
        System.out.println("Hierro " + id + " esperando...");
        V.EntraHierro();
        System.out.println("Hierro " + id + " en pulido");
        
        try {
            sleep(rand.nextInt(5000) + 2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hierro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        V.SaleHierro();
        System.out.println("Hierro " + id + " terminado");
        
    }
    
}
