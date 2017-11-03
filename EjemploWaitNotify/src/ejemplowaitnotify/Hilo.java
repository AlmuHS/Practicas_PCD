package ejemplowaitnotify;


import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author almu
 */
public class Hilo extends Thread{
    
    private int id;
    private final Compartido c;
    
    public Hilo(int id, Compartido c){
        this.id = id;
        this.c = c;
    }
    
    
    public void run(){
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        
        
        
       
        for (int i = 0; i < 5; i++) {
            //Protocolo ocupacion
            c.CogerTurno(id);
            
            //Seccion critica
            System.out.println("Soy el hilo " + id);
            
            //Protocolo liberacion
            c.PasaTurno();
            
            try {
                Thread.sleep(rnd.nextInt(3)*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(id == 0){
                c.CogerTurno(id);
                System.out.println("Soy el hilo " + id);
                c.PasaTurno();
            }
        }
    }
    
}
