/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplohilos;

/**
 *
 * @author almu
 */
public class HiloTipo1 extends Thread {
    
    private compartido c;
    
    public HiloTipo1(compartido c){
        this.c = c;
    }
    
    public void run(){
        for (int i = 0; i < 10000; i++) {
            c.incrementa();
        }
    }
}
