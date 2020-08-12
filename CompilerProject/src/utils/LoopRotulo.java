/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Computador
 */
public class LoopRotulo {
    static int counter = 0;
    
    static String newLoop() {
        LoopRotulo.counter++;
        return "Loop" + LoopRotulo.counter;
    }
}
