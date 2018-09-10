/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_mina.pkg1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Pequena extends Thread{
    Monton M;
    
    
    public Pequena(Monton M){
        this.M = M;
    }
    
    public void run(){
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
        System.out.println("Cargadora pequeña iniciada\n");
        
        
        for (int i = 0; i < 10; i++) {
            
            System.out.println("Cargadora pequeña esperando...\n");
            M.CargaPoco();
            System.out.println("Cargadora pequeña retira 1 Tm\n");
            
            try {
                sleep(rand.nextInt(3000)+1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pequena.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        System.out.println("Cargadora pequeña terminada");
        
    }
    
}
