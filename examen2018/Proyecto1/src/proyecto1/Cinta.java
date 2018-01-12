/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class Cinta implements Runnable{
    Monton M;
    
    public Cinta(Monton M){
        this.M = M;
    }
    
    public void run(){
        Random rand = new Random(); 
        int mineral;
        
        while(true){
            mineral = rand.nextInt(3)+ 2;
            M.Rellena(mineral);
            
            try {
                sleep(rand.nextInt(4) + 1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cinta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
}
