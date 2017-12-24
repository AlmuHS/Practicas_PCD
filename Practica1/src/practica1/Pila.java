/*
 * Copyright (C) 2017 Almudena García Jurado-Centurión
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package practica1;

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
            datos[cima] = elemento;
            numelementos++;
            cima++;
        }
    }

    @Override
    public Object Desapila() throws Exception{
        if(pilavacia())
            throw new java.lang.Exception("Error: la pila esta vacia");
        else{
            cima--;
            Object primero = datos[cima];
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
