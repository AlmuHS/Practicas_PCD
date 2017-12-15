package practica8_v2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;

/**
 *
 * @author almu
 */
public class AppletParking extends Applet {
    GenThread GT;
    CanvasParking CP;
    Monitor monitor;
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        CP = new CanvasParking(800, 500);
        monitor = new Monitor();
        GT = new GenThread(CP, monitor);
        
        this.setSize(800, 500);
        this.add(CP);
    }
    
    @Override
    public void start(){
        GT.start();
    }

}
