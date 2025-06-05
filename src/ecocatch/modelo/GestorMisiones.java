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
        agregarMision(new Mision(4, "Toxico", "Atrapa muchas baterías tóxicas.", "Atrapa 10 elementos tóxicos."));
        agregarMision(new Mision(5, "Rey del reciclaje", "Reduce la contaminación", "Atrapa 15 elementos tóxicos, 10 elementos orgánicos y 20 elementos inorgánicos. "));
        // ... puedes agregar más misiones aquí
    }

    public void agregarMision(Mision mision) {
        misiones.put(mision.getId(), mision);
    }

    /**
     * Agrega una nueva misión generando automáticamente un ID único.
     */
    public void agregarMisionAutoId(String nombre, String descripcion, String objetivo) {
        int nuevoId = getSiguienteMisionId();
        Mision mision = new Mision(nuevoId, nombre, descripcion, objetivo);
        agregarMision(mision);
    }

    /**
     * Devuelve el siguiente ID único para una nueva misión.
     */
    public int getSiguienteMisionId() {
        return misiones.keySet().stream().mapToInt(Integer::intValue).max().orElse(0) + 1;
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
