package practica1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Pila implements IPila{
    private int cima;
    private int capacidad;
    private int numelementos;
    private Object[] datos;

    
    public Pila(int capacidad){
        this.capacidad = capacidad;
        datos = new Object[capacidad];
        cima = 0;
        numelementos = 0;
    }
    
    @Override
    public int GetNum() {
        return numelementos;
    }

    @Override
    public void Apila(Object elemento) throws Exception{
        if(pilallena()){
            throw new java.lang.Exception("Error: la pila esta llena");
        }
        else{
            cima++;
            datos[cima] = elemento;
            numelementos++;
        }
    }

    @Override
    public Object Desapila() throws Exception{
        if(pilavacia())
            throw new java.lang.Exception("Error: la pila esta vacia");
        else{
            Object primero = datos[cima];
            cima--;
            numelementos--;
            return primero;
        }
    }
    
    @Override
    public Object Primero() throws Exception{
        if(pilavacia())
            throw new java.lang.Exception("Error: la pila esta vacia");
        else{
            return datos[cima];
        }
    }
    
    private boolean pilavacia(){
       return (numelementos == 0);
        
    }
    
    private boolean pilallena(){
        return (numelementos == capacidad);
    }
    
    
}
