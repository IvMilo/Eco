/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

/**
 *
 * @author Milo
 */
public class MultiListaElementos {
    private NodoTipo raiz;

    public MultiListaElementos() {
        inicializarTipos();
    }

    private void inicializarTipos() {
        NodoTipo toxico     = new NodoTipo("Tóxico");
        NodoTipo inorganico = new NodoTipo("Inorgánico");
        NodoTipo organico   = new NodoTipo("Orgánico");

        organico.setSiguiente(inorganico);
        inorganico.setSiguiente(toxico);

        raiz = organico;
    }

    public void agregar(FallingElement e) {
        NodoTipo actual = raiz;
        while (actual != null) {
            if (actual.getTipo().equalsIgnoreCase(e.getTipo())) {
                actual.agregarElemento(e);
                return;
            }
            actual = actual.getSiguiente();
        }
        // Si llega aquí, es porque el tipo no existe (debería evitarse)
        System.err.println("Tipo no reconocido: " + e.getTipo());
    }

    public void mostrarMultilista() {
        NodoTipo actualTipo = raiz;
        while (actualTipo != null) {
            System.out.println(">>> " + actualTipo.getTipo() + ":");
            NodoElemento temp = actualTipo.getCabeza();
            while (temp != null) {
                System.out.println("   - " + temp.elemento);
                temp = temp.siguiente;
            }
            actualTipo = actualTipo.getSiguiente();
        }
    }

    public int contarPorTipo(String tipo) {
        NodoTipo actual = raiz;
        while (actual != null) {
            if (actual.getTipo().equalsIgnoreCase(tipo)) {
                int count = 0;
                NodoElemento temp = actual.getCabeza();
                while (temp != null) {
                    count++;
                    temp = temp.siguiente;
                }
                return count;
            }
            actual = actual.getSiguiente();
        }
        return 0;
    }
}
