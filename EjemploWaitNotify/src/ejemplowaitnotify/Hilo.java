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

package ejemplowaitnotify;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author almu
 */
public class Hilo extends Thread{
    
    private int id;
    private final Compartido c;
    
    public Hilo(int id, Compartido c){
        this.id = id;
        this.c = c;
    }
    
    
    public void run(){
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        
        
        
       
        for (int i = 0; i < 5; i++) {
            //Protocolo ocupacion
            c.CogerTurno(id);
            
            //Seccion critica
            System.out.println("Soy el hilo " + id);
            
            //Protocolo liberacion
            c.PasaTurno();
            
            try {
                Thread.sleep(rnd.nextInt(3)*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(id == 0){
                c.CogerTurno(id);
                System.out.println("Soy el hilo " + id);
                c.PasaTurno();
            }
        }
    }
    
}
