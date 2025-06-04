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


public class Decision implements Serializable {
    private final String descripcion;
    private final String efectoCorto;
    private final String efectoLargo;

    public Decision(String descripcion, String efectoCorto, String efectoLargo) {
        this.descripcion = descripcion;
        this.efectoCorto = efectoCorto;
        this.efectoLargo = efectoLargo;
    }

    public String getDescripcion() { return descripcion; }
    public String getEfectoCorto() { return efectoCorto; }
    public String getEfectoLargo() { return efectoLargo; }
}
