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
        
    }
    
}
