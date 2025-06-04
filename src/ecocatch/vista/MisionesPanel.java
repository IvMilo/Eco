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

public class MisionesPanel extends JPanel {
    private final GestorMisiones gestorMisiones;
    private final Usuario usuario;
    private final JButton btnJugar;
    private final JList<String> listaMisiones;
    private final JLabel lblObjetivo, lblEstado;
    private List<Mision> misiones; // Cambia a variable de instancia

    public MisionesPanel(GestorMisiones gestorMisiones, Usuario usuario, Runnable onSeleccionarMision) {
        this.gestorMisiones = gestorMisiones;
        this.usuario = usuario;
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Seleccionar Misión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // Inicialmente carga todas las misiones
        misiones = gestorMisiones.getMisiones().stream().sorted((a,b) -> a.getId()-b.getId()).collect(Collectors.toList());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Mision m : misiones) {
            modelo.addElement("[" + m.getId() + "] " + m.getNombre() + (usuario.getMisionesCompletadas().contains(m.getId()) ? " (✔)" : ""));
        }
        listaMisiones = new JList<>(modelo);
        listaMisiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(listaMisiones), BorderLayout.CENTER);

        JPanel pnlInferior = new JPanel(new GridLayout(3, 1));
        lblObjetivo = new JLabel(" ", SwingConstants.CENTER);
        lblEstado = new JLabel(" ", SwingConstants.CENTER);
        btnJugar = new JButton("Jugar misión");
        pnlInferior.add(lblObjetivo);
        pnlInferior.add(lblEstado);
        pnlInferior.add(btnJugar);
        add(pnlInferior, BorderLayout.SOUTH);

        listaMisiones.addListSelectionListener(e -> {
            int idx = listaMisiones.getSelectedIndex();
            if (idx >= 0 && idx < misiones.size()) {
                Mision m = misiones.get(idx);
                lblObjetivo.setText("Objetivo: " + m.getObjetivo());
                lblEstado.setText(usuario.getMisionesCompletadas().contains(m.getId()) ? "¡Completada!" : "Por completar");
            } else {
                lblObjetivo.setText(" ");
                lblEstado.setText(" ");
            }
        });

        btnJugar.addActionListener(e -> {
            if (!listaMisiones.isSelectionEmpty()) {
                onSeleccionarMision.run();
            }
        });
    }

    /**
     * Refresca el listado de misiones mostrado en la lista, útil después de añadir o borrar misiones.
     */
    public void actualizarMisiones() {
        misiones = gestorMisiones.getMisiones().stream().sorted((a,b) -> a.getId()-b.getId()).collect(Collectors.toList());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Mision m : misiones) {
            modelo.addElement("[" + m.getId() + "] " + m.getNombre() + (usuario.getMisionesCompletadas().contains(m.getId()) ? " (✔)" : ""));
        }
        listaMisiones.setModel(modelo);
        lblObjetivo.setText(" ");
        lblEstado.setText(" ");
        listaMisiones.clearSelection();
        listaMisiones.revalidate();
        listaMisiones.repaint();
    }

    public int getMisionSeleccionada() {
        int idx = listaMisiones.getSelectedIndex();
        // Devuelve el id real o -1 si no hay selección
        if (idx >= 0 && idx < misiones.size()) {
            return misiones.get(idx).getId();
        }
        return -1;
    }
}