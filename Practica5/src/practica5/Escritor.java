/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Escritor implements Runnable {

    private Compartido comp;
    private int id;
    private MiCanvas cv;

    public Escritor(Compartido comp, int id, MiCanvas cv) {
        this.comp = comp;
        this.id = id;
        this.cv = cv;
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
        cv.avisaSC(1, id, 1);

        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Protocolo de salida
        comp.hayescritor = false;
        cv.avisaSC(1, id, 0);
        notifyAll();
    }
}
