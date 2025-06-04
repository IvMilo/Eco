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

public class ResultadoPanel extends JPanel {
    public ResultadoPanel(
            int puntaje,
            Mision mision,
            boolean exitoMision,
            GestorRecursos gestorRecursos,
            PilaDecisiones pilaDecisiones,
            Usuario usuario,
            Runnable onVolverAMisiones,
            Runnable onReintentarMision
    ) {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(223, 245, 223));
        setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // Título con icono
        JLabel lblTitulo = new JLabel(
            (exitoMision ? "🎉 " : "🌱 ") + "Resultados de la Misión", SwingConstants.CENTER
        );
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitulo.setForeground(exitoMision ? new Color(27, 94, 32) : new Color(30, 136, 229));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central con información principal y borde
        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(200, 230, 201),
                                                     0, getHeight(), new Color(178, 235, 242));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);
            }
        };
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(new CompoundBorder(
            new LineBorder(new Color(56, 142, 60), 2, true),
            new EmptyBorder(20, 40, 20, 40)
        ));

        // Puntaje grande y destacado
        JLabel lblPuntaje = new JLabel("🏆 Puntaje: " + puntaje, SwingConstants.CENTER);
        lblPuntaje.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblPuntaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblPuntaje);

        // Estado misión
        JLabel lblEstadoMision = new JLabel(
                "<html>Misión: <b>" + mision.getNombre() +
                        "</b> " + (exitoMision ? "<span style='color:#388e3c;'>(¡Completada!)</span>" : "<span style='color:#d32f2f;'>(No completada)</span>") + "</html>"
        );
        lblEstadoMision.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblEstadoMision.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblEstadoMision);

        // Objetivo misión (con HTML y ancho máximo)
        JLabel lblObjetivo = new JLabel("<html><div style='width:340px;'>🎯 <b>Objetivo:</b> " + mision.getObjetivo() + "</div></html>");
        lblObjetivo.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        lblObjetivo.setBorder(new EmptyBorder(6,0,6,0));
        lblObjetivo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblObjetivo.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentral.add(lblObjetivo);

        // Recursos finales
        panelCentral.add(Box.createVerticalStrut(10));
        JLabel lblRecursos = new JLabel("Recursos finales:", SwingConstants.CENTER);
        lblRecursos.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblRecursos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblRecursos);

        JPanel recursosBox = new JPanel(new GridLayout(1, 3, 18, 0));
        recursosBox.setOpaque(false);
        JLabel lblDinero = new JLabel("💰 Dinero: " + gestorRecursos.getCantidad(Recurso.Tipo.DINERO), SwingConstants.CENTER);
        lblDinero.setFont(new Font("Segoe UI", Font.BOLD, 15));
        JLabel lblEnergia = new JLabel("⚡ Energía: " + gestorRecursos.getCantidad(Recurso.Tipo.ENERGIA), SwingConstants.CENTER);
        lblEnergia.setFont(new Font("Segoe UI", Font.BOLD, 15));
        JLabel lblAgua = new JLabel("💧 Agua: " + gestorRecursos.getCantidad(Recurso.Tipo.AGUA), SwingConstants.CENTER);
        lblAgua.setFont(new Font("Segoe UI", Font.BOLD, 15));
        recursosBox.add(lblDinero);
        recursosBox.add(lblEnergia);
        recursosBox.add(lblAgua);
        panelCentral.add(recursosBox);

        // Decisiones tomadas
        panelCentral.add(Box.createVerticalStrut(12));
        JLabel lblDecisiones = new JLabel("Decisiones tomadas:", SwingConstants.LEFT);
        lblDecisiones.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDecisiones.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblDecisiones);

        List<Decision> decisiones = pilaDecisiones.getDecisiones().stream().collect(Collectors.toList());
        if (decisiones.isEmpty()) {
            JLabel ninguna = new JLabel("Ninguna.");
            ninguna.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            ninguna.setForeground(new Color(120, 120, 120));
            ninguna.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentral.add(ninguna);
        } else {
            JPanel panelLista = new JPanel();
            panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
            panelLista.setOpaque(false);
            for (Decision d : decisiones) {
                JLabel entry = new JLabel("<html><div style='width:340px'>• <b>" + d.getDescripcion() + "</b> <span style='color:#388e3c'>(Corto: " +
                        d.getEfectoCorto() + "</span>, <span style='color:#0288d1'>Largo: " +
                        d.getEfectoLargo() + "</span>)</div></html>");
                entry.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                entry.setAlignmentX(Component.CENTER_ALIGNMENT);
                panelLista.add(entry);
            }
            panelCentral.add(panelLista);
        }

        // Logros y progreso usuario
        panelCentral.add(Box.createVerticalStrut(14));
        JLabel lblUsuario = new JLabel("👤 Usuario: " + usuario.getUsername(), SwingConstants.RIGHT);
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblUsuario);
        JLabel lblProgreso = new JLabel("🌟 Misiones completadas: " + usuario.getMisionesCompletadas().size(), SwingConstants.RIGHT);
        lblProgreso.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblProgreso.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblProgreso);

        add(panelCentral, BorderLayout.CENTER);

        // Botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        panelBotones.setOpaque(false);

        JButton btnVolver = new JButton("⬅ Volver a misiones");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnVolver.setBackground(new Color(30, 136, 229));
        btnVolver.setForeground(Color.white);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(new LineBorder(new Color(30, 136, 229), 2, true));

        JButton btnReintentar = new JButton("🔁 Reintentar misión");
        btnReintentar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnReintentar.setBackground(new Color(56, 142, 60));
        btnReintentar.setForeground(Color.white);
        btnReintentar.setFocusPainted(false);
        btnReintentar.setBorder(new LineBorder(new Color(56, 142, 60), 2, true));

        panelBotones.add(btnVolver);
        panelBotones.add(btnReintentar);

        add(panelBotones, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> onVolverAMisiones.run());
        btnReintentar.addActionListener(e -> onReintentarMision.run());
    }
}