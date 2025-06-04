/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.io.Serializable;
/**
 *
 * @author Milo
 */


public class Mision implements Serializable {
    private final int id;
    private final String nombre;
    private final String descripcion;
    private boolean completada;
    private final String objetivo; // Texto de objetivo principal

    public Mision(int id, String nombre, String descripcion, String objetivo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.completada = false;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getObjetivo() { return objetivo; }
    public boolean isCompletada() { return completada; }
    public void marcarCompletada() { this.completada = true; }
}
