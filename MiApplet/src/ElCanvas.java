
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
