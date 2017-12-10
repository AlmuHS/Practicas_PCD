package practica8_v2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author almu
 */
public class AppletParking extends Applet {
    GenThread GT;
    CanvasParking CP;
    ReentrantLock RLPark; 
    Shared share;
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        CP = new CanvasParking(800, 500);
        RLPark = new ReentrantLock();
        share = new Shared();
        
        GT = new GenThread(CP, RLPark, share);
        
        this.setSize(800, 500);
        this.add(CP);
    }
    
    @Override
    public void start(){
        GT.start();
    }

}
