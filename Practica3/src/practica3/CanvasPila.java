package practica3;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

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
    
    @Override
    public void paint(Graphics g){
        this.setSize(400, 200);
        this.setBackground(Color.GREEN);
        
        Image offscreen = createImage(this.getWidth(), this.getHeight()); // parpadeo
        Graphics og = offscreen.getGraphics();// parpadeo
        
        Font f = new Font("Cantarell", Font.TRUETYPE_FONT + Font.BOLD, 16); 
             
        og.setColor(Color.red);
        og.fillOval(25, 35, 20, 20);
        og.setFont(f);
        og.drawString("valor del contador 1: " , 50, 50);

        og.setColor(Color.GREEN);
        og.fillOval(25, 85, 20, 20);
        og.drawString("valor del contador 2: " , 50, 100);

     
        g.drawImage(offscreen, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void avisa(String mensaje){
        
    }
    
    public void representa(Object[] buf, int cima, int numele){
        
    }
}
