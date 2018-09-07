/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class GenThread extends Thread{
    Monitor M;
    CanvasParking CP;
    Thread cars;
    Thread bus;
    Random rand;
 
    public GenThread(CanvasParking CP, Monitor M){
        this.M = M;
        this.CP = CP;
        rand = new Random();
    }
    
    
    public void launchThread() throws InterruptedException{
        int id = 1;
        
        while(true){
            
            if(rand.nextInt(10) < 9){
               Car C = new Car(id, CP, M);
                cars = new Thread(C);
                cars.start(); 
            }
            else{
                Bus B = new Bus(id, CP, M);
                bus = new Thread(B);
                bus.start();
            }
            id++;
            sleep(rand.nextInt(2000));
            
        }
    }
    
    public void run(){
        
        try {
            launchThread();
        } catch (InterruptedException ex) {
            Logger.getLogger(GenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
