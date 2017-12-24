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


package holamundo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class HolaMundo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Saludador hola = new Saludador();

        try {
            hola.saluda("hola mundo");
        } catch (Exception ex) {
            //Logger.getLogger(HolaMundo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error, debe introducir un valor distinto a 0");
        }

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int r = rand.nextInt();
        
        if(r % 2 == 0){
            //par
        }
        else{
            //impar
        }
                
    }

}
