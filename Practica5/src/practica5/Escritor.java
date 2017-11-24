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
public class Escritor implements Runnable {

    private Compartido comp;

    public Escritor(Compartido comp) {
        this.comp = comp;
    }

    public synchronized void run() {

        //Protocolo de entrada
        while (comp.hayescritor || (comp.numlectores > 0)) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        comp.hayescritor = true;

        //Sección crítica
        System.out.println("Soy escritor");

        //Protocolo de salida
        comp.hayescritor = false;
        notifyAll();
    }
}
