/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.applet.Applet;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class FilApplet extends Applet{
    Filosofo[] filosofos;
    Semaforo[] palillos;
    SemaforoGeneral sentados;
    
    int numfilosofos;
    FilCanvas canvas;
    
    @Override
    public void init(){
        
        numfilosofos = 5;
        this.setSize(600, 250);
        
        
        palillos = new Semaforo[numfilosofos];
        try {
            sentados = new SemaforoGeneral(numfilosofos-1);
        } catch (Exception ex) {
            Logger.getLogger(FilApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
        filosofos = new Filosofo[numfilosofos];
        
        canvas = new FilCanvas(600, 250);
        
        
        for (int i = 0; i < numfilosofos; i++) {
            try {
                palillos[i] = new Semaforo(1);
            } catch (Exception ex) {
                Logger.getLogger(FilApplet.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        for (int i = 0; i < numfilosofos; i++) {
            filosofos[i] = new Filosofo(i, palillos[i], palillos[(i+1)%numfilosofos], sentados, canvas);
        }
        
        this.add(canvas);
    }
    
    @Override
    public void start(){
        
        for (int i = 0; i < numfilosofos; i++) {
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(FilApplet.class.getName()).log(Level.SEVERE, null, ex);
            }
            filosofos[i].start();
            
        }
    }
    
    @Override
    public void stop(){
        for (Filosofo filosofo : filosofos) {
            filosofo.interrupt();
        }
    }
    
    
}
