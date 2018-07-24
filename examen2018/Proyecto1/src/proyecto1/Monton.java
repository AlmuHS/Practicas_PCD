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
public class Monton {

    int cantidadMineral = 0;
    int grandeEspera = 0;

    public synchronized void CargaPoco() {
        
        while (grandeEspera > 0 || cantidadMineral < 1) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Monton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Cinta rellena. Cantidad de Mineral actual = " + cantidadMineral);
    
        cantidadMineral -= 1;
        System.out.println("Cargadora pequena retira. Cantidad de Mineral actual = " + cantidadMineral);
        if(grandeEspera > 0) notify();
    }

    public synchronized void CargaMucho() {
        grandeEspera++;
        
        while (cantidadMineral < 3) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Monton.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Cinta rellena. Cantidad de Mineral actual = " + cantidadMineral);
        
        grandeEspera--;
        cantidadMineral -= 3;
        System.out.println("Cargadora grande retira. Cantidad de Mineral actual = " + cantidadMineral);
        if(grandeEspera > 0) notify();
    }

public synchronized void Rellena(int cantidad){
        cantidadMineral += cantidad;
        notifyAll();
    }
}
