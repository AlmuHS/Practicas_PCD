/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

import java.util.concurrent.Semaphore;

/**
 *
 * @author almu
 */
public class Filosofo extends Thread{
    private int id;
    private Semaphore izquierdo;
    private Semaphore derecho;
    private Semaphore sentados;
    private FilCanvas canvas;
    
    public Filosofo(int id, Semaphore izquierdo, Semaphore derecho, Semaphore sentados, FilCanvas canvas){
        
    }
    
    @Override
    public void run(){
        
    }
    
    
}
