/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author Milo
 */
public class EstadisticasPanel extends JPanel {
    public JButton btnVolver;

    public EstadisticasPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(232, 255, 245));

        JLabel lblTitulo = new JLabel("Estadísticas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(34, 120, 74));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(24, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Ejemplo de tabla de estadísticas
        String[] cols = {"Partida", "Puntaje", "Fecha"};
        String[][] data = {{"1", "15", "2025-05-25"}, {"2", "20", "2025-05-26"}, {"3", "12", "2025-05-26"}};
        JTable table = new JTable(data, cols);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(12, 32, 12, 32));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        add(scroll, BorderLayout.CENTER);

        btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnVolver.setBackground(new Color(44, 181, 130));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 36, 10, 36));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);
    }

    public void setControlador(ActionListener listener) {
        btnVolver.addActionListener(listener);
    }
}
