/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.util.Stack;
import java.io.Serializable;
/**
 *
 * @author Milo
 */


public class PilaDecisiones implements Serializable {
    private Stack<Decision> pila;

    public PilaDecisiones() {
        pila = new Stack<>();
    }

    public void agregarDecision(Decision d) {
        pila.push(d);
    }

    public Decision deshacerDecision() {
        return pila.isEmpty() ? null : pila.pop();
    }

    public Stack<Decision> getDecisiones() {
        return pila;
    }
}
