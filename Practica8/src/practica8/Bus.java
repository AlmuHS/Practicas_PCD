/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Bus implements Runnable {

    private int id;
    private CanvasParking cv;
    private ReentrantLock RLock;
    Condition empty;

    public Bus(int id, CanvasParking cv, ReentrantLock RL) {
        this.id = id;
        this.cv = cv;
        RLock = RL;
        empty = RLock.newCondition();
    }

    public void run() {
        cv.inserta(2, id);
        
        if (!RLock.tryLock()) {
            try {
                empty.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            RLock.lock();
            cv.quita(2, id);
            cv.aparcabus(id);
        } finally {
            RLock.unlock();
        }

    }

}
