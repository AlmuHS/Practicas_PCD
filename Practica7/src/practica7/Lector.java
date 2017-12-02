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

package practica7;

import static java.lang.Math.abs;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Lector extends Thread{
    private int id;
    private MiCanvas cv;
    ReentrantReadWriteLock RWLock;
    private int tiempo;
    
    
    public Lector(ReentrantReadWriteLock RWLock, int id, MiCanvas cv){
        this.id = id;
        this.cv = cv;
        this.RWLock = RWLock;
    }
    
    @Override
    public void run(){
        Random rand = new Random(1500);
        rand.setSeed(System.currentTimeMillis() + id);
        tiempo = abs(rand.nextInt()%1500);
       
        
        try {
            //Protocolo entrada
            cv.llegaLector(id);
            RWLock.readLock().lock();
            Thread.sleep(tiempo/4);
            
            if(rand.nextInt()%4 == 2){
                CambioEscritor();
            }
            Thread.sleep(tiempo*3/4);
            
            //Seccion crítica
            System.out.println("Lector " + id + " entrando en Seccion Critica");
            cv.avisaSC(0, id, 1);
            Thread.sleep(1000);
            
            //Protocolo salida
            System.out.println("Lector " + id + " saliendo");
            cv.avisaSC(0, id, 0);
             
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            RWLock.readLock().unlock();
        }
        
    }
    
    private void CambioEscritor(){
        try {
            System.out.println("Lector " + id + " tranformado en escritor");
            RWLock.readLock().unlock();
            RWLock.writeLock().lock();
            RWLock.readLock().lock();
            System.out.println("Lector " + id + ", actuando como escritor, entrando en seccion critica");
            cv.avisaSC(2, id, 1);
            Thread.sleep(tiempo/4);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            RWLock.writeLock().unlock();
            //RWLock.readLock().lock();
        }
        
    }
    
}
