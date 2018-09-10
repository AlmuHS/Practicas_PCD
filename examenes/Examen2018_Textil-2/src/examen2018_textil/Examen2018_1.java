/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_textil;

/**
 *
 * @author almu
 */
public class Examen2018_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Linea L1 = new Linea();
        
        Generador Gen = new Generador(L1);
        
        Gen.start();
        
    }
    
}
