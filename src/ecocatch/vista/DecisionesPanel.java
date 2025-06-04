/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Milo
 */


public class DecisionesPanel extends JPanel {
    private final PilaDecisiones pilaDecisiones;
    private final DefaultListModel<String> modelo;
    private final JList<String> listaDecisiones;
    private final JButton btnDeshacer;

    public DecisionesPanel(PilaDecisiones pilaDecisiones, Runnable onDeshacer) {
        this.pilaDecisiones = pilaDecisiones;
        setLayout(new BorderLayout());
        modelo = new DefaultListModel<>();
        listaDecisiones = new JList<>(modelo);
        add(new JLabel("Decisiones tomadas:"), BorderLayout.NORTH);
        add(new JScrollPane(listaDecisiones), BorderLayout.CENTER);
        btnDeshacer = new JButton("Deshacer última");
        add(btnDeshacer, BorderLayout.SOUTH);

        btnDeshacer.addActionListener(e -> {
            pilaDecisiones.deshacerDecision();
            actualizar();
            onDeshacer.run();
        });
    }

    public void actualizar() {
        modelo.clear();
        List<Decision> decisiones = pilaDecisiones.getDecisiones().stream().collect(Collectors.toList());
        for (Decision d : decisiones) {
            modelo.addElement(d.getDescripcion() + " (Corto: " + d.getEfectoCorto() + ", Largo: " + d.getEfectoLargo() + ")");
        }
    }
}
