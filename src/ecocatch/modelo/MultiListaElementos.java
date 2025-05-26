/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *
 * @author Milo
 */
public class MultiListaElementos implements Iterable<FallingElement> {
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

    // --- NUEVO: Implementación de Iterable<FallingElement> ---
    @Override
    public Iterator<FallingElement> iterator() {
        return new Iterator<FallingElement>() {
            private NodoTipo tipoActual = raiz;
            private NodoElemento elemActual = (raiz != null) ? raiz.getCabeza() : null;

            private void avanzarSiNecesario() {
                while (tipoActual != null && elemActual == null) {
                    tipoActual = tipoActual.getSiguiente();
                    elemActual = (tipoActual != null) ? tipoActual.getCabeza() : null;
                }
            }

            @Override
            public boolean hasNext() {
                avanzarSiNecesario();
                return elemActual != null;
            }

            @Override
            public FallingElement next() {
                avanzarSiNecesario();
                if (elemActual == null) {
                    throw new NoSuchElementException();
                }
                FallingElement value = elemActual.elemento;
                elemActual = elemActual.siguiente;
                return value;
            }
        };
    }
}