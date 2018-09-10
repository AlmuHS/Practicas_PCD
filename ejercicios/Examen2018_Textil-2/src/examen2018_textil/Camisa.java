/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_textil;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Camisa extends Thread{
    int id;
    Linea lin;
    
    public Camisa(int id, Linea lin){
        this.id = id;
        this.lin = lin;
    }
    
    public void run(){
        try {
            System.out.println("Llega camisa " + id);
            
            System.out.println("Camisa " + id + " entrando a corte");
            lin.EntraCorte();
            sleep(2000);
            System.out.println("Camisa " + id + " cortada");
            
            
            System.out.println("Camisa " + id + " entrando a cosido");
            lin.CoserCamisa();
            sleep(2000);
            System.out.println("Camisa " + id + " terminada");
            lin.SaleCoser();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Camisa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
