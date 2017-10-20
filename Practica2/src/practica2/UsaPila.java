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
package practica2;

/**
 *
 * @author usuario
 */
public class UsaPila {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        PilaLenta pila = new PilaLenta(7);

        Productor p1 = new Productor(pila);
        Productor p2 = new Productor(pila);
        
        Consumidor cr1 = new Consumidor(pila);
        Thread c1 = new Thread(cr1);
        
        Consumidor cr2 = new Consumidor(pila);
        Thread c2 = new Thread(cr2);
        
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        
        p1.join();
        p2.join();
        c1.join();
        c2.join();
        
    }
    
}
