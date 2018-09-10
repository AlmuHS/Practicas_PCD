/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_taller.pkg1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Coche extends Thread{
    int id;
    Taller T;
    
    public Coche(int id, Taller T){
        this.id = id;
        this.T = T;
    }
    
    public void run(){
        try {
            Random rand = new Random();
            rand.setSeed(System.currentTimeMillis());
            
            System.out.println("Entrando coche " + id + " al taller");
            
            
            T.EntraCoche();
            System.out.println("Arreglando coche " + id);
            sleep(rand.nextInt(4000) + 2000);
            
            System.out.println("Arreglo coche " + id + " terminado");
            T.SaleCoche();
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
