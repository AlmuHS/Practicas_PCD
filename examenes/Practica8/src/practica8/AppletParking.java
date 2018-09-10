/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica8;

import java.applet.Applet;

/**
 *
 * @author almu
 */
public class AppletParking extends Applet{
    GenThread GT;
    CanvasParking CP;
    Monitor monitor;
    
    
    public void init(){
        CP = new CanvasParking(800, 500);
        monitor = new Monitor();
        
        GT = new GenThread(CP, monitor);
        
        this.setSize(800, 500);
        this.add(CP);    
    }
    
    public void start(){
        GT.start();
    }
    
}
