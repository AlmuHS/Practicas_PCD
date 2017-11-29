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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Lector extends Thread{
    private Compartido comp;
    private int id;
    private MiCanvas cv;
    
    public Lector(Compartido comp, int id, MiCanvas cv){
        this.comp = comp;
        this.id = id;
        this.cv = cv;
    }
    
    @Override
    public synchronized void run(){
        
         //Protocolo entrada
        try {
            comp.EntradaLector(id);
            cv.llegaLector(id);
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //Seccion crítica
        System.out.println("Lector " + id + " entrando en Seccion Critica");
        cv.avisaSC(0, id, 1);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Protocolo salida
        comp.SalidaLector();
        System.out.println("Lector " + id + " saliendo");
        cv.avisaSC(0, id, 0);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        cv.saleLector(id);
        
    }
    
}
