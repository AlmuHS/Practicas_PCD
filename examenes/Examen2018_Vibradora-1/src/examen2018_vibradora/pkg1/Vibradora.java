/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_vibradora.pkg1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Vibradora {

    int numHierro;
    int numInox;
    int esperaHierro;
    int esperaInox;

    public Vibradora() {
        numHierro = 0;
        numInox = 0;
        esperaHierro = 0;
        esperaInox = 0;
    }

    public synchronized void EntraInox() {
        esperaInox++;
        
        while (numHierro > 0 || numInox == 3 || (numInox == 0 && esperaHierro > 0)) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Vibradora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        esperaInox--;
        numInox++;
    }

    public synchronized void SaleInox() {
        numInox--;
        notifyAll();
    }

    public synchronized void EntraHierro() {
        esperaHierro++;
        
        while(numInox > 0 || numHierro == 2 || (numHierro == 0 && esperaInox > 0)){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Vibradora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        esperaHierro--;
        numHierro++;
    }

    public synchronized void SaleHierro() {
        numHierro--;
        notifyAll();
    }

}
