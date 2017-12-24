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
public class Bus extends Thread {

    private int id;
    private CanvasParking cv;
    Monitor monitor;

    public Bus(int id, CanvasParking cv, Monitor monitor) {
        this.id = id;
        this.cv = cv;
        this.monitor = monitor;
       
    }

    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        try {
            cv.inserta(2, id);
            sleep(abs(rand.nextInt() % 1000 + 500));
            
            monitor.addBus();
   
            cv.quita(2, id);
            sleep(100);
            cv.aparcabus(id);
            sleep(abs(rand.nextInt() % 2000 + 1000));

        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            cv.salebus();
            monitor.delBus();
        }

    }

}
