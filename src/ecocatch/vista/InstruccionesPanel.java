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
public class InstruccionesPanel extends JPanel {
    public JButton btnVolver;

    public InstruccionesPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(232, 255, 245));

        JLabel lblTitulo = new JLabel("¿Cómo jugar EcoCatch?");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(34, 120, 74));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(24, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel();
        panelCentro.setOpaque(false);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

        JLabel iconos = new JLabel("🌿  🧴  🔋");
        iconos.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        iconos.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea txtInstrucciones = new JTextArea();
        txtInstrucciones.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txtInstrucciones.setLineWrap(true);
        txtInstrucciones.setWrapStyleWord(true);
        txtInstrucciones.setEditable(false);
        txtInstrucciones.setOpaque(false);
        txtInstrucciones.setForeground(new Color(24, 75, 50));
        txtInstrucciones.setText("""
                                 \u00a1Ayuda a limpiar el planeta atrapando los objetos contaminantes con la caneca recicladora!
                                 
                                 \u2022 Usa las teclas \u2190 (izquierda) y \u2192 (derecha) para mover la caneca.
                                 \u2022 Atrapa hojas (\ud83c\udf3f), pl\u00e1sticos (\ud83e\uddf4) y bater\u00edas (\ud83d\udd0b) que caen del cielo.
                                 \u2022 Gana puntos por cada objeto atrapado.
                                 \u2022 \u00a1Evita dejar caer los objetos!
                                 
                                 \u00a1Supera tu puntaje y desbloquea logros!""");
        txtInstrucciones.setBorder(BorderFactory.createEmptyBorder(18, 26, 18, 26));

        panelCentro.add(iconos);
        panelCentro.add(Box.createVerticalStrut(12));
        panelCentro.add(txtInstrucciones);

        add(panelCentro, BorderLayout.CENTER);

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