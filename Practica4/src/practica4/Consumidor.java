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
package practica4;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Consumidor implements Runnable{
    private PilaLenta lapila;
    private int id;
    
    public Consumidor(PilaLenta pila, int id){
        lapila = pila;
        this.id = id;
    }
    
    void consumir(){
        Random randnum = new Random();
        //randnum.setSeed(id);
        int valor;
        
        try {
            for (int i = 0; i < 15; i++) {   

                valor = (int) lapila.Desapila();
                System.out.println("Soy el consumidor " + this.id + " y extraigo el valor " + valor);
                Thread.sleep(randnum.nextInt(3000) + 1000);

            }//Fin for
        
        } catch (Exception ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Consumidor " + id + " termina de desapilar");
        
        try {
            Thread.sleep(2000);
            synchronized(this.lapila){
                lapila.notifyAll();
            }
        
            Thread.sleep(1500);
            synchronized(this.lapila){
                lapila.notifyAll();
            }

            Thread.sleep(2500);
            synchronized(this.lapila){
                lapila.notifyAll();
            } 
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finaliza el productor " + id);
          
    }
    
    
    @Override
    public void run(){
        consumir();
    }
    
    
}
    

