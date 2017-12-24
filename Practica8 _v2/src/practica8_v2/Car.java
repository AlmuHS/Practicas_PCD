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

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Math.abs;
import static java.lang.Thread.sleep;

/**
 *
 * @author almu
 */
public class Car implements Runnable {

    private int id;
    private CanvasParking cv;
    
    private Monitor monitor;

    public Car(int id, CanvasParking cv, Monitor monitor) {
        this.id = id;
        this.cv = cv;
        this.monitor = monitor;
        
    }

    @Override
    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int queue = 1;

        try {
            cv.inserta(1, id);
            sleep(abs(rand.nextInt() % 1000 + 500));
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }

        queue = monitor.addCar();

        try {
            cv.quita(1, id);
            cv.aparcacoche(id, queue);
            
            sleep(abs(rand.nextInt()) % 2000 + 1000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cv.salecoche(id, queue);
            monitor.delCar(queue);
        }

    }

}
