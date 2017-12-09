package practica8;

import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author almu
 */
public class Shared {
    public Queue<Integer> BusQueue;
    public Queue<Integer> CarQueue;
    public int freeCar;
    public int freeBus;
    
    public Shared(){
        BusQueue = new LinkedList();
        CarQueue = new LinkedList();
        freeCar = 3;
        freeBus = 2;
    }
}
