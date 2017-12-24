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
