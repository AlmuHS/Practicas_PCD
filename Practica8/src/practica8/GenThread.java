/*
 * Copyright (C) 2017 Almudena García Jurado-Centurión
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
    ReentrantLock[] RLBus;
    
    
    public GenThread(CanvasParking cv, ReentrantLock[] RL, Queue<Integer> BusQueue){
        this.cv = cv;
        this.RL = new ReentrantLock[4];
        this.RL = RL;
        this.BusQueue = BusQueue;
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        RLBus = new ReentrantLock[2];
    }
    
    public void launchThread() throws InterruptedException {
        int id = 1;
        
        for(int i = 0; i < RL.length; i++){
            RL[i] = new ReentrantLock();
        }
        
        while (true) {
            if(rand.nextInt() % 10 < 9){
                Car c = new Car(id, cv, RL, BusQueue);
                cars = new Thread(c);
                cars.start();
            }
            else{
                RLBus[0] = RL[3];
                RLBus[1] = RL[4];
          
                bus = new Bus(id, cv, RLBus, BusQueue);
                bus.start();
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
