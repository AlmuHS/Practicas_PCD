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
public class Car implements Runnable {

    private int id;
    private CanvasParking cv;
    private ReentrantLock[] RLock;
    private Queue<Integer> BusQueue;
    private Condition[] mutex;

    public Car(int id, CanvasParking cv, ReentrantLock[] RL, Queue<Integer> BusQueue) {
        this.id = id;
        this.cv = cv;
        this.RLock = RL;
        this.BusQueue = BusQueue;
        mutex = new Condition[5];
        
        for (int i = 0; i < RL.length; i++) {
            mutex[i] = RLock[i].newCondition();
        }

    }

    @Override
    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int queue = 1;

        try {
            cv.inserta(1, id);
            sleep(abs(rand.nextInt() % 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = 0;
        Boolean find = false;

        while (!find) {
            find = RLock[i].tryLock();

            if (find && (i == 3 || i == 4)) {
                if (!BusQueue.isEmpty()) {
                    find = false;
                    RLock[i].unlock();
                } else {
                    queue = 2;
                }
            } else if (!find) {
                i = 1 + i % (RLock.length - 1);
            }
        }

        try {
            cv.quita(queue, id);
            sleep(500);
            cv.aparcacoche(id, queue);
            sleep(abs(rand.nextInt()) % 5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cv.salecoche(id, queue);
            RLock[i].unlock();
        }
    }

}
