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
    
    private int numParkedCar;
    private int numParkedCarinBus;
    private Boolean ParkedBus;
    private int BusWaiting;
    
    ReentrantLock RLockCar;
    private final ReentrantLock RLockBus;
    private final Condition mutexCar;
    private final Condition mutexBus;

    public Shared() {
       
        numParkedCar = 0;
        numParkedCarinBus = 0;
        ParkedBus = false;
        BusWaiting = 0;
        
        
        RLockCar = new ReentrantLock();
        RLockBus = new ReentrantLock();
        mutexCar = RLockCar.newCondition();
        mutexBus = RLockBus.newCondition();
    }

    public int addCar() {
        int queue = 1;
        try {
            
            RLockCar.lock();
            RLockBus.lock();
            
            if((numParkedCar == 3 && numParkedCarinBus == 2) || ParkedBus || BusWaiting > 0){
                mutexCar.await();
            }
            
            if(numParkedCar < 3){
                queue = 1;
                numParkedCar++;
            }
            else{
                queue = 2;
                numParkedCarinBus++;
            }
            

        } catch (InterruptedException ex) {
            Logger.getLogger(Shared.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            RLockCar.unlock();
            RLockBus.unlock();
        }
       
        return queue;
    }

    public void delCar(int queue) {
        
        try{
        if (queue == 1) {
            if (numParkedCar > 0) {
                numParkedCar--;
            }
            
        } else if (queue == 2) {
            if (numParkedCarinBus > 0) {
                numParkedCarinBus--;
            }
        }
        
        }finally{
           if(RLockCar.isLocked()) RLockCar.unlock();
           else if (RLockBus.isLocked()) RLockBus.unlock(); 
        }
        
    }

    public void addBus() {
        try {
            RLockCar.lock();
            if (numParkedCarinBus > 0 || BusWaiting > 0) {
                BusWaiting++;
                mutexBus.await();
                
            }
            ParkedBus = true;
            BusWaiting--;
           
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Shared.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            RLockCar.unlock();
        }
    }

    public void delBus() {
        try{
            ParkedBus = false;

        }finally{
             RLockBus.unlock();
        }
    }

}
