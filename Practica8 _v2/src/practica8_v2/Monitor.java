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

package practica8_v2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author almu
 */
public class Monitor {

    private int numParkedCar;
    private int numParkedCarinBus;
    private Boolean ParkedBus;
    private int BusWaiting;

    ReentrantLock RLock;
    private final Condition mutexCar;
    private final Condition mutexBus;

    public Monitor() {

        numParkedCar = 0;
        numParkedCarinBus = 0;
        ParkedBus = false;
        BusWaiting = 0;

        RLock = new ReentrantLock();
        mutexCar = RLock.newCondition();
        mutexBus = RLock.newCondition();
    }

    public int addCar() {
        int queue = 1;
        try {

            RLock.lock();

            if ((numParkedCar == 3) && (numParkedCarinBus == 2 || ParkedBus || BusWaiting > 0)) {
                mutexCar.await();
            }

            if (numParkedCar < 3) {
                queue = 1;
                numParkedCar++;
            } else {
                queue = 2;
                numParkedCarinBus++;
            }

            return queue;

        } catch (InterruptedException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RLock.unlock();
        }
        return 0;
    }

    public void delCar(int queue) {
        try {

            RLock.lock();

            if (queue == 1) {
                if (numParkedCar > 0) {
                    numParkedCar--;
                }

            } else if (queue == 2) {
                if (numParkedCarinBus > 0) {
                    numParkedCarinBus--;
                }
            }

            if (numParkedCarinBus == 0 && BusWaiting > 0) {
                mutexBus.signal();
            } else if (numParkedCarinBus >= 0 && BusWaiting == 0) {
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
            if (numParkedCarinBus > 0 || ParkedBus) {
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
