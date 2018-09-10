/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2018_taller.pkg1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almu
 */
public class Taller {

    int esperacamion;
    int operariosLibres;
    ReentrantLock mutex;
    Condition colaCamion;
    Condition colaCoche;

    public Taller() {
        esperacamion = 0;
        operariosLibres = 4;
        mutex = new ReentrantLock();
        colaCamion = mutex.newCondition();
        colaCoche = mutex.newCondition();
    }

    public void EntraCamion() {
        mutex.lock();
        try {
            esperacamion++;

            while (operariosLibres < 2) {
                try {
                    colaCamion.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            operariosLibres -= 2;
            esperacamion--;
            System.out.println("Operarios Libres: " + operariosLibres);
        } finally {
            mutex.unlock();
        }
    }

    public void EntraCoche() {
        mutex.lock();
        try {
            while (operariosLibres < 1 || esperacamion > 0) {
                try {
                    colaCoche.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Taller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            operariosLibres--;
            System.out.println("Operarios Libres: " + operariosLibres);
        } finally {
            mutex.unlock();
        }
    }

    public void SaleCamion() {
        mutex.lock();
        try {
            operariosLibres += 2;
            System.out.println("Operarios Libres: " + operariosLibres);
            
            if (esperacamion > 0 && operariosLibres >= 2) {
                colaCamion.signal();
            } else {
                colaCoche.signal();
            }

        } finally {
            mutex.unlock();
        }
    }

    public void SaleCoche() {
        mutex.lock();
        try {
            operariosLibres++;
            System.out.println("Operarios Libres: " + operariosLibres);

            if (esperacamion > 0 && operariosLibres >= 2) {
                colaCamion.signal();
            } else {
                colaCoche.signal();
            }

            
        } finally {
            mutex.unlock();
        }
    }

}
