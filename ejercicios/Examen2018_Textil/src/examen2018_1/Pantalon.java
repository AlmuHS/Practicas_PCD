/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_1;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Pantalon implements Runnable{
    int id;
    Linea lin;
    
    
    public Pantalon(int id, Linea lin){
        this.id = id;
        this.lin = lin;
    }
    
    @Override
    public void run() {     
        
        try {
            System.out.println("Llega Pantalon " + id);
            
            System.out.println("Pantalon " + id + " entrando a corte");
            lin.EntraCorte();
            sleep(2000);
            System.out.println("Pantalon " + id + " cortado");
            
            System.out.println("Pantalon " + id + " entrando a cosido");
            lin.CoserPantalon();
            sleep(3000);
            System.out.println("Pantalon " + id + " terminado");
            
            lin.SaleCoser();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Pantalon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
