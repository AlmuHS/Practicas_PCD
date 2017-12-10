package practica8_v2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
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
public class Shared {
    private int freeCar;
    private int freeBus;
    ReentrantLock RLockCar;
    private final ReentrantLock RLockBus;
    private final Condition mutexCar;
    private final Condition mutexBus;
    
    public Shared(){
        freeCar = 5;
        freeBus = 2;
        RLockCar = new ReentrantLock();
        RLockBus = new ReentrantLock();
        mutexCar = RLockCar.newCondition();
        mutexBus = RLockBus.newCondition();
    }
    
    public synchronized int addCar(){
        int queue = 1;
        try {
            if(RLockCar.isLocked()){
                if(!RLockBus.isLocked()){
                    queue = 2;
                    freeBus--;
                    if(freeBus == 0) RLockBus.lock();
                }
                else{
                    mutexCar.await();
                    if(freeCar > 0) freeCar--;
                    if(freeCar == 0 && freeBus == 0) RLockCar.lock();
                }
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Shared.class.getName()).log(Level.SEVERE, null, ex);
        }
        return queue;
    }
    
    public synchronized void delCar(int queue){
        if(queue == 1){
            if(freeCar < 5) freeCar++;
            
            if(RLockCar.isLocked()){
                RLockCar.unlock();
                mutexCar.signalAll();
            }
        }
        else if(queue == 2){
            if(freeBus < 2) freeBus++;
            RLockBus.unlock();
        } 
    }
    
    public void addBus(){
        try {
            if(RLockBus.isLocked() || freeBus < 2) mutexBus.await();
            freeBus = 0;
            RLockBus.lock();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Shared.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delBus(){
        freeBus = 2;
        RLockBus.unlock();
        mutexBus.signalAll();
    }
    
    
    
}
