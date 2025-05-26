/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

/**
 *
 * @author Milo
 */

public class NodoTipo {
    private final String tipo;           // "Orgánico", "Inorgánico", "Tóxico"
    private NodoElemento cabeza;         // inicio de su sublista
    private NodoTipo siguiente;          // siguiente categoría

    public NodoTipo(String tipo) {
        this.tipo = tipo;
        this.cabeza = null;
        this.siguiente = null;
    }

    public String getTipo() { return tipo; }

    public NodoElemento getCabeza() { return cabeza; }

    public NodoTipo getSiguiente() { return siguiente; }

    public void setSiguiente(NodoTipo siguiente) {
        this.siguiente = siguiente;
    }

    public void agregarElemento(FallingElement e) {
        NodoElemento nuevo = new NodoElemento(e);
        if (cabeza == null) cabeza = nuevo;
        else {
            NodoElemento temp = cabeza;
            while (temp.siguiente != null) temp = temp.siguiente;
            temp.siguiente = nuevo;
        }
    }
}

