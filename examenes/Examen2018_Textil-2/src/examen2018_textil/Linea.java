package examen2018_textil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author almu
 */
public class Linea {
    int esperapantalon;
    boolean librecoser;
    int librecorte;
    
    ReentrantLock RLock;
    Condition mutexCorte;
    Condition mutexCoserPantalon;
    Condition mutexCoserCamisa;
    
    public Linea(){
        esperapantalon = 0;
        librecoser = true;
        librecorte = 2;
        
        RLock = new ReentrantLock();
        mutexCorte = RLock.newCondition();
        mutexCoserPantalon = RLock.newCondition();  
        mutexCoserCamisa = RLock.newCondition();  
    }
    
    
    public void EntraCorte(){
        RLock.lock();

        try {
            while(librecorte == 0){
                mutexCorte.await();
            }
            librecorte--;
        } catch (InterruptedException ex) {
            Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RLock.unlock();
        }
    }
    
    public void CoserPantalon(){
        RLock.lock();
        try {
            esperapantalon++;
            
            while(!librecoser){
                try {
                    mutexCoserPantalon.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            esperapantalon--;
            librecoser = false;
            librecorte++;
            
            mutexCorte.signal();
            
        } finally {
            RLock.unlock();
        }
        
    }
    
    public void CoserCamisa(){
        RLock.lock();
        try {
            while(!librecoser || esperapantalon > 0){
                try {
                    mutexCoserCamisa.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            librecoser = false;
            librecorte++;
            mutexCorte.signal();
        } finally {
            RLock.unlock();
        }
    }
    
    public void SaleCoser(){
        RLock.lock();
        try {
            librecoser = true;
            if(esperapantalon > 0) mutexCoserPantalon.signal();
            else mutexCoserCamisa.signal();
        } finally {
            RLock.unlock();
        }
    }
    
}
