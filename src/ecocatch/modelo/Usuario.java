/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.util.LinkedList;
import java.util.List;
import java.io.*;
/**
 *
 * @author Milo
 */


public class Usuario implements Serializable {
    private final String username;
    private String passwordHash;
    private int progreso; // nivel/mision máxima alcanzada
    private List<Integer> misionesCompletadas; // Multilista simple de ID de misiones completadas

    public Usuario(String username, String password) {
        this.username = username;
        this.passwordHash = Integer.toString(password.hashCode());
        this.misionesCompletadas = new LinkedList<>();
        this.progreso = 0;
    }

    public boolean verificarPassword(String password) {
        return this.passwordHash.equals(Integer.toString(password.hashCode()));
    }

    public String getUsername() {
        return username;
    }

    public int getProgreso() {
        return progreso;
    }

    public void completarMision(int idMision) {
        if (!misionesCompletadas.contains(idMision)) {
            misionesCompletadas.add(idMision);
            if (idMision > progreso) progreso = idMision;
        }
    }

    public List<Integer> getMisionesCompletadas() {
        return misionesCompletadas;
    }
}
