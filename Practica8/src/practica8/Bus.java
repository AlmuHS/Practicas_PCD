/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import static java.lang.Thread.sleep;
import java.util.Queue;
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
    private ReentrantLock RLock;
    Queue<Integer> BusQueue;
    Condition empty;

    public Bus(int id, CanvasParking cv, ReentrantLock RL, Queue<Integer> BusQueue) {
        this.id = id;
        this.cv = cv;
        RLock = RL;
        this.BusQueue = BusQueue;
        empty = RLock.newCondition();
    }

    public void run() {
        try {
            cv.inserta(2, id);
            BusQueue.offer(id);
            sleep(2000);
            if (!RLock.tryLock()) {
                empty.wait();
            }
            
            try {
                cv.quita(2, id);
                sleep(1000);
                cv.aparcabus(id);
                sleep(1000);
                BusQueue.remove(id);
                cv.salebus();
            } finally {
                RLock.unlock();
            }
            
        }   catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
