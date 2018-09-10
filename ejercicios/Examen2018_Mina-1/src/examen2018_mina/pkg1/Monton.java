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
public class Monton {
    int cantidad;
    int esperaGrande;
    
    public Monton(){
        cantidad = 4;
        esperaGrande = 0;
        System.out.println("Cantidad de Mineral: " + cantidad);
    }
    
    public synchronized void CargaPoco(){
        while(cantidad < 1 || esperaGrande > 0){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Monton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        cantidad--;
        System.out.println("Cantidad de mineral: " + cantidad);
    }
    
    public synchronized void CargaMucho(){
        esperaGrande++;
        
        while(cantidad < 3){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Monton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        esperaGrande--;
        cantidad -= 3;
        notifyAll();
        
        System.out.println("Cantidad de mineral: " + cantidad);
    }
    
    public synchronized void Rellena(int cantidad){
        this.cantidad = this.cantidad + cantidad;
        System.out.println("Cantidad de mineral: " + this.cantidad);
        
        notifyAll();
    }
    
}
