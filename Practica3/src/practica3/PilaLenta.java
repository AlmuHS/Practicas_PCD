package practica3;

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

/**
 *
 * @author usuario
 */
public class PilaLenta implements IPila{
    private int cima;
    private int capacidad;
    private int numelementos;
    private Object[] datos;
    private CanvasPila canvas;

    
    public PilaLenta(int capacidad, CanvasPila elcanvas){
        this.capacidad = capacidad;
        datos = new Object[capacidad];
        this.canvas = elcanvas;
        cima = 0;
        numelementos = 0;
    }
    
    @Override
    public int GetNum() {
        return numelementos;
    }

    @Override
    public synchronized void Apila(Object elemento) throws Exception{
        if(pilallena()){
            canvas.avisa("Error, la pila esta llena");
            throw new java.lang.Exception("Error, la pila esta llena");
        }
        else{
            datos[cima] = elemento;
            cima++;
            numelementos++;
            canvas.representa(datos, cima, numelementos);
        }
    }

    @Override
    public synchronized Object Desapila() throws Exception{
        
        if(pilavacia()){
            canvas.avisa("Error, la pila esta vacía");
            throw new java.lang.Exception("Error, la pila esta vacía");
        }
        
        else{
            Object primero = datos[cima - 1];
            cima--;
            numelementos--;
            canvas.representa(datos, cima, numelementos);
            return primero;
        }
    }
    
    @Override
    public Object Primero() throws Exception{
        if(pilavacia()){
            canvas.avisa("Error, la pila esta vacía");
            throw new java.lang.Exception("Error, la pila esta vacía");
        }
        else{
            return datos[cima - 1];
        }
    }
    
    private boolean pilavacia(){
       return (numelementos == 0);
        
    }
    
    private boolean pilallena(){
        return (numelementos == capacidad);
    }
    
    
}
