/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Car implements Runnable {

    private int id;
    private CanvasParking cv;
    private ReentrantLock[] RLock;
    private Queue<Integer> BusQueue;
    private Condition mutex;

    public Car(int id, CanvasParking cv, ReentrantLock[] RL, Queue<Integer> BusQueue) {
        this.id = id;
        this.cv = cv;
        this.RLock = RL;
        this.BusQueue = BusQueue;
        mutex = RLock[1].newCondition();
    }

    @Override
    public void run() {
        Random rand = new Random();
        int cola = 1;

        try {
            cv.inserta(1, id);
            sleep(abs(rand.nextInt()%3000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }


        int i = 0;
        
        while (!RLock[i].tryLock()) {
            if (i < RLock.length - 1) {
                i++;
            } else {
                i = 0;
            }
        }

        if (i == 3 && BusQueue.isEmpty()) {
            cola = 2;
        } 
        
        try {
            cv.aparcacoche(id, cola);
            cv.quita(cola, id);
            sleep(abs(rand.nextInt()) % 5000);
            cv.salecoche(id, cola);

        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RLock[i].unlock();
        }
    }

}
