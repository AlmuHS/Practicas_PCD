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
public class EjemploHilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       compartido c = new compartido(); 
       
       HiloTipo1 h1 = new HiloTipo1(c);//Hilo heredado de thread  
       HiloTipo1 h3 = new HiloTipo1(c);
       HiloTipo1 h4 = new HiloTipo1(c);
       
       HiloTipo2 r2 = new HiloTipo2(c);
       Thread h2 = new Thread(r2);
       
       
       
       System.out.println("Soy el hilo main, con nombre " + Thread.currentThread().getName()
                            + ", prioridad " + Thread.currentThread().getPriority());
       System.out.println("Con id " + Thread.currentThread().getId());
       
       Thread.currentThread().setName("HiloMain");
       Thread.currentThread().setPriority(9);
       
       System.out.println("Soy el hilo main, con nombre " + Thread.currentThread().getName()
                            + ", prioridad " + Thread.currentThread().getPriority());
       
       System.out.println("Con id " + Thread.currentThread().getId());
       
       c.informa();
       
       h1.start();
       h2.start();
       h3.start();
       h4.start();
       
        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException ex) {
           
        }
        
       System.out.println("Finaliza main");
       
       c.informa();
    }
    
}
