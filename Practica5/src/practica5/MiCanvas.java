/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author almu
 */
public class MiCanvas extends Canvas {

    
    public MiCanvas(){
        this.setSize(450, 700);
    }
    
    @Override
    public void paint(Graphics g) {

        this.setBackground(Color.GREEN);

        Image offscreen = createImage(this.getWidth(), this.getHeight()); // parpadeo
        Graphics og = offscreen.getGraphics();// parpadeo
        
        og.setColor(Color.ORANGE);
        og.fillOval(20, 20, 20, 20);
        
        og.setColor(Color.red);

        g.drawImage(offscreen, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

}
