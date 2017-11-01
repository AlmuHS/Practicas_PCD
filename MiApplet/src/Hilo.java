
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author almu
 */
public class Hilo extends Thread {

    private int contador;
    private Compartido c;

    public Hilo(Compartido c, int contador) {
        this.contador = contador;
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            c.incrementa(contador);
        }
    }

}
