package practica8;

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

import java.applet.Applet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author almu
 */
public class AppletParking extends Applet {
    GenThread GT;
    CanvasParking CP;
    ReentrantLock[] RLPark; 
    Queue<Integer> BusQueue;
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        CP = new CanvasParking(800, 500);
        RLPark = new ReentrantLock[5];
        BusQueue = new LinkedList();
        GT = new GenThread(CP, RLPark, BusQueue);
        
        this.setSize(800, 500);
        this.add(CP);
    }
    
    @Override
    public void start(){
        GT.start();
    }

    // TODO overwrite start(), stop() and destroy() methods
}
