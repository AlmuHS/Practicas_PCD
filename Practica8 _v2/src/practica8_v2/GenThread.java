/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8_v2;

import static java.lang.Math.abs;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class GenThread extends Thread{

    Thread cars;
    Thread bus;
    CanvasParking cv;
    Random rand;
    Shared share;
    
    
    public GenThread(CanvasParking cv, Shared share){
        this.cv = cv;
        
        this.share = share;
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
    }
    
    public void launchThread() throws InterruptedException {
        int id = 1;
        
        while (true) {
            if(rand.nextInt() % 10 < 9){
                Car c = new Car(id, cv, share);
                cars = new Thread(c);
                cars.start();
            }
            else{
                Bus b = new Bus(id, cv, share);
                b.start();
            }
            id++;
            sleep(abs(rand.nextInt()%1000 + 500));
        }
        
    }
    
    @Override
    public void run(){
        try {
            launchThread();
        } catch (InterruptedException ex) {
            Logger.getLogger(GenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
