/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

/**
 *
 * @author Milo
 */
public class ListaElementos {
    private NodoElemento cabeza;

    public void agregar(FallingElement e) {
        NodoElemento nuevo = new NodoElemento(e);
        if (cabeza == null) cabeza = nuevo;
        else {
            NodoElemento t = cabeza;
            while (t.siguiente != null) t = t.siguiente;
            t.siguiente = nuevo;
        }
    }

    public void eliminar(NodoElemento target, NodoElemento previo) {
        if (previo == null) cabeza = target.siguiente;
        else previo.siguiente = target.siguiente;
    }

    public NodoElemento getCabeza() { return cabeza; }
}
