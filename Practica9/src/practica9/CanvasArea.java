package practica9;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pedro
 */
public class CanvasArea extends Canvas {

    private int[] rectangulo; 
    private int dx; //cantidad de intervalos
    private double area = 0.0;
     
    public CanvasArea(int ancho, int alto, int dx) {
        this.setSize(ancho, alto);
        this.dx = dx;
        rectangulo = new int[dx];
        for (int i = 0; i < dx; i++) {
            rectangulo[i]=0;
        }
    }

    public synchronized void pintacalculo(int id, int division){
        rectangulo[division]=id;
        this.repaint();        
    }
    
    public void pintaarea(double area){
        this.area = area;
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), ColorModel.OPAQUE);
        Graphics gbuf = imagen.getGraphics();
        gbuf.setColor(Color.white);
        gbuf.fillRect(0, 0, getWidth(), getHeight());

        int origenx = 100;
        int origeny = 250;
        int ancho = 250;
        int alto = 200;
        
       
        Font f1 = new Font("Arial", Font.BOLD, 16);
        gbuf.setFont(f1);
        gbuf.setColor(Color.black);
        gbuf.drawLine(origenx, origeny, origenx + ancho, origeny);
        gbuf.drawLine(origenx, origeny, origenx, origeny - alto);
        gbuf.drawString("0", origenx, origeny + 20);
        gbuf.drawString("1", origenx + ancho, origeny + 20);

        gbuf.drawString("Area calculada: "+ area, 100, 20);
        
        double YMAX = Funcion.fn(1);
        for (int i = 0; i < 100; i++) {
            if (Funcion.fn(i/100)>YMAX) {
                YMAX=Funcion.fn(i/100);
            }
        }
        
        String cadYmax = String.format("%1.1f",YMAX);
        gbuf.drawString(cadYmax, origenx - 25, origeny - alto + 10);

        gbuf.setColor(Color.blue);

        int antx = origenx, anty = origeny;
        for (int i = 0; i < ancho; i++) {
            double Y = Funcion.fn((double) i / ancho) * alto / YMAX;
            int puntox = origenx + i;
            int puntoy = (int) Math.round(origeny - Y);
            gbuf.drawLine(antx, anty, puntox, puntoy);
            antx = puntox;
            anty = puntoy;
        }
        
        for (int i = 0; i < dx; i++) {
            if(rectangulo[i]!=0){
                switch(rectangulo[i]%10){
                    case 0: gbuf.setColor(Color.red);
                        break;
                    case 1: gbuf.setColor(Color.BLACK);
                        break;
                    case 2: gbuf.setColor(Color.BLUE);
                        break;
                    case 3: gbuf.setColor(Color.CYAN);
                        break;                        
                    case 4: gbuf.setColor(Color.GREEN);
                        break;
                    case 5: gbuf.setColor(Color.MAGENTA);
                        break;
                    case 6: gbuf.setColor(Color.ORANGE);
                        break;
                    case 7: gbuf.setColor(Color.PINK);
                        break;                        
                    case 8: gbuf.setColor(Color.YELLOW);
                        break;
                    case 9: gbuf.setColor(Color.LIGHT_GRAY);
                        break;                        
                }
                double sizerect = Funcion.fn((double) i/dx + (double) 1/dx/2) * alto / YMAX;
                gbuf.fillRect(origenx+i*ancho/dx, origeny - (int) sizerect, ancho/dx, (int) sizerect);
            }
        }
        
        
        g.drawImage(imagen, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
