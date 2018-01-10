/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica9;

import java.applet.Applet;

/**
 *
 * @author almu
 */
public class AppletArea extends Applet{
    private CanvasArea canvas;
    private int numhilos;
    private int numpartes;
    private Supervisor spvs;
    
    
    @Override
    public void init(){
        canvas = new CanvasArea(500, 500, numpartes);
    }
    
    @Override
    public void start(){
        
    }
    
    @Override
    public void stop(){
        
    }
    
}
