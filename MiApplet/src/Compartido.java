/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author almu
 */
public class Compartido {
    
    private int[] contadores;
    private ElCanvas cv;
    
    public Compartido(int v1, int v2, ElCanvas cv){
        contadores = new int[2];
        contadores[0]=v1;
        contadores[1]=v2;
    }
    
    public synchronized void incrementa(int contador){
        contadores[contador]++;
        System.out.println("Contador 1: " + contadores[0] + "\tContador 2" + contadores[1]);
        cv.actualiza(contadores);
    }
    
    
    
}
