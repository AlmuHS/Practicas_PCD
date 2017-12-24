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

/**
 *
 * @author almu
 */
public class GenThread extends Thread{

    Thread cars;
    Thread bus;
    CanvasParking cv;
    Random rand;
    Monitor monitor;
    
    
    public GenThread(CanvasParking cv, Monitor monitor){
        this.cv = cv;
        
        this.monitor = monitor;
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
    }
    
    public void launchThread() throws InterruptedException {
        int id = 1;
        
        while (true) {
            if(rand.nextInt() % 10 < 9){
                Car c = new Car(id, cv, monitor);
                cars = new Thread(c);
                cars.start();
            }
            else{
                Bus b = new Bus(id, cv, monitor);
                b.start();
            }
            id++;
            sleep(abs(rand.nextInt()%1000 + 500));
        }
        
    }
    
    @Override
    public void run(){
        try {
            launchThread();
        } catch (InterruptedException ex) {
            Logger.getLogger(GenThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
