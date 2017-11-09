package practica4;

/*
 * Copyright (C) 2017 Almudena García Jurado-Centurión
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.applet.Applet;
import java.awt.Color;

/**
 *
 * @author almu
 */
public class PilaApplet extends Applet {
    
    private Productor[] prod; 
    private Consumidor[] cons;
    private Thread[] tcon; 
    int nprods = 4;
    int ncons = 1;
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    
    @Override
    public void init() {
       int capacidad = 15; 
        
       CanvasPila cp = new CanvasPila(capacidad);
       PilaLenta pila = new PilaLenta(capacidad, cp);
       
       this.setSize(450, 700);
       this.setBackground(Color.red);
       this.add(cp); 
       
       prod = new Productor[nprods];
       
       for (int i = 0; i < nprods; i++) {
           prod[i] = new Productor(pila, i);
       }
       
       cons = new Consumidor[ncons];
       tcon = new Thread[ncons];
       
       for (int i = 0; i < ncons; i++) {
           cons[i] = new Consumidor(pila, i);
           tcon[i] = new Thread(cons[i]);
       }
       
    }
    
    @Override
    public void start(){
        
        for (Productor prod1 : prod) {
            prod1.start();
        }
        
        for (Thread tcon1 : tcon) {
            tcon1.start();           
        }
        
    }
    
    @Override
    public void stop(){
        
        for (Productor prod1 : prod) {
            prod1.interrupt();
        }
        
        for (Thread tcon1 : tcon) {
            tcon1.interrupt();
        }
        
    }

}
