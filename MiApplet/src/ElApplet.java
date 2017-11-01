/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author almu
 */
public class ElApplet extends Applet {

    private Hilo h1, h2, h3;
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        Dimension d = new Dimension(400, 200);
        setSize(400, 200); // Establece el tamaño del applet
                
        ElCanvas cv = new ElCanvas(d);
        add(cv);
        
        
        Compartido c = new Compartido(0, 0, cv);
        h1 = new Hilo(c, 0);
        h2 = new Hilo(c, 1);
        h3 = new Hilo(c, 1);
    }
    
    @Override
    public void start(){
            h1.start();
            h2.start();
            h3.start();        
    }
    
    @Override
    public void stop(){
            h1.interrupt();
            h2.interrupt();
            //h3.interrupt();
    }
    // TODO overwrite start(), stop() and destroy() methods
}
