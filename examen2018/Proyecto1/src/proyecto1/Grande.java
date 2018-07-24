/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Grande implements Runnable {

    int id;
    Monton M;

    public Grande(int id, Monton M) {
        this.id = id;
        this.M = M;
    }

    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 0; i < 7; i++) {
            System.out.println("Soy cargador grande con id " + id);

            System.out.println("Cargador grande " + id + " esperando para entrar");
            M.CargaMucho();

            System.out.println("Cargador grande " + id + " entrando");

            try {
                sleep(rand.nextInt(2) + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pequenya.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Cargador grande " + id + " saliendo");
    }
}
