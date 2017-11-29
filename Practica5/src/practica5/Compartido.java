/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5;

/**
 *
 * @author almu
 */
public class Compartido {
    private int numlectores = 0;
    private boolean hayescritor = false;
    
    public synchronized void EntradaLector() throws InterruptedException{
        while(hayescritor){
            System.out.println(Thread.currentThread().getName()+" voy a Esperar siendo un lector");
            wait();
        }     
        numlectores++;
    }
    
    public synchronized void SalidaLector(){
        numlectores--;
        if(numlectores == 0) notifyAll();
    }
    
    public synchronized void EntradaEscritor() throws InterruptedException{
        while (hayescritor || (numlectores > 0)) {
            wait();
        }
        hayescritor = true;
    }
    
    public synchronized void SalidaEscritor(){
        hayescritor = false;
        notifyAll();
    }
    
    
    
}
