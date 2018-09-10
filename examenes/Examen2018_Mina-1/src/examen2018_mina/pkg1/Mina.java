/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_mina.pkg1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Mina {
        
    public static void main(String[] args) {
        try {
            Monton M = new Monton();
            
            Cinta C = new Cinta(M);
            Thread TC = new Thread(C);
            
            Grande G = new Grande(M);
            Thread TG = new Thread(G);
            
            Pequena P = new Pequena(M);
            
            TC.start();
            P.start();
            TG.start();
            
            P.join();
            TG.join();
            TC.interrupt();
            TC.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
