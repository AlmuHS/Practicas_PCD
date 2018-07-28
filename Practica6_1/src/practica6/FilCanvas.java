/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica6;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;


/**
 *
 * @author pedro
 */
public class FilCanvas extends Canvas {

    private int[] estados = new int[5];
    Image[] status = new Image[5];

    public FilCanvas(int ancho, int alto) {
        this.setSize(ancho, alto);
        this.setBackground(Color.white);
        for (int i = 0; i < 5; i++) {
            estados[i] = 0;
        }
        status[0] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/piensa.gif"));
        status[1] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/hambre.gif"));
        status[2] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/conizquierdo.gif"));
        status[3] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/conderecho.gif"));
        status[4] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/come.gif"));
    }

    public synchronized void ponestado(int filo, int estado) {
        estados[filo] = estado;
        this.repaint();
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }
    
    @Override
    public synchronized void paint(Graphics g) {
        MediaTracker dibu = new MediaTracker(this);
         BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), ColorModel.OPAQUE);
        Graphics gbuf = imagen.getGraphics();


        try {

            for (int i = 0; i < 5; i++) {
                dibu.addImage(status[i], i);
                dibu.waitForID(i);
            }

        } catch (java.lang.InterruptedException e) {
            System.out.println("Couldn't load one of the images");
        }

        gbuf.setColor(Color.white);
        gbuf.fillRect(0, 0, this.getWidth(), this.getHeight());
        gbuf.setColor(Color.blue);
        gbuf.fillRect(10, 100, 500, 100);
        gbuf.drawImage(status[estados[0]], 10, 10, null);
        gbuf.drawImage(status[estados[1]], 110, 10, null);
        gbuf.drawImage(status[estados[2]], 210, 10, null);
        gbuf.drawImage(status[estados[3]], 310, 10, null);
        gbuf.drawImage(status[estados[4]], 410, 10, null);
                        
        g.drawImage(imagen, 0, 0, this);

    }
}