/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Mina {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Monton M = new Monton();
        Grande G = new Grande(1, M);
        Pequenya[] P = new Pequenya[2];
        Cinta C = new Cinta(M);
        
        Thread TG = new Thread(G);
        TG.start();
        
        Thread TC = new Thread(C);
        TC.start();
        
        for (int i = 0; i < 2; i++) {
            P[i] = new Pequenya(i, M);
            P[i].start();
        }
        
        try {
            TG.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < P.length; i++) {
            try {
                P[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        TC.interrupt();
    }
    
}
