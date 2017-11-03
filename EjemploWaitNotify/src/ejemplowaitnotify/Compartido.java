package ejemplowaitnotify;


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
public class Compartido {
    private int turno;
    
    public Compartido(int inicial){
        this.turno = inicial;
    }
    
    public synchronized void CogerTurno(int id){
        while(turno != id){
            try {
                wait();
                System.out.println("Despierta el hilo " + id);
            } catch (InterruptedException ex) {
            }
        }
    }

    public synchronized void PasaTurno() {
        turno = (turno + 1) % 5;
        notifyAll();
    }
}
