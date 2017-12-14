/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8_v2;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Car implements Runnable {

    private int id;
    private CanvasParking cv;
    
    private Shared share;

    public Car(int id, CanvasParking cv, Shared share) {
        this.id = id;
        this.cv = cv;
        this.share = share;
        
    }

    @Override
    public void run() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int queue = 1;

        try {
            cv.inserta(1, id);
            sleep(abs(rand.nextInt() % 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }

        queue = share.addCar();

        try {
            cv.quita(1, id);
            cv.aparcacoche(id, queue);
            
            sleep(abs(rand.nextInt()) % 1000 + 1000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cv.salecoche(id, queue);
            share.delCar(queue);
        }

    }

}
