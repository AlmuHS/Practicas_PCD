/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_mina.pkg1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Grande implements Runnable{
    Monton M;
    
    
    public Grande(Monton M){
        this.M = M;
    }
    
    @Override
    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
        System.out.println("Cargadora grande iniciada\n");
        
        for (int i = 0; i < 7; i++) {
            
            System.out.println("Cargadora grande esperando...\n");
            M.CargaMucho();
            System.out.println("Cargadora grande retira 3 Tm\n");
            
            try {
                sleep(rand.nextInt(4000)+2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pequena.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        System.out.println("Cargadora grande terminada");
    }
    
}
