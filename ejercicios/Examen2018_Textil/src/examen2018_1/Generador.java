/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Generador extends Thread {

    Thread[] prenda;
    Linea lin;
    int numprendas;

    public Generador(Linea lin) {
        this.lin = lin;
        numprendas = 10;
        prenda = new Thread[numprendas];
    }

    public void lanzarHilos() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        try {

            for (int i = 0; i < numprendas; i++) {

                if (rand.nextInt(10) < 3) {
                    Pantalon pant = new Pantalon(i, lin);
                    prenda[i] = new Thread(pant);
                    prenda[i].start();
                } else {
                    Camisa camisa = new Camisa(i, lin);
                    prenda[i] = new Thread(camisa);
                    camisa.start();
                }

                sleep(rand.nextInt(1000) + 3000);

            }

            for (int i = 0; i < numprendas; i++) {
                prenda[i].join();
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        lanzarHilos();
    }

}
