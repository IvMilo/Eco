/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.util.EnumMap;
/**
 *
 * @author Milo
 */


public class GestorRecursos {
    private EnumMap<Recurso.Tipo, Integer> recursos;

    public GestorRecursos() {
        recursos = new EnumMap<>(Recurso.Tipo.class);
        for (Recurso.Tipo t : Recurso.Tipo.values()) {
            recursos.put(t, 100); // valor inicial por defecto
        }
    }

    public int getCantidad(Recurso.Tipo tipo) {
        return recursos.getOrDefault(tipo, 0);
    }

    public void modificarRecurso(Recurso.Tipo tipo, int delta) {
        recursos.put(tipo, getCantidad(tipo) + delta);
    }

    public boolean consumirRecurso(Recurso.Tipo tipo, int cantidad) {
        int actual = getCantidad(tipo);
        if (actual >= cantidad) {
            recursos.put(tipo, actual - cantidad);
            return true;
        }
        return false;
    }
}
