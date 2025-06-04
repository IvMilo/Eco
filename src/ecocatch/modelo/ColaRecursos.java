/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.util.LinkedList;
import java.util.Queue;
import java.io.Serializable;
/**
 *
 * @author Milo
 */


public class ColaRecursos implements Serializable {
    private Queue<Recurso> recursos;

    public ColaRecursos() {
        recursos = new LinkedList<>();
    }

    public void agregarRecurso(Recurso r) {
        recursos.offer(r);
    }

    public Recurso usarRecurso() {
        return recursos.poll();
    }

    public Queue<Recurso> getRecursos() {
        return recursos;
    }
}