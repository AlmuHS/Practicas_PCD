/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8_v2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Math.abs;
import static java.lang.Thread.sleep;

/**
 *
 * @author almu
 */
public class Bus extends Thread {

    private int id;
    private CanvasParking cv;
    Monitor monitor;

    public Bus(int id, CanvasParking cv, Monitor monitor) {
        this.id = id;
        this.cv = cv;
        this.monitor = monitor;
       
    }

    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        try {
            cv.inserta(2, id);
            sleep(abs(rand.nextInt() % 1000 + 500));
            
            monitor.addBus();
   
            cv.quita(2, id);
            sleep(100);
            cv.aparcabus(id);
            sleep(abs(rand.nextInt() % 2000 + 1000));

        } catch (InterruptedException ex) {
            Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            cv.salebus();
            monitor.delBus();
        }

    }

}
