/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.Random;

/**
 *
 * @author usuario
 */
public class UsaPila {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Pila p = new Pila(7);
        Random rand = new Random();

        int r; 
        for (int i = 0; i < 7; i++) {
           r = rand.nextInt()%10;
           System.out.println("Insertando numero " + r);
           p.Apila(r);
        }
        
        
    }
    
}
