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

import java.applet.Applet;
import java.awt.Color;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author almu
 */
public class MiApplet extends Applet {
    
    MiCanvas cv;
    Escritor[] escritores; //array de hilos escritores
    Lector[] lectores; //array de hilos lectores
    Thread[] tescritores;
    
    int nescritores; //numero de escritores
    int nlectores; //numero de lectores
    ReentrantReadWriteLock RWLock;
    
    
    @Override
    public void init() {
        
       //Indicamos numero de lectores y escritores
       nescritores = 4;
       nlectores = 3;
       
       //Creamos los arrays de lectores y escritores
       escritores = new Escritor[nescritores];
       lectores = new Lector[nlectores];
       tescritores = new Thread[nescritores];
       
       //Creamos el objeto compartido
       RWLock = new ReentrantReadWriteLock();
       
       //Creamos el canvas
       cv = new MiCanvas(nlectores, nescritores);
       
       //Inicializamos el Applet
       this.setSize(500, 350);
       this.setBackground(Color.red);
       
       //Añadimos el canvas
       this.add(cv); 
       
       //Inicializamos lectores y escritores
       for(int i = 0; i < nescritores; i++){
           escritores[i] = new Escritor(RWLock, i, cv);
           tescritores[i] = new Thread(escritores[i]);
       }
       
       for(int j = 0; j < nlectores; j++){
           lectores[j] = new Lector(RWLock,j, cv);
       }
    }
    
    
    @Override
    public void start(){
        
       //Lanzamos los hilos lectores y escritores
       for(int i = 0; i < nlectores; i++){
           lectores[i].start();
       }
       
       for(int i = 0; i < nescritores; i++){
           tescritores[i].start();
       }
       
        
    }
    
    @Override
    public void stop(){
        //Paramos todos los hilos
       for(int i = 0; i < nescritores; i++){
           tescritores[i].interrupt();
       }
       
       for(int i = 0; i < nlectores; i++){
           lectores[i].interrupt();
       } 
    }

}
