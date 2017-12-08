/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author almu
 */
public class Bus implements Runnable {

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
        cv.inserta(2, id);
        
        while (!RLock.tryLock()) {
            /*try {
                empty.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
        
        try {
            cv.quita(2, id);
            cv.aparcabus(id);
        } finally {
            RLock.unlock();
        }

    }

}
