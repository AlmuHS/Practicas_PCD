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
public class Compartido {
    private int turno;
    
    public Compartido(int inicial){
        this.turno = inicial;
    }
    
    public synchronized void CogerTurno(int id){
        while(turno != id){
            try {
                wait();
                System.out.println("Despierta el hilo " + id);
            } catch (InterruptedException ex) {
            }
        }
    }

    public synchronized void PasaTurno() {
        turno = (turno + 1) % 5;
        notifyAll();
    }
}
