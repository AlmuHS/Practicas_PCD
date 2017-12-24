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


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;


/**
 *
 * @author almu
 */
public class ElCanvas extends Canvas{
    
    private int counter[];
    
   public ElCanvas(Dimension d){
       this.setSize(d);
       this.setBackground(Color.pink);
   }
   
   public void actualiza(int counter[]){
        this.counter = counter;
        this.repaint();// necesario para ir actualizando
   }
   
   @Override
   public void paint(Graphics g){
      Image offscreen = createImage(this.getWidth(), this.getHeight()); // parpadeo
      Graphics og = offscreen.getGraphics();// parpadeo
      
      Font f = new Font("Cantarell", Font.TRUETYPE_FONT + Font.BOLD, 16); 
       
      if(counter != null){
        og.setColor(Color.red);
        //og.fillOval(25, 35, 20, 20);
        og.setFont(f);
        og.drawString("valor del contador 1: " + counter[0], 50, 50);

        og.setColor(Color.GREEN);
        //og.fillOval(25, 85, 20, 20);
        og.drawString("valor del contador 2: " + counter[1], 50, 100);
      }
     
      g.drawImage(offscreen, 0, 0, null);
   }

    @Override
   public void update(Graphics g){
       paint(g);
   }
   
   
}
