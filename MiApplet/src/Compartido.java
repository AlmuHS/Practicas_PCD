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

/**
 *
 * @author almu
 */
public class Compartido {
    
    private int[] contadores;
    private ElCanvas cv;
    
    public Compartido(int v1, int v2, ElCanvas cv){
        contadores = new int[2];
        contadores[0]=v1;
        contadores[1]=v2;
    }
    
    public synchronized void incrementa(int contador){
        contadores[contador]++;
        System.out.println("Contador 1: " + contadores[0] + "\tContador 2" + contadores[1]);
        cv.actualiza(contadores);
    }
    
    
    
}
