/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5;

import java.applet.Applet;
import java.awt.Color;

/**
 *
 * @author almu
 */
public class MiApplet extends Applet {
    
    MiCanvas cv;
    Escritor[] escritores; //array de hilos escritores
    Lector[] lectores; //array de hilos lectores
    Thread[] tescritores;
    
    int nescritores; //numero de escritores
    int nlectores; //numero de lectores
    
    
    public void init() {
        
       //Indicamos numero de lectores y escritores
       nescritores = 4;
       nlectores = 2;
       
       //Creamos los arrays de lectores y escritores
       escritores = new Escritor[nescritores];
       lectores = new Lector[nlectores];
       tescritores = new Thread[nescritores];
       
       //Creamos el objeto compartido
       Compartido comp = new Compartido(nlectores);
       
       //Creamos el canvas
       cv = new MiCanvas();
       
       //Inicializamos el Applet
       this.setSize(450, 700);
       this.setBackground(Color.red);
       
       //Añadimos el canvas
       this.add(cv); 
       
       //Inicializamos lectores y escritores
       for(int i = 0; i < nescritores; i++){
           escritores[i] = new Escritor(comp);
           tescritores[i] = new Thread(escritores[i]);
       }
       
       for(int j = 0; j < nlectores; j++){
           lectores[j] = new Lector(comp);
       }
    }
    
    
    @Override
    public void start(){
        
       //Lanzamos los hilos lectores y escritores
       for(int i = 0; i < nescritores; i++){
           tescritores[i].start();
       }
       
       for(int i = 0; i < nlectores; i++){
           lectores[i].start();
       } 
    }

}
