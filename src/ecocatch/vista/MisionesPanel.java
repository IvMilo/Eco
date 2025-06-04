/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.*;
import javax.swing.*;
import javax.swing.border.*;
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
    private final JLabel lblObjetivo, lblEstado, lblTitulo;
    private List<Mision> misiones;

    public MisionesPanel(GestorMisiones gestorMisiones, Usuario usuario, Runnable onSeleccionarMision) {
        this.gestorMisiones = gestorMisiones;
        this.usuario = usuario;
        setLayout(new BorderLayout());
        setBackground(new Color(223, 245, 223));

        // Título con icono ecológico
        lblTitulo = new JLabel("🌱 Seleccionar Misión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(34, 80, 34));
        lblTitulo.setBorder(new EmptyBorder(30, 10, 20, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Misiones
        misiones = gestorMisiones.getMisiones().stream().sorted((a,b) -> a.getId()-b.getId()).collect(Collectors.toList());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Mision m : misiones) {
            modelo.addElement(
                "<html><b>[" + m.getId() + "] " + m.getNombre() + "</b> " +
                (usuario.getMisionesCompletadas().contains(m.getId()) 
                    ? "<span style='color:#388e3c'>(✔ Completada)</span>"
                    : "<span style='color:#0288d1'>(Pendiente)</span>") +
                "</html>"
            );
        }
        listaMisiones = new JList<>(modelo);
        listaMisiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMisiones.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        listaMisiones.setBackground(new Color(240, 255, 245));
        listaMisiones.setForeground(new Color(30, 60, 30));
        listaMisiones.setBorder(new CompoundBorder(
            new LineBorder(new Color(120, 180, 120), 2, true),
            new EmptyBorder(10, 12, 10, 12)
        ));
        listaMisiones.setCellRenderer(new MisionesCellRenderer());
        JScrollPane scroll = new JScrollPane(listaMisiones);
        scroll.setBorder(new EmptyBorder(10, 25, 10, 25));
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con detalles y botón
        JPanel pnlInferior = new JPanel(new GridLayout(3, 1, 0, 6));
        pnlInferior.setOpaque(false);
        pnlInferior.setBorder(new EmptyBorder(20, 25, 25, 25));

        lblObjetivo = new JLabel(" ", SwingConstants.CENTER);
        lblObjetivo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblEstado = new JLabel(" ", SwingConstants.CENTER);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnJugar = new JButton("🌍 Jugar misión");
        btnJugar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnJugar.setBackground(new Color(56, 142, 60));
        btnJugar.setForeground(Color.white);
        btnJugar.setFocusPainted(false);
        btnJugar.setBorder(new LineBorder(new Color(34, 80, 34), 2, true));

        pnlInferior.add(lblObjetivo);
        pnlInferior.add(lblEstado);
        pnlInferior.add(btnJugar);
        add(pnlInferior, BorderLayout.SOUTH);

        // Lógica de selección
        listaMisiones.addListSelectionListener(e -> {
            int idx = listaMisiones.getSelectedIndex();
            if (idx >= 0 && idx < misiones.size()) {
                Mision m = misiones.get(idx);
                lblObjetivo.setText("🎯 Objetivo: " + m.getObjetivo());
                boolean completada = usuario.getMisionesCompletadas().contains(m.getId());
                lblEstado.setText(completada ? "✅ ¡Completada!" : "Por completar");
                lblEstado.setForeground(completada ? new Color(56, 142, 60) : new Color(30, 136, 229));
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
     * Refresca el listado de misiones mostrado en la lista.
     */
    public void actualizarMisiones() {
        misiones = gestorMisiones.getMisiones().stream().sorted((a,b) -> a.getId()-b.getId()).collect(Collectors.toList());
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Mision m : misiones) {
            modelo.addElement(
                "<html><b>[" + m.getId() + "] " + m.getNombre() + "</b> " +
                (usuario.getMisionesCompletadas().contains(m.getId()) 
                    ? "<span style='color:#388e3c'>(✔ Completada)</span>"
                    : "<span style='color:#0288d1'>(Pendiente)</span>")
                + "</html>"
            );
        }
        listaMisiones.setModel(modelo);
        lblObjetivo.setText(" ");
        lblEstado.setText(" ");
        listaMisiones.clearSelection();
        listaMisiones.revalidate();
        listaMisiones.repaint();
    }

    /**
     * Retorna el id de la misión seleccionada.
     */
    public int getMisionSeleccionada() {
        int idx = listaMisiones.getSelectedIndex();
        if (idx >= 0 && idx < misiones.size()) {
            return misiones.get(idx).getId();
        }
        return -1;
    }

    // CellRenderer para HTML y color de selección eco
    static class MisionesCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int idx, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, idx, isSelected, cellHasFocus);
            label.setOpaque(true);
            label.setBackground(isSelected ? new Color(200, 255, 200) : new Color(240, 255, 245));
            label.setBorder(new EmptyBorder(8, 12, 8, 12));
            return label;
        }
    }
}