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

package practica5;

/**
 *
 * @author almu
 */
public class Compartido {
    private int numlectores = 0;
    private boolean hayescritor = false;
    
    public synchronized void EntradaLector(int id) throws InterruptedException{
        while(hayescritor){
            System.out.println("Lector " + id + " esperando");
            wait();
        }     
        numlectores++;
    }
    
    public synchronized void SalidaLector(){
        numlectores--;
        if(numlectores == 0) notifyAll();
    }
    
    public synchronized void EntradaEscritor(int id) throws InterruptedException{
        while (hayescritor || (numlectores > 0)) {
            System.out.println("Escritor " + id + " esperando");
            wait();
        }
        hayescritor = true;
    }
    
    public synchronized void SalidaEscritor(){
        hayescritor = false;
        notifyAll();
    }
    
    
    
}
