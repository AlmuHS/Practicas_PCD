/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holamundo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class HolaMundo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Saludador hola = new Saludador();

        try {
            hola.saluda("hola mundo");
        } catch (Exception ex) {
            //Logger.getLogger(HolaMundo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error, debe introducir un valor distinto a 0");
        }

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int r = rand.nextInt();
        
        if(r % 2 == 0){
            //par
        }
        else{
            //impar
        }
                
    }

}
