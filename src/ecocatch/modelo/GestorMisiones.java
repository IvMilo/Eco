/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.io.*;
import java.util.*;
/**
 *
 * @author Milo
 */


public class GestorMisiones {
    private Map<Integer, Mision> misiones; // Multilista de misiones por ID
    private static final String ARCHIVO_MISIONES = "ecocatch_misiones.dat";

    public GestorMisiones() {
        misiones = new HashMap<>();
        cargarMisiones();
        if (misiones.isEmpty()) {
            inicializarMisiones();
            guardarMisiones();
        }
    }

    public void inicializarMisiones() {
        agregarMision(new Mision(1, "Deforestación", "Evita la deforestación atrapando residuos orgánicos.", "Atrapa 3 elementos orgánicos."));
        agregarMision(new Mision(2, "Deshielo", "Reduce el deshielo atrapando recursos energéticos.", "Atrapa 3 elementos inorgánicos."));
        agregarMision(new Mision(3, "Emisiones", "Reduce emisiones atrapando baterías tóxicas.", "Atrapa 2 elementos tóxicos."));
        // ... agregar más misiones según necesidad
    }

    public void agregarMision(Mision mision) {
        misiones.put(mision.getId(), mision);
    }

    public Collection<Mision> getMisiones() {
        return misiones.values();
    }

    public Mision getMision(int id) {
        return misiones.get(id);
    }

    public void guardarMisiones() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_MISIONES))) {
            out.writeObject(misiones);
        } catch (IOException e) { e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    public void cargarMisiones() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_MISIONES))) {
            misiones = (HashMap<Integer, Mision>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            misiones = new HashMap<>();
        }
    }
}
