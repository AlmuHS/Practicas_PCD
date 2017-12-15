/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8_v2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Math.abs;

/**
 *
 * @author almu
 */
public class GenThread extends Thread{

    Thread cars;
    Thread bus;
    CanvasParking cv;
    Random rand;
    Monitor monitor;
    
    
    public GenThread(CanvasParking cv, Monitor monitor){
        this.cv = cv;
        
        this.monitor = monitor;
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
    }
    
    public void launchThread() throws InterruptedException {
        int id = 1;
        
        while (true) {
            if(rand.nextInt() % 10 < 9){
                Car c = new Car(id, cv, monitor);
                cars = new Thread(c);
                cars.start();
            }
            else{
                Bus b = new Bus(id, cv, monitor);
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
