/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.GestorRecursos;
import ecocatch.modelo.Recurso;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Milo
 */


public class RecursosPanel extends JPanel {
    private final GestorRecursos gestorRecursos;
    private final JLabel lblDinero, lblEnergia, lblAgua;

    public RecursosPanel(GestorRecursos gestorRecursos) {
        this.gestorRecursos = gestorRecursos;
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        lblDinero = new JLabel();
        lblEnergia = new JLabel();
        lblAgua = new JLabel();
        add(lblDinero); add(lblEnergia); add(lblAgua);
        actualizar();
    }

    public void actualizar() {
        ImageIcon icon = null;
        java.net.URL iconURL = getClass().getResource("/ecocatch/recursos/dinero.png");
        if (iconURL != null) {
            ImageIcon original = new ImageIcon(iconURL);
            Image scaled = original.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaled);
        }
        lblDinero.setText("💰: " + gestorRecursos.getCantidad(Recurso.Tipo.DINERO));
        lblEnergia.setText("⚡: " + gestorRecursos.getCantidad(Recurso.Tipo.ENERGIA) + "  ");
        lblAgua.setText("💧: " + gestorRecursos.getCantidad(Recurso.Tipo.AGUA));
    }
}
