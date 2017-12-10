/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8_v2;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Bus extends Thread {

    private int id;
    private CanvasParking cv;
    Shared share;

    public Bus(int id, CanvasParking cv, ReentrantLock RL, Shared share) {
        this.id = id;
        this.cv = cv;
        this.share = share;
       
    }

    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        try {
            cv.inserta(2, id);
            sleep(abs(rand.nextInt() % 3000) + 1000);
            
            share.addBus();
   
            cv.quita(2, id);
            sleep(1000);
            cv.aparcabus(id);
            sleep(abs(rand.nextInt() % 3000));

        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            cv.salebus();
            share.delBus();
        }

    }

}
