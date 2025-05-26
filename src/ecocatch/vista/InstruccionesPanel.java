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
        setBackground(new Color(225, 255, 225));

        JLabel lblTitulo = new JLabel("¿Cómo jugar EcoCatch?");
        lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(0, 102, 51));
        add(lblTitulo, BorderLayout.NORTH);

        JTextArea txtInstrucciones = new JTextArea();
        txtInstrucciones.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
        txtInstrucciones.setLineWrap(true);
        txtInstrucciones.setWrapStyleWord(true);
        txtInstrucciones.setEditable(false);
        txtInstrucciones.setOpaque(false);
        txtInstrucciones.setForeground(Color.BLACK);
        txtInstrucciones.setText("""
                                 \u00a1Ayuda a limpiar el planeta atrapando los objetos contaminantes con la caneca recicladora!
                                 
                                 \u2022 Usa las teclas \u2190 (izquierda) y \u2192 (derecha) para mover la caneca.
                                 \u2022 Atrapa hojas, pl\u00e1sticos y bater\u00edas que caen del cielo.
                                 \u2022 Cada objeto tiene un color:
                                    - \ud83d\udfe2 Hoja (org\u00e1nico)   - \ud83d\udd35 Pl\u00e1stico (inorg\u00e1nico)   - \ud83d\udfe3 Bater\u00eda (t\u00f3xico)
                                 \u2022 Gana puntos por cada objeto atrapado.
                                 \u2022 \u00a1Evita dejar caer los objetos!
                                 
                                 \u00a1Supera tu puntaje y desbloquea logros!""");
        txtInstrucciones.setPreferredSize(new Dimension(650, 400));
        JScrollPane scroll = new JScrollPane(txtInstrucciones);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setOpaque(false);
        panelCentro.add(scroll);
        add(panelCentro, BorderLayout.CENTER);

        btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        btnVolver.setBackground(new Color(200, 230, 255));
        JPanel panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);
    }

    public void setControlador(ActionListener listener) {
        btnVolver.addActionListener(listener);
    }
}
