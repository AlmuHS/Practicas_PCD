/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Monton {

    int cantidadMineral = 0;
    int grandeEspera = 0;
    ReentrantLock RLock;
    Condition mutex;

    public Monton() {
        RLock = new ReentrantLock();
        mutex = RLock.newCondition();
    }

    public void CargaPoco() {

        try {
             RLock.lock();
            
            if (grandeEspera > 0) {
                mutex.await();
            }
            else if(cantidadMineral < 1){
                mutex.await();
            }
            System.out.println("Cinta rellena. Cantidad de Mineral actual = " + cantidadMineral);
            
            cantidadMineral -= 1;
            System.out.println("Cargadora pequena retira. Cantidad de Mineral actual = " + cantidadMineral);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Monton.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RLock.unlock();
        }

    }

    public void CargaMucho() {
        
       
        
        try {
             RLock.lock();
            
            grandeEspera++;
            
            if (cantidadMineral < 3) {
                mutex.await();
            }
            System.out.println("Cinta rellena. Cantidad de Mineral actual = " + cantidadMineral);

            grandeEspera--;
            cantidadMineral -= 3;
            System.out.println("Cargadora grande retira. Cantidad de Mineral actual = " + cantidadMineral);

        } catch (InterruptedException ex) {
            Logger.getLogger(Monton.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RLock.unlock();
        }
    }

    public void Rellena(int cantidad) {
        RLock.lock();
        try {
            cantidadMineral += cantidad;
            
            mutex.signal();
            
        } finally {
            RLock.unlock();
        }
        
       
    }
}
