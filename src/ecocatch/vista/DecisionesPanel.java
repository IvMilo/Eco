/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.*;
import javax.swing.*;
import javax.swing.border.*;
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
        setBackground(new Color(233, 255, 234)); // Suave verde claro

        // Título destacado
        JLabel lblTitulo = new JLabel("Historial de Decisiones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(34, 80, 34));
        lblTitulo.setBorder(new EmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Lista estilizada
        JList<String> lista = new JList<>(modelo);
        lista.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lista.setBackground(new Color(245, 255, 245));
        lista.setForeground(new Color(30, 60, 30));
        lista.setCellRenderer(new DecisionCellRenderer());
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(new CompoundBorder(
            new LineBorder(new Color(120, 180, 120), 2, true),
            new EmptyBorder(8, 8, 8, 8)
        ));
        add(scroll, BorderLayout.CENTER);

        // Icono ecológico (opcional, si tienes un recurso)
        // JLabel icono = new JLabel(new ImageIcon("src/ecocatch/recursos/leaf_icon.png"));
        // icono.setHorizontalAlignment(SwingConstants.CENTER);
        // add(icono, BorderLayout.WEST);

        actualizar();
    }

    public void actualizar() {
        modelo.clear();
        int n = 1;
        for (Decision d : pilaDecisiones.getDecisiones()) {
            modelo.addElement(
                "<html><b>" + n + ".</b> <span style='color:#3A7D3A'>" + d.getDescripcion() + "</span>"
                + "<br><span style='color:#1976D2'>" + d.getEfectoCorto() + "</span>"
                + "<br><span style='color:#757575;font-size:11px'>" + d.getEfectoLargo() + "</span></html>"
            );
            n++;
        }
    }

    // Custom cell renderer for HTML and eco badge
    static class DecisionCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int idx, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, idx, isSelected, cellHasFocus);
            label.setOpaque(true);
            label.setBackground(isSelected ? new Color(200, 255, 200) : new Color(245, 255, 245));
            label.setBorder(new EmptyBorder(8, 12, 8, 12));
            return label;
        }
    }
}

