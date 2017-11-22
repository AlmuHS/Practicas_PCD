/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5;

import java.applet.Applet;
import java.awt.Color;

/**
 *
 * @author almu
 */
public class MiApplet extends Applet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        int capacidad = 15; 
        
       this.setSize(450, 700);
       this.setBackground(Color.red);
       //this.add(cp); 
    }

    // TODO overwrite start(), stop() and destroy() methods
}
