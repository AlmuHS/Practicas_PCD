/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author almu
 */
public class GenThread extends Thread{

    Thread cars;
    Thread bus;
    CanvasParking cv;
    ReentrantLock RL;

    public GenThread(CanvasParking cv, ReentrantLock RL){
        this.cv = cv;
        this.RL = RL;
    }
    
    public void launchThread() {
        int id = 1;
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
        while (true) {
            if(rand.nextInt() % 10 < 9){
                Car c = new Car(id, cv, RL);
                cars = new Thread(c);
                cars.start();
            }
            else{
                Bus b = new Bus(id, cv, RL);
                bus = new Thread(bus);
                bus.start();
            }
            id++;
        }
    }
    
    @Override
    public void run(){
        launchThread();
    }
}
