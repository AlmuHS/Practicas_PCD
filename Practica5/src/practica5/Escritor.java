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

        try {
            //Protocolo de entrada
            comp.EntradaEscritor(id);
            cv.llegaEscritor(id);
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Sección crítica
        System.out.println("Escritor " + id + " entrando en Seccion Critica");
        cv.avisaSC(1, id, 1);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Protocolo de salida
        comp.SalidaEscritor();
        System.out.println("Escritor " + id + " saliendo");
        cv.avisaSC(1, id, 0);
         
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }
        cv.saleEscritor(id);
        
    }
}
