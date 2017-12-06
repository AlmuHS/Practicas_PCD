/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author almu
 */
public class Car implements Runnable{
    private int id;
    private CanvasParking cv;
    private ReentrantLock RLock;
    
    public Car(int id, CanvasParking cv, ReentrantLock RL){
        this.id = id;
        this.cv = cv;
        this.RLock = RL;
    }
    
    @Override
    public void run(){
        
    }
    
    
}
