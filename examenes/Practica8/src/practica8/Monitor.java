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
public class Monitor {

    int numParkedCar;
    Boolean ParkedBus;
    int numParkedCarInBus;
    int BusWaiting;

    ReentrantLock RLock;
    Condition mutexCar;
    Condition mutexBus;

    public Monitor() {
        numParkedCar = 0;
        ParkedBus = false;
        numParkedCarInBus = 0;
        BusWaiting = 0;

        RLock = new ReentrantLock();

        mutexCar = RLock.newCondition();
        mutexBus = RLock.newCondition();
    }

    public int addCar() {
        int queue = 1; //CarQueue = 1, BusQueue = 2;

        try {
            RLock.lock();

            if ((numParkedCar == 3) && (numParkedCarInBus == 2 || ParkedBus || BusWaiting > 0)) {
                mutexCar.await();
            }

            if (numParkedCar < 3) {
                queue = 1;
                numParkedCar++;
            } else {
                queue = 2;
                numParkedCarInBus++;
            }
            
            return queue;

        } catch (InterruptedException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RLock.unlock();
        }

        return queue;
    }

    public void delCar(int queue) {
        try {

            RLock.lock();

            if (queue == 1) {
                if (numParkedCar > 0) {
                    numParkedCar--;
                }

            } else if (queue == 2) {
                if (numParkedCarInBus > 0) {
                    numParkedCarInBus--;
                }
            }

            if ((numParkedCarInBus == 0) && BusWaiting > 0) {
                mutexBus.signal();
            } else if (numParkedCarInBus >= 0 && BusWaiting == 0) {
                mutexCar.signal();
            }

        } finally {
            RLock.unlock();
        }
    }

    public void addBus() {

        try {
            RLock.lock();
            BusWaiting++;
            if ((numParkedCarInBus > 0) || ParkedBus) {
                mutexBus.await();
            }

            BusWaiting--;
            ParkedBus = true;

        } catch (InterruptedException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RLock.unlock();
        }
    }

    public void delBus() {
        try {
            RLock.lock();

            ParkedBus = false;
            
            if (BusWaiting > 0) {
                mutexBus.signal();
            } else {
                mutexCar.signal();
            }

        } finally {
            RLock.unlock();
        }
    }

}
