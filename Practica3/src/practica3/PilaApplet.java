package practica3;

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
    
    private Productor prod; 
    private Consumidor cons;
    Thread tcon; 
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
       CanvasPila cp = new CanvasPila(10);
       PilaLenta pila = new PilaLenta(10, cp);
       
       setSize(400, 200);
       this.setBackground(Color.red);
       
       add(cp); 
       
       prod = new Productor(pila);
       cons = new Consumidor(pila);
       tcon = new Thread(cons);      
    }
    
    @Override
    public void start(){  
        prod.start();
        tcon.start();
    }
    
    @Override
    public void stop(){
        prod.interrupt();
        tcon.interrupt();
    }

}
