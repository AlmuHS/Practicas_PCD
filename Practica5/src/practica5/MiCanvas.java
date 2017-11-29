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

    int numlectores, numescritores;
    
    int[] poslectores;
    int[] posescritores;
    
    public MiCanvas(int numlectores, int numescritores){
        this.setSize(450, 300); //Tamano del canvas
        this.numlectores = numlectores; //Numero de lectores
        this.numescritores = numescritores; //Numero de escritores
        
        poslectores = new int[numlectores]; //Posiciones y de los lectores
        posescritores = new int [numescritores]; //Posicion y de los escritores
        
        //La posicion y inicial de los lectores es 20
        for(int i = 0; i < numlectores; i++){
            poslectores[i] = 20; 
        }
        
        //La posicion y inicial de los escritores es 50
        for(int i = 0; i < numescritores; i++){
            posescritores[i] = 50;
        }
    }
    
    @Override
    public void paint(Graphics g) {

        //Color de fondo del canvas
        this.setBackground(Color.GREEN);

        //Creamos la imagen a dibujar en el canvas
        Image offscreen = createImage(this.getWidth(), this.getHeight()); // parpadeo
        Graphics og = offscreen.getGraphics();// parpadeo
        
        //Pintamos los lectores
        for(int i = 0; i < numlectores; i++){
            og.setColor(Color.ORANGE);
            og.fillOval(20 + 30 * i, poslectores[i], 20, 20);
        }
        
        //Pintamos los escritores
        for(int i = 0; i < numescritores; i++){
            og.setColor(Color.BLUE);
            og.fillOval(20 + 30*i, posescritores[i], 20, 20);
        }
        
        //Dibujamos la imagen
        g.drawImage(offscreen, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void avisaSC(int tipo, int id, int entra){
        
        //Si es un lector
        if(tipo == 0){
            //Si entra en la seccion critica, incrementa su posicion
            if(entra == 1) poslectores[id] += 50; 
            
            //Si sale de la seccion critica, devuelvelo a su posicion original
            else poslectores[id] -= 50;
        }
        
        //Si es un escritor
        else if(tipo == 1){
            
            //Si entra en la seccion critica, incrementa su posicion
            if(entra == 1) posescritores[id] += 20;
            
            //Si sale de la seccion critica, devuelvelo a su posicion original
            else posescritores[id] -= 20;
        }
        
        repaint();
    }
    
    
    
}
