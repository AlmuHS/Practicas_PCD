/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica9;

import java.util.concurrent.Callable;

/**
 *
 * @author almu
 */
public class Calculador implements Callable<Integer>{
    private CanvasArea canvas;
    private int numpartes;
    private int miparte;
    
    public Calculador(CanvasArea canvas, int numpartes, int miparte){
        
    }

    @Override
    public Integer call() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       
}
