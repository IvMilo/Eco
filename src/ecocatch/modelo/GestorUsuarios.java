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


public class GestorUsuarios {
    private Map<String, Usuario> usuarios; // Multilista: username -> Usuario
    private Usuario usuarioActual;
    private static final String ARCHIVO_USUARIOS = "ecocatch_usuarios.dat";

    public GestorUsuarios() {
        usuarios = new HashMap<>();
        cargarUsuarios();
    }

    public boolean registrarUsuario(String username, String password) {
        if (usuarios.containsKey(username)) return false;
        Usuario user = new Usuario(username, password);
        usuarios.put(username, user);
        guardarUsuarios();
        return true;
    }

    public boolean login(String username, String password) {
        Usuario user = usuarios.get(username);
        if (user != null && user.verificarPassword(password)) {
            usuarioActual = user;
            return true;
        }
        return false;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void guardarUsuarios() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            out.writeObject(usuarios);
        } catch (IOException e) { e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    public void cargarUsuarios() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            usuarios = (HashMap<String, Usuario>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            usuarios = new HashMap<>();
        }
    }
}
