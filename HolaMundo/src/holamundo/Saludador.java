/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holamundo;

/**
 *
 * @author usuario
 */
public class Saludador implements ISaludador{
    private int num = 0;
    
    public Saludador(int num){
        this.num = num;
    }
    
    public Saludador(){
       this.num = 0;
    }
    
    public void saluda(String mensaje) throws Exception{
        if(this.num != 0){
            System.out.println(mensaje);
        }
        else{
            //error
            //throw new Exception("No es posible saludar con 0");
            System.out.println("Debe introducir un valor distinto a cero");
        }
        
    }
}
