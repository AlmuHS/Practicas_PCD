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
public class EjemploHilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       compartido c = new compartido(); 
       
       HiloTipo1 h1 = new HiloTipo1(c);//Hilo heredado de thread  
       HiloTipo1 h3 = new HiloTipo1(c);
       HiloTipo1 h4 = new HiloTipo1(c);
       
       HiloTipo2 r2 = new HiloTipo2(c);
       Thread h2 = new Thread(r2);
       
       
       
       System.out.println("Soy el hilo main, con nombre " + Thread.currentThread().getName()
                            + ", prioridad " + Thread.currentThread().getPriority());
       System.out.println("Con id " + Thread.currentThread().getId());
       
       Thread.currentThread().setName("HiloMain");
       Thread.currentThread().setPriority(9);
       
       System.out.println("Soy el hilo main, con nombre " + Thread.currentThread().getName()
                            + ", prioridad " + Thread.currentThread().getPriority());
       
       System.out.println("Con id " + Thread.currentThread().getId());
       
       c.informa();
       
       h1.start();
       h2.start();
       h3.start();
       h4.start();
       
        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException ex) {
           
        }
        
       System.out.println("Finaliza main");
       
       c.informa();
    }
    
}
