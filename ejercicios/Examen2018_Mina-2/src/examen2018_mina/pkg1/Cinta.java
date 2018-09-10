/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_mina.pkg1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Cinta implements Runnable {

    Monton M;

    public Cinta(Monton M) {
        this.M = M;
    }

    @Override
    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        System.out.println("Cinta iniciada\n");

        try {

            while (true) {
                int cantidad = rand.nextInt(5) + 2;

                M.Rellena(cantidad);
                System.out.println("Cinta carga " + cantidad + " Tm\n");

                sleep(rand.nextInt(5000) + 1000);

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Cinta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
