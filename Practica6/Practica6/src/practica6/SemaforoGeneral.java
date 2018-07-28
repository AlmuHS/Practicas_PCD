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
public class SemaforoGeneral {
    protected volatile int contador;
    
    public SemaforoGeneral(int inicial) throws Exception{
        if(inicial < 0){
            throw new Exception("Imposible inicializar semaforo general");
        }
        contador = inicial;
    }

   
    
    public synchronized void WAIT() throws InterruptedException{
        while(contador == 0) wait();
        contador--;
    }
    
    public synchronized void SIGNAL(){
        contador++;
        notify();
    }
    
}
