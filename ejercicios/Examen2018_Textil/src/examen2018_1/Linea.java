package examen2018_1;

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
    
    public Linea(){
        esperapantalon = 0;
        librecoser = true;
        librecorte = 2;
    }
    
    
    public synchronized void EntraCorte(){
        while(librecorte == 0){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        librecorte--;
    }
    
    public synchronized void CoserPantalon(){
        esperapantalon++;
        
        while(!librecoser){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        esperapantalon--;
        librecoser = false;
        librecorte++;
        notifyAll();
    }
    
    public synchronized void CoserCamisa(){
        while(!librecoser || esperapantalon > 0){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Linea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        librecoser = false;
        librecorte++;
        notifyAll();
    }
    
    public synchronized void SaleCoser(){
        librecoser = true;
        notifyAll();
    }
    
}
