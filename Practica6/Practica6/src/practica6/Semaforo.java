/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica6;

/**
 *
 * @author almu
 */
public class Semaforo {
    protected volatile int contador;
    
    public Semaforo(int inicial) throws Exception{
        if(inicial != 0 && inicial != 1){
            throw new Exception("Imposible inicializar semaforo binario");
        }
        contador = inicial;
    }
    
    public synchronized void WAIT() throws InterruptedException{
        while(contador == 0){
            wait();
        }
        contador = 0;
    }
    
    public synchronized void SIGNAL() throws InterruptedException{
        contador = 1;
        notify();
    }
}
