/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Escritor extends Thread{
    private Compartido comp;
    
    public Escritor(Compartido comp){
        this.comp = comp;
    }
    
    public synchronized void run(){
        
        //Protocolo de entrada
        if(comp.hayescritor || (comp.numlectores > 0)){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Soy escritor");
        comp.hayescritor = true;
        
        //Protocolo de salida
        comp.hayescritor = false;
        notifyAll();
    }
}
