/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_taller.pkg1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Taller {
    int esperacamion;
    int operariosLibres;
    
    public Taller(){
        esperacamion = 0;
        operariosLibres = 4;
    }
    
    public synchronized void EntraCamion(){
        esperacamion++;
        
        while(operariosLibres < 2){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        operariosLibres -= 2;
        esperacamion--;
        System.out.println("Operarios Libres: " + operariosLibres);
    }
    
    public synchronized void EntraCoche(){
        while(operariosLibres < 1 || esperacamion > 0){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        operariosLibres--;
        System.out.println("Operarios Libres: " + operariosLibres);
    }
    
    public synchronized void SaleCamion(){
        operariosLibres += 2;
        notifyAll();
        System.out.println("Operarios Libres: " + operariosLibres);
    }
    
    public synchronized void SaleCoche(){
        operariosLibres++;
        notifyAll();
        System.out.println("Operarios Libres: " + operariosLibres);
    }
    
    
}
