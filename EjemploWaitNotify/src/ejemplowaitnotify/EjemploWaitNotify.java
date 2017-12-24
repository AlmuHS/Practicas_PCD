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


package ejemplowaitnotify;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class EjemploWaitNotify {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        
        Hilo[] h = new Hilo[5];
        Compartido c = new Compartido(0);
        
        for (int i = 0; i < 5; i++) {
            h[i] = new Hilo(i, c);
        }
        
        for (int i = 0; i < 5; i++) {
            h[i].start();
        }
        
        for (int i = 1; i < 5; i++) {
            try {
                h[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(EjemploWaitNotify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Fin de main");
        synchronized(c){
            c.notify();
        }
        
        synchronized(c){
            c.notify();
        }
        
    
    }//Fin main
   
}
