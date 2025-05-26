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
public class MenuPanel extends JPanel {
    public JButton btnJugar, btnInstrucciones, btnEstadisticas, btnSalir;

    public MenuPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(232, 255, 245));

        // Título grande con sombra
        JLabel lblTitulo = new JLabel("EcoCatch");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 56));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(34, 120, 74));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central con los botones
        JPanel panelCentro = new JPanel();
        panelCentro.setOpaque(false);
        panelCentro.setLayout(new GridLayout(4, 1, 0, 32));

        btnJugar = createMenuButton("JUGAR");
        btnInstrucciones = createMenuButton("INSTRUCCIONES");
        btnEstadisticas = createMenuButton("ESTADÍSTICAS");
        btnSalir = createMenuButton("SALIR");

        panelCentro.add(btnJugar);
        panelCentro.add(btnInstrucciones);
        panelCentro.add(btnEstadisticas);
        panelCentro.add(btnSalir);

        JPanel panelContenedor = new JPanel(new GridBagLayout());
        panelContenedor.setOpaque(false);
        panelContenedor.add(panelCentro);

        add(panelContenedor, BorderLayout.CENTER);

        // Pie de página
        JLabel lblPie = new JLabel("Hecho con ♻️ por Milo");
        lblPie.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        lblPie.setHorizontalAlignment(SwingConstants.CENTER);
        lblPie.setForeground(new Color(59, 97, 77));
        lblPie.setBorder(BorderFactory.createEmptyBorder(0,0,18,0));
        add(lblPie, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 28));
        btn.setBackground(new Color(44, 181, 130));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 60, 12, 60));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(new Color(36, 160, 234)); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btn.setBackground(new Color(44, 181, 130)); }
        });
        return btn;
    }

    public void setControladores(ActionListener listener) {
        btnJugar.addActionListener(listener);
        btnInstrucciones.addActionListener(listener);
        btnEstadisticas.addActionListener(listener);
        btnSalir.addActionListener(listener);
    }
}
