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
        setSize(400, 200);
    }
    
    @Override
    public void paint(Graphics g){
        this.setBackground(Color.GREEN);
        
        Image offscreen = createImage(this.getWidth(), this.getHeight()); // parpadeo
        Graphics og = offscreen.getGraphics();// parpadeo
        
        Font f = new Font("Cantarell", Font.TRUETYPE_FONT + Font.BOLD, 16); 
           
        
        //og.fillOval(25, 35, 20, 20);
        int i = 50;
        
        og.setColor(Color.red);
        og.setFont(f);
        
        if(datos != null && numelementos != 0) og.drawString((String) datos[cima-1], i, 50);
        i *= 2;

        g.drawImage(offscreen, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void avisa(String mensaje){
        System.out.println(mensaje);
        repaint();
    }
    
    public void representa(Object[] buf, int cima, int numele){
        datos = buf;
        this.cima = cima;
        numelementos = numele;
        repaint();
    }
}
