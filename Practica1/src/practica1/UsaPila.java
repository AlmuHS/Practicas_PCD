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

package practica1;

import java.util.Random;

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
        // TODO code application logic here
        Pila p = new Pila(7);
        //Random rand = new Random();

        int r = 0;
        int num;
        for (int i = 0; i < 7; i++) {
           //r = rand.nextInt()%10;
           r++;
           System.out.println("Insertando numero " + r);
           p.Apila(r);   
        }
        
        System.out.println("La pila tiene " + p.GetNum() + " elementos");
        
        int capacidad = p.GetNum();
        while(capacidad > 1){
            capacidad = p.GetNum();
            num = (int) p.Desapila();
            System.out.println("Extraido numero " + num);
            
            System.out.println("Quedan " + capacidad + " elementos");
        }
        
        
    }
    
}
