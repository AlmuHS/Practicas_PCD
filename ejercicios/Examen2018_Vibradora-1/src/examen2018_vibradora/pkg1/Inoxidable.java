/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_vibradora.pkg1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Inoxidable extends Thread{
    int id;
    Vibradora V;
    
    public Inoxidable(int id, Vibradora V){
        this.id = id;
        this.V = V;
    }
    
    @Override
    public void run(){
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
        System.out.println("Inoxidable" + id + " comenzado");
        
        System.out.println("Inoxidable " + id + " esperando");
        V.EntraInox();
        System.out.println("Inoxidable " + id + " en pulido");
        
        try {
            sleep(rand.nextInt(5000) + 3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Inoxidable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        V.SaleInox();
        System.out.println("Inoxidable " + id + " terminado");
        
    }
    
}
