package practica8;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author almu
 */
public class Car implements Runnable{
    CanvasParking CP;
    Monitor M;
    int id;
    
    
    public Car(int id, CanvasParking CP, Monitor M){
        this.id = id;
        this.CP = CP;
        this.M = M;
    }
    
    public void run(){
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        
        int queue = 1; //CarQueue = 1, BusQueue = 2;
        
        try {
            CP.inserta(queue, id);
            sleep(rand.nextInt(1000) + 500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        queue = M.addCar();
        
        try {
            CP.quita(queue, id);
            CP.aparcacoche(id, queue);
            sleep(rand.nextInt(2000)+1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CP.salecoche(id, queue);
            M.delCar(queue);
        }
    }
    
}
