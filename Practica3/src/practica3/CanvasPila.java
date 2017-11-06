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
        setSize(450, 600);
    }
    
    @Override
    public void paint(Graphics g){
        this.setBackground(Color.lightGray);
        
        Image offscreen = createImage(this.getWidth(), this.getHeight()); // parpadeo
        Graphics og = offscreen.getGraphics();// parpadeo
        
        Font f = new Font("DejaVu Sans", Font.TRUETYPE_FONT + Font.BOLD + Font.PLAIN, 30); 
            
        og.setFont(f);
        og.setColor(Color.red);
        
        //Dibujo cuadrados
        for (int i = 0; i < capacidad; i++) {
            og.drawRect(200, 85 + 40 * (i-1), 80, 80);
        }
        
        //Dibujo contenidos de la pila
        if(datos != null){
            for (int i = 0; i < numelementos; i++) {
                Object dato = datos[i];
                if(dato != null) og.drawString(dato.toString(), 200, 80 + 40*i);
            }
            
            //Muestro numero de elementos y capacidad actual
            Font f1 = new Font("DejaVu Sans", Font.TRUETYPE_FONT + Font.BOLD, 15);
            og.setFont(f1);
            og.drawString("Numero de elementos: " + String.valueOf(numelementos), 100, 510);
            og.drawString("Capacidad: " + String.valueOf(capacidad - numelementos), 100, 530);
        }
        //En caso de error, muestro mensaje
        else if(mensaje != null || numelementos == capacidad){
            Font f1 = new Font("DejaVu Sans", Font.TRUETYPE_FONT + Font.BOLD, 15);
            og.setFont(f1);
            og.drawString(mensaje, 100, 560);
        }
        g.drawImage(offscreen, 0, 0, null);
    }
    
    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    public void avisa(String mensaje){
        this.mensaje = mensaje;
        repaint();
    }
    
    public void representa(Object[] buf, int cima, int numele){
        datos = buf;
        this.cima = cima;
        numelementos = numele;
        repaint();
    }
}
