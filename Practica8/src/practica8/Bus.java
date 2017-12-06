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
public class Bus implements Runnable{
    private int id;
    private CanvasParking cv;
    private ReentrantLock RLock;
    
    public Bus(int id, CanvasParking cv, ReentrantLock RL){
        this.id = id;
        this.cv = cv;
        RLock = RL;
    }
    
    public void run(){
        
    }
    
}
