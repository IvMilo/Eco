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
        setLayout(new GridBagLayout());
        setBackground(new Color(180, 230, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);

        JLabel lblTitulo = new JLabel("🌱 EcoCatch 🌱");
        lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 48));
        lblTitulo.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblTitulo, gbc);

        btnJugar = crearBoton("Jugar");
        gbc.gridy = 1;
        add(btnJugar, gbc);

        btnInstrucciones = crearBoton("Instrucciones");
        gbc.gridy = 2;
        add(btnInstrucciones, gbc);

        btnEstadisticas = crearBoton("Estadísticas");
        gbc.gridy = 3;
        add(btnEstadisticas, gbc);

        btnSalir = crearBoton("Salir");
        gbc.gridy = 4;
        add(btnSalir, gbc);
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        btn.setBackground(new Color(255, 255, 200));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(300, 60));
        return btn;
    }

    public void setControladores(ActionListener listener) {
        btnJugar.addActionListener(listener);
        btnInstrucciones.addActionListener(listener);
        btnEstadisticas.addActionListener(listener);
        btnSalir.addActionListener(listener);
    }
}
