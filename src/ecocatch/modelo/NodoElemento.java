/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

/**
 *
 * @author Milo
 */
public class NodoElemento {
    public FallingElement elemento;
    public NodoElemento   siguiente;

    public NodoElemento(FallingElement elemento) {
        this.elemento = elemento;
    }
}

