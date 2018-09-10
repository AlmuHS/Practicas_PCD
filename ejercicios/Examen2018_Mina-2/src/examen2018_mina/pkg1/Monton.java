/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_mina.pkg1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Monton {
    int cantidad;
    int esperaGrande;
    ReentrantLock mutex;
    Condition ColaPequena;
    Condition ColaGrande;
    
    public Monton(){
        cantidad = 4;
        esperaGrande = 0;
        mutex = new ReentrantLock();
        ColaPequena = mutex.newCondition();
        ColaGrande = mutex.newCondition();
        
        System.out.println("Cantidad de Mineral: " + cantidad);
    }
    
    public void CargaPoco(){
        mutex.lock();
        try {
            while(cantidad < 1 || esperaGrande > 0){
                try {
                    ColaPequena.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Monton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            cantidad--;
        } finally {
            mutex.unlock();
        }
        
        System.out.println("Cantidad de mineral: " + cantidad);
    }
    
    public void CargaMucho(){
        mutex.lock();
        try {
            esperaGrande++;
            
            while(cantidad < 3){
                try {
                    ColaGrande.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Monton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            esperaGrande--;
            cantidad -= 3;
            
        } finally {
            mutex.unlock();
        }
        
        System.out.println("Cantidad de mineral: " + cantidad);
    }
    
    public void Rellena(int cantidad){
        mutex.lock();
        try {
            this.cantidad = this.cantidad + cantidad;
            System.out.println("Cantidad de mineral: " + this.cantidad);
                        
            if(esperaGrande > 0 && cantidad >= 3) ColaGrande.signal();
            else ColaPequena.signal();
            
        } finally {
            mutex.unlock();
        }
    }
    
}
