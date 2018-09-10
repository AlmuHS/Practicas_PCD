/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Bus implements Runnable {

    int id;
    CanvasParking CP;
    Monitor M;

    public Bus(int id, CanvasParking CP, Monitor M) {
        this.id = id;
        this.CP = CP;
        this.M = M;
    }

    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        try {
            int queue = 2; //CarQueue = 1, BusQueue = 2;

            CP.inserta(queue, id);
            sleep(rand.nextInt(1000) + 500);

            M.addBus();

            CP.quita(queue, id);
            sleep(100);
            CP.aparcabus(id);
            sleep(rand.nextInt(2000) + 1000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CP.salebus();
            M.delBus();
        }

    }
}
