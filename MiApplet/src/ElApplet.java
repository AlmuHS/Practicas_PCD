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
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author almu
 */
public class ElApplet extends Applet {

    private Hilo h1, h2, h3;
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        Dimension d = new Dimension(400, 200);
        setSize(400, 200); // Establece el tamaño del applet
                
        ElCanvas cv = new ElCanvas(d);
        add(cv);
        
        
        Compartido c = new Compartido(0, 0, cv);
        h1 = new Hilo(c, 0);
        h2 = new Hilo(c, 1);
        h3 = new Hilo(c, 1);
    }
    
    @Override
    public void start(){
            h1.start();
            h2.start();
            h3.start();        
    }
    
    @Override
    public void stop(){
            h1.interrupt();
            h2.interrupt();
            //h3.interrupt();
    }
    // TODO overwrite start(), stop() and destroy() methods
}
