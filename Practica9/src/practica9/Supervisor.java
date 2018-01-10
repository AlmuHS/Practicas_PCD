/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author almu
 */
public class Supervisor extends Thread{
    private CanvasArea canvas;
    private int numhilos;
    private int numpartes;
    
    
    
    public Supervisor(CanvasArea canvas, int numhilos, int numpartes){
        this.canvas = canvas;
        this.numhilos = numhilos;
        this.numpartes = numpartes;
    }
    
    @Override
    public void run(){
        ExecutorService thp = Executors.newFixedThreadPool(numhilos);
        
        for (int i = 0; i < numhilos; i++) {
            Calculador calc = new Calculador(canvas, numpartes, i);
            thp.execute((Runnable) calc);
            
        }
    }
}
