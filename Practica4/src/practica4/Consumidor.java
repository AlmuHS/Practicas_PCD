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

import static java.lang.Math.abs;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Consumidor implements Runnable{
    private PilaLenta lapila;
    
    public Consumidor(PilaLenta pila){
        lapila = pila;
    }
    
    void consumir(){
        Random randnum = new Random();
        int valor;
        for (int i = 0; i < 15; i++) {
            
            try {
                valor = (int) lapila.Desapila();
                System.out.println("Soy consumidor y extraigo el valor " + valor);
                Thread.sleep(abs(randnum.nextInt() % 3 + 1));
            } catch (Exception ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }//Fin for
        
    }
    
    @Override
    public void run(){
        consumir();
    }
    
}
    

