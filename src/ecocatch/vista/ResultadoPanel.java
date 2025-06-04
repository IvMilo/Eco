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
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Título
        JLabel lblTitulo = new JLabel("Resultados de la Misión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central con info principal
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false);

        // Puntaje
        JLabel lblPuntaje = new JLabel("Puntaje: " + puntaje, SwingConstants.CENTER);
        lblPuntaje.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panelCentral.add(lblPuntaje);

        // Estado misión
        JLabel lblEstadoMision = new JLabel(
            "Misión: " + mision.getNombre() + 
            (exitoMision ? " (¡Completada!)" : " (No completada)")
        );
        lblEstadoMision.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelCentral.add(lblEstadoMision);

        // Objetivo misión
        JLabel lblObjetivo = new JLabel("Objetivo: " + mision.getObjetivo());
        lblObjetivo.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        panelCentral.add(lblObjetivo);

        // Recursos finales
        panelCentral.add(Box.createVerticalStrut(10));
        panelCentral.add(new JLabel("Recursos finales:", SwingConstants.CENTER));
        panelCentral.add(new JLabel("💰 Dinero: " + gestorRecursos.getCantidad(Recurso.Tipo.DINERO)));
        panelCentral.add(new JLabel("⚡ Energía: " + gestorRecursos.getCantidad(Recurso.Tipo.ENERGIA)));
        panelCentral.add(new JLabel("💧 Agua: " + gestorRecursos.getCantidad(Recurso.Tipo.AGUA)));

        // Decisiones tomadas
        panelCentral.add(Box.createVerticalStrut(10));
        List<Decision> decisiones = pilaDecisiones.getDecisiones().stream().collect(Collectors.toList());
        panelCentral.add(new JLabel("Decisiones tomadas:", SwingConstants.LEFT));
        if (decisiones.isEmpty()) {
            panelCentral.add(new JLabel("Ninguna."));
        } else {
            for (Decision d : decisiones) {
                panelCentral.add(new JLabel("• " + d.getDescripcion() + 
                    " (Corto: " + d.getEfectoCorto() + ", Largo: " + d.getEfectoLargo() + ")"));
            }
        }

        // Logros y progreso usuario
        panelCentral.add(Box.createVerticalStrut(10));
        JLabel lblUsuario = new JLabel("Usuario: " + usuario.getUsername(), SwingConstants.RIGHT);
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelCentral.add(lblUsuario);
        JLabel lblProgreso = new JLabel("Misiones completadas: " + usuario.getMisionesCompletadas().size(), SwingConstants.RIGHT);
        panelCentral.add(lblProgreso);

        add(panelCentral, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        JButton btnVolver = new JButton("Volver a misiones");
        JButton btnReintentar = new JButton("Reintentar misión");
        panelBotones.add(btnVolver);
        panelBotones.add(btnReintentar);
        add(panelBotones, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> onVolverAMisiones.run());
        btnReintentar.addActionListener(e -> onReintentarMision.run());
    }
}