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
import static java.lang.Thread.sleep;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Bus extends Thread {

    private int id;
    private CanvasParking cv;
    private ReentrantLock[] RLock;
    Queue<Integer> BusQueue;
    Condition empty;

    public Bus(int id, CanvasParking cv, ReentrantLock[] RL, Queue<Integer> BusQueue) {
        this.id = id;
        this.cv = cv;
        RLock = RL;
        this.BusQueue = BusQueue;
        //empty = RLock.newCondition();
    }

    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        try {
            cv.inserta(2, id);
            BusQueue.offer(id);
            sleep(abs(rand.nextInt()%3000) + 1000);
            
            Boolean free1 = false;
            Boolean free2 = false;
            
            while (!free1 || !free2) {
                free1 = RLock[0].tryLock();
                free2 = RLock[1].tryLock();
                
                if(!free1 && free2){
                    RLock[1].unlock();
                    free2 = false;
                }
                else if(free1 && !free2){
                    RLock[0].unlock();
                    free1 = false;
                }
            }
            
            try {
                cv.quita(2, id);
                sleep(1000);
                cv.aparcabus(id);
                sleep(abs(rand.nextInt() % 3000));
                BusQueue.remove(id);
                
            } finally {
                cv.salebus();
                RLock[0].unlock();
                RLock[1].unlock();
            }
            
        }   catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
