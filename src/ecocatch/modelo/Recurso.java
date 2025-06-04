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


public class Recurso implements Serializable {
    public enum Tipo { DINERO, ENERGIA, AGUA }
    private Tipo tipo;
    private int cantidad;

    public Recurso(Tipo tipo, int cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Tipo getTipo() { return tipo; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public void modificar(int delta) { this.cantidad += delta; }
}
