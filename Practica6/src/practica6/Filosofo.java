/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Filosofo extends Thread {

    int id;
    Semaforo izquierdo;
    Semaforo derecho;
    Semaforo sentados;
    FilCanvas canvas;

    Filosofo(int id, Semaforo izquierdo, Semaforo derecho, Semaforo sentados, FilCanvas canvas) {
        this.id = id;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.sentados = sentados;
        this.canvas = canvas;
    }

    public void run() {
        while (true) {

            try {
                System.out.println("Filosofo " + id + " pensando");
                canvas.ponestado(id, 0);
                
                sentados.WAIT();
                canvas.ponestado(id, 1);
                
                System.out.println("Filosofo " + id + " sentado, esperando palillo izquierdo");
                izquierdo.WAIT();
                canvas.ponestado(id, 2);
                
                System.out.println("Filosofo " + id + " sentado, esperando palillo derecho");
                derecho.WAIT();
                canvas.ponestado(id, 3);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Filosofo " + id + " comiendo");

            try {
                izquierdo.SIGNAL();
                derecho.SIGNAL();
                sentados.SIGNAL();
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
