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

package ejemplohilos;

/**
 *
 * @author almu
 */
public class HiloTipo1 extends Thread {
    
    private compartido c;
    
    public HiloTipo1(compartido c){
        this.c = c;
    }
    
    public void run(){
        for (int i = 0; i < 10000; i++) {
            c.incrementa();
        }
    }
}
