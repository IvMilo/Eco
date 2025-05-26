/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

/**
 *
 * @author Milo
 */
public class Elemento {
    private final String nombre;
    private final String tipo; // "Orgánico", "Inorgánico", "Tóxico"
    private final int puntos;

    public Elemento(String nombre, String tipo, int puntos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPuntos() {
        return puntos;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") -> " + puntos + " puntos";
    }
}

