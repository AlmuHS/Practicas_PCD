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
    Queue<Integer> CarQueue;
    private Condition[] mutex;

    public Car(int id, CanvasParking cv, ReentrantLock[] RL, Queue<Integer> BusQueue, Queue<Integer> CarQueue) {
        this.id = id;
        this.cv = cv;
        this.RLock = RL;
        this.BusQueue = BusQueue;
        this.CarQueue = CarQueue;
        mutex = new Condition[5];
        for (int i = 0; i < RL.length; i++) {
            mutex[i] = RLock[i].newCondition();
        }

    }

    @Override
    public void run() {
        Random rand = new Random();
        int cola = 1;

        try {
            cv.inserta(1, id);
            sleep(abs(rand.nextInt() % 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = 0;

        //CarQueue.offer(id);

        //while (CarQueue.peek().equals(id)) {}

        Boolean find = false;

        while (!find) {
            find = RLock[i].tryLock();

            if (find && i == 3) {
                if (!BusQueue.isEmpty()) {
                    find = false;
                    RLock[i].unlock();
                } else {
                    cola = 2;
                }
            } else if (!find) {
                i = 1 + i % (RLock.length - 1);
            }
        }

        try {
            cv.aparcacoche(id, cola);
            //CarQueue.poll();
            cv.quita(cola, id);
            sleep(abs(rand.nextInt()) % 5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cv.salecoche(id, cola);
            RLock[i].unlock();
        }
    }

}
