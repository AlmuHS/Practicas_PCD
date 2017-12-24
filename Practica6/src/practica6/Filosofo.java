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

package practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author almu
 */
public class Filosofo extends Thread{
    private int id;
    private Semaphore izquierdo;
    private Semaphore derecho;
    private Semaphore sentados;
    private FilCanvas canvas;
    
    public Filosofo(int id, Semaphore izquierdo, Semaphore derecho, Semaphore sentados, FilCanvas canvas){
        
    }
    
    @Override
    public void run(){
        
    }
    
    
}
