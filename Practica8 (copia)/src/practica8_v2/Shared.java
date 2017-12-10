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
    private Condition mutexCar;
    private Condition mutexBus;
    
    public Shared(){
        freeCar = 3;
        freeBus = 2;
        RLockCar = new ReentrantLock();
        RLockBus = new ReentrantLock();
        mutexCar = RLockCar.newCondition();
        mutexBus = RLockBus.newCondition();
    }
    
    public synchronized void addCar(){
        try {
            if(RLockCar.isLocked()) mutexCar.await();
            
            if(freeCar > 0) freeCar--;
            if(freeCar == 0) RLockCar.lock();
        } catch (InterruptedException ex) {
            Logger.getLogger(Shared.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void delCar(){
        if(freeCar < 3) freeCar++;
        if(RLockCar.isLocked()) RLockCar.unlock();
    }
    
    public void addBus(){
        if(freeBus > 0) freeBus--;
        if(freeBus == 0) RLockBus.lock();
    }
    
    public void delBus(){
        if(freeBus < 2) freeBus++;
        if(RLockCar.isLocked()) RLockBus.unlock();
    }
    
    public ReentrantLock getLockCar(){
        return RLockCar;
    }
    
    public ReentrantLock getLockBus(){
        return RLockBus;
    }
    
}
