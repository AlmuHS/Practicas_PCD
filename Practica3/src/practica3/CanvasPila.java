package practica3;


import java.awt.Canvas;
import java.awt.Graphics;

/*
 * Copyright (C) 2017 almu
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

/**
 *
 * @author almu
 */
public class CanvasPila extends Canvas{
    private int cima;
    private int capacidad;
    private int numelementos;
    private Object datos[];
    private String mensaje;
    
    public CanvasPila(int capacidad){
        
        this.capacidad = capacidad;        
    }
    
    public void paint(Graphics g){
        
    }
    
    public void update(Graphics g){
        
    }
    
    public void avisa(String mensaje){
        
    }
    
    public void representa(Object[] buf, int cima, int numele){
        
    }
}
