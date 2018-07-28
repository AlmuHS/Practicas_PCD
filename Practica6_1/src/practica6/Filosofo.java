/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Filosofo extends Thread {

    int id;
    Semaphore izquierdo;
    Semaphore derecho;
    Semaphore sentados;
    FilCanvas canvas;

    Filosofo(int id, Semaphore izquierdo, Semaphore derecho, Semaphore sentados, FilCanvas canvas) {
        this.id = id;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.sentados = sentados;
        this.canvas = canvas;
    }

    @Override
    public void run() {

        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        while(true) {

            System.out.println("Filosofo " + id + " pensando");
            canvas.ponestado(id, 0);

            try {
                Thread.sleep(rnd.nextInt(4) * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Filosofo " + id + " esperando para comer");
            canvas.ponestado(id, 1);

            try {
                sentados.acquire();
                System.out.println("Filosofo " + id + " sentado, esperando palillo izquierdo");

                izquierdo.acquire();
                canvas.ponestado(id, 3);
                
                
                System.out.println("Filosofo " + id + " sentado, esperando palillo derecho");
                derecho.acquire();
               

            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }

            canvas.ponestado(id, 4);
            System.out.println("Filosofo " + id + " comiendo");
            try {
                Thread.sleep(rnd.nextInt(5) * 1000);
            } catch (InterruptedException ex) {
                return;
            }
            

            izquierdo.release();
            canvas.ponestado(id, 2);
            derecho.release();
            System.out.println("Filosofo " + id + " termina");
            sentados.release();
        }
    }
}
