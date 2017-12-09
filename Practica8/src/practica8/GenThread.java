/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import static java.lang.Math.abs;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
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
    ReentrantLock[] RL;
    Queue<Integer> BusQueue;
    Random rand;
    
    
    public GenThread(CanvasParking cv, ReentrantLock[] RL, Queue<Integer> BusQueue){
        this.cv = cv;
        this.RL = new ReentrantLock[4];
        this.RL = RL;
        this.BusQueue = BusQueue;
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
    }
    
    public void launchThread() throws InterruptedException {
        int id = 1;
        
        for(int i = 0; i < 4; i++){
            RL[i] = new ReentrantLock();
        }
        
        while (true) {
            if(rand.nextInt() % 10 < 9){
                Car c = new Car(id, cv, RL, BusQueue);
                cars = new Thread(c);
                cars.start();
            }
            else{
                Bus b = new Bus(id, cv, RL[3], BusQueue);
                b.start();
            }
            id++;
            sleep(abs(rand.nextInt()%2000 + 500));
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
