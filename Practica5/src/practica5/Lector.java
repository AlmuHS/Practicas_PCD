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
public class Lector extends Thread{
    private Compartido comp;
    
    public Lector(Compartido comp){
        this.comp = comp;
    }
    
    @Override
    public synchronized void run(){
        
        //Protocolo entrada
        if(comp.hayescritor){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        comp.numlectores++;
        
        //Seccion crítica
        System.out.println("Soy lector");
        
        
        //Protocolo salida
        comp.numlectores--;
        if(comp.numlectores == 0) notifyAll();
        
    }
    
}
