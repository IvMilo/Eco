/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Milo
 */

public class DecisionesPanel extends JPanel {
    private final PilaDecisiones pilaDecisiones;
    private final DefaultListModel<String> modelo;

    public DecisionesPanel(PilaDecisiones pilaDecisiones, Runnable onActualizar) {
        this.pilaDecisiones = pilaDecisiones;
        this.modelo = new DefaultListModel<>();

        setLayout(new BorderLayout());
        JLabel lblTitulo = new JLabel("Historial de Decisiones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        JList<String> lista = new JList<>(modelo);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        actualizar();
    }

    public void actualizar() {
        modelo.clear();
        for (Decision d : pilaDecisiones.getDecisiones()) {
            modelo.addElement(
                d.getDescripcion() + " | " + d.getEfectoCorto() + " | " + d.getEfectoLargo()
            );
        }
    }
}

