/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_taller.pkg1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Generador{
   Taller T;
   Thread[] vehiculos;
   int numvehiculos;
   
   public Generador(){
       numvehiculos = 10;
       vehiculos = new Thread[numvehiculos];
       T = new Taller();
   }
   
   public void lanzarHilos(){
       Random rand = new Random();
       rand.setSeed(System.currentTimeMillis());
       
       for (int i = 0; i < vehiculos.length; i++) {
       
           if(rand.nextInt(10) < 7){
               Coche C = new Coche(i, T);
               vehiculos[i] = new Thread(C);
           }
           else{
               Camion CA = new Camion(i, T);
               vehiculos[i] = new Thread(CA);
           }
           vehiculos[i].start();   
       }
       
       for (Thread vehiculo : vehiculos) {
           try {
               vehiculo.join();
           }catch (InterruptedException ex) {
               Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
   
   
}
