/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Pequenya extends Thread {

    int id;
    Monton M;

    public Pequenya(int id, Monton M) {
        this.id = id;
        this.M = M;
    }

    @Override
    public void run() {
        Random rand = new Random();
        System.out.println("Soy cargador pequeno con id " + id);
        
        for (int i = 0; i < 10; i++) {
            
            System.out.println("Cargador pequeno " + id + " esperando para entrar");
            M.CargaPoco();

            System.out.println("Cargador pequeno " + id + " entrando");

            try {
                sleep(rand.nextInt(2) + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pequenya.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Cargador pequeno " + id + " saliendo");
    }
}
