/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_vibradora.pkg1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Vibradora {

    int numHierro;
    int numInox;
    int esperaHierro;
    int esperaInox;
    
    ReentrantLock mutex;
    Condition colaHierro;
    Condition colaInox;

    public Vibradora() {
        numHierro = 0;
        numInox = 0;
        esperaHierro = 0;
        esperaInox = 0;
        
        mutex = new ReentrantLock();
        
        colaHierro = mutex.newCondition();
        colaInox = mutex.newCondition();
    }

    public void EntraInox() {
        mutex.lock();
        
        try {
            esperaInox++;
            
            while (numHierro > 0 || numInox == 3) {
                try {
                    colaInox.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Vibradora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            esperaInox--;
            numInox++;
        } finally {
            mutex.unlock();
        }
    }

    public void SaleInox() {
        mutex.lock();
        
        try {
            numInox--;
            if(esperaHierro > 0 && numInox == 0) colaHierro.signal();
            else colaInox.signal();
        } finally {
            mutex.unlock();
        }
    }

    public void EntraHierro() {
        mutex.lock();
        try {
            esperaHierro++;
            
            while(numInox > 0 || numHierro == 2){
                try {
                    colaHierro.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Vibradora.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            esperaHierro--;
            numHierro++;
        } finally {
            mutex.unlock();
        }
    }

    public void SaleHierro() {
        mutex.lock();
        
        try {
            numHierro--;
            if(esperaInox > 0 && numHierro == 0) colaInox.signal();
            else colaHierro.signal();
        } finally {
            mutex.unlock();
        }
    }

}
