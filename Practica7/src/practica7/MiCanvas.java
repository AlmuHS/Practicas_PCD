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

package practica7;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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
    Boolean[] lectoractivo;
    Boolean[] escritoractivo;
    Boolean[] lectorescritor;
    
    public MiCanvas(int numlectores, int numescritores){
        this.setSize(500, 350); //Tamano del canvas
        this.numlectores = numlectores; //Numero de lectores
        this.numescritores = numescritores; //Numero de escritores
        
        poslectores = new int[numlectores]; //Posiciones y de los lectores
        posescritores = new int [numescritores]; //Posicion y de los escritores
        
        lectoractivo = new Boolean[numlectores];//indicador de lector activo
        escritoractivo = new Boolean[numescritores];//indicador de escritor activo
        lectorescritor = new Boolean[numlectores];
        
        //La posicion y inicial de los lectores es 20
        for(int i = 0; i < numlectores; i++){
            poslectores[i] = 20;
            lectoractivo[i] = false;
            lectorescritor[i] = false;
        }
        
        //La posicion y inicial de los escritores es 50
        for(int i = 0; i < numescritores; i++){
            posescritores[i] = 70;
            escritoractivo[i] = false;
        }
    }
    
    @Override
    public void paint(Graphics g) {

        //Color de fondo del canvas
        this.setBackground(Color.GREEN);

        //Creamos la imagen a dibujar en el canvas
        Image offscreen = createImage(this.getWidth(), this.getHeight()); // parpadeo
        Graphics og = offscreen.getGraphics();// parpadeo
        
        Font f = new Font("DejaVu Sans", Font.TRUETYPE_FONT + Font.BOLD + Font.PLAIN, 14);
        og.setFont(f);
        
        //Pintamos los lectores
        for(int i = 0; i < numlectores; i++){
            if(lectoractivo[i]){
                og.setColor(Color.ORANGE);
                og.fillOval(50 + 50 * i, poslectores[i], 40, 40);
                if(lectorescritor[i]){
                    og.setColor(Color.BLUE);
                    og.fillOval(50 + 50 * i, poslectores[i], 30, 30);
                }
                og.setColor(Color.RED);
               og.drawString(String.valueOf(i), 65 + 50*i, poslectores[i] + 25);
            }
        }
        
        //Linea de separacion de seccion critica
        og.setColor(Color.RED);
        og.drawLine(50, 115, 350, 115);
        
        //Letrero de lectores
        og.setColor(Color.ORANGE);
        og.drawString("Lectores", 370, 40);
        
        
        //Pintamos los escritores
        for(int i = 0; i < numescritores; i++){
            if(escritoractivo[i]){
               og.setColor(Color.BLUE);
               og.fillOval(50 + 50*i, posescritores[i], 40, 40);
               og.setColor(Color.CYAN);
               og.drawString(String.valueOf(i), 65 + 50*i, posescritores[i] + 25);
            }
        }
        
        //Letrero de escritores
        og.setColor(Color.BLUE);
        og.drawString("Escritores", 370, 90);
        
        //Letrero de seccion critica
        og.setColor(Color.RED);
        og.drawString("Seccion critica", 370, 180);
        
        
        og.setColor(Color.MAGENTA);
        og.drawLine(50, 200, 350, 200);
        
        //Letrero de finalizados
        og.setColor(Color.MAGENTA);
        og.drawString("Finalizados", 370, 250);
        
        //Dibujamos la imagen
        g.drawImage(offscreen, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void llegaEscritor(int id){
        this.escritoractivo[id] = true;
        repaint();
    }
    
    public void llegaLector(int id){
        this.lectoractivo[id] = true;
        repaint();
    }
    
    public void avisaSC(int tipo, int id, int entra){
        
       
        switch (tipo) {
             //Si es un lector
            case 0:
                //Si entra en la seccion critica, muestralo en el area central
                lectorescritor[id] = false;
                if(entra == 1) poslectores[id] += 110;
                
                //Si sale de la seccion critica, muestralo en la zona de finalizados
                else poslectores[id] += 80;
                break;
                
            //Si es un escritor
            case 1:
                //Si entra en la seccion critica, muestralo en el area central
                if(entra == 1) posescritores[id] += 80;
                
                //Si sale de la seccion critica, muestralo en la zona de finalizados
                else posescritores[id] += 110;
                break;
                
            //Si es un lector transformado a escritor
            case 2:
                 //Si entra en la seccion critica, incrementa su posicion
                lectorescritor[id] = true;
                if(entra == 1) poslectores[id] += 110;
                //Si sale de la seccion critica, muestralo en la zona de finalizados
                else poslectores[id] += 80;
                break;
                
            default:
                break;
        }
        
        
        repaint();
    }
    
    
    
}
