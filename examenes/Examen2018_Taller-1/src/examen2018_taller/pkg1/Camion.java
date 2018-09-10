/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_taller.pkg1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Camion implements Runnable{
    int id;
    Taller T;
    
    public Camion(int id, Taller T){
        this.id = id;
        this.T = T;
    }
    
    
    @Override
    public void run() {
        try {
            Random rand = new Random();
            rand.setSeed(System.currentTimeMillis());
            
            System.out.println("Entrando camion " + id + " al taller");
            
            T.EntraCamion();
            System.out.println("Arreglando camion " + id);
            sleep(rand.nextInt(4000) + 2000);
            
            System.out.println("Terminado arreglo camion " + id);  
            T.SaleCamion();
                      
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Camion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
