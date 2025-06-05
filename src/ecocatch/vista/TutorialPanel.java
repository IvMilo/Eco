/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Milo
 */


public class TutorialPanel extends JPanel {
    private int currentStep = 0;
    private final List<JPanel> steps = new ArrayList<>();
    private final JButton btnPrev, btnNext, btnFinish;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final Runnable onFinishTutorial;

    public TutorialPanel(Runnable onFinishTutorial) {
        this.onFinishTutorial = onFinishTutorial;
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(223, 245, 223));
        setBorder(new EmptyBorder(32, 60, 32, 60));

        // Título
        JLabel lblTitulo = new JLabel("📖 Tutorial: ¿Cómo jugar EcoCatch?", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(46, 125, 50));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Crear los pasos del tutorial
        crearPasos();

        // Panel central con CardLayout para los pasos
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        for (int i = 0; i < steps.size(); i++) {
            cardPanel.add(steps.get(i), "step" + i);
        }
        add(cardPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);

        btnPrev = new JButton("Anterior");
        btnPrev.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnPrev.addActionListener(e -> mostrarPaso(currentStep - 1));

        btnNext = new JButton("Siguiente");
        btnNext.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnNext.addActionListener(e -> mostrarPaso(currentStep + 1));

        btnFinish = new JButton("Finalizar");
        btnFinish.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnFinish.setBackground(new Color(56, 142, 60));
        btnFinish.setForeground(Color.white);
        btnFinish.setFocusPainted(false);
        btnFinish.setBorder(new LineBorder(new Color(56, 142, 60), 2, true));
        btnFinish.addActionListener(e -> onFinishTutorial.run());

        panelBotones.add(btnPrev);
        panelBotones.add(btnNext);
        panelBotones.add(btnFinish);

        add(panelBotones, BorderLayout.SOUTH);

        mostrarPaso(0);
    }

    private void crearPasos() {
        // Paso 1: Introducción
        steps.add(crearStepPanel(
            "¡Bienvenido a EcoCatch!",
            "<html><div style='width:440px'>EcoCatch es un juego educativo donde aprenderás sobre el cuidado ambiental y la gestión de recursos. <br><br>¡Vamos a aprender cómo jugar!</div></html>",
            null
        ));

        // Paso 2: Selección de misión
        steps.add(crearStepPanel(
            "1. Selecciona una misión",
            "<html><div style='width:440px'>Cada misión tiene un objetivo ecológico diferente.<br>¡Escoge la que prefieras y prepárate para el reto!</div></html>",
            "/ecocatch/recursos/tutorial_mision.png" // Opcional: agrega una imagen de ejemplo
        ));

        // Paso 3: Controles del jugador
        steps.add(crearStepPanel(
            "2. Controla al jugador",
            "<html><div style='width:440px'>Usa las flechas <b>←</b> y <b>→</b> de tu teclado para moverte y recoger elementos que caen.<br>¡Atrapa los correctos para sumar puntos!</div></html>",
            "/ecocatch/recursos/tutorial_controles.png"
        ));

        // Paso 4: Tipos de elementos
        steps.add(crearStepPanel(
            "3. Tipos de elementos",
            "<html><div style='width:440px'>Recoge:<br>- <b style='color:#4caf50'>Orgánicos</b>: suman puntos.<br>- <b style='color:#2196f3'>Inorgánicos</b>: pueden tener efectos mixtos.<br>- <b style='color:#ff5722'>Tóxicos</b>: restan recursos.<br><br>¡Evítalos si puedes!</div></html>",
            "/ecocatch/recursos/tutorial_elementos.png"
        ));

        // Paso 5: Objetivo y decisiones
        steps.add(crearStepPanel(
            "4. Cumple el objetivo y toma decisiones",
            "<html><div style='width:440px'>Junta la cantidad indicada de cada elemento para completar la misión.<br>Durante la partida aparecerán decisiones. <b>¡Elige sabiamente!</b> Afectan tus recursos a corto y largo plazo.</div></html>",
            "/ecocatch/recursos/tutorial_objetivo.png"
        ));

        // Paso 6: Recursos y consejos
        steps.add(crearStepPanel(
            "5. Gestiona tus recursos",
            "<html><div style='width:440px'>Administra tu <b>dinero</b>, <b>energía</b> y <b>agua</b> para avanzar.<br><br><b>Consejos:</b><ul><li>Piensa antes de tomar decisiones.</li><li>Prioriza completar el objetivo de la misión.</li><li>Evita perder recursos para maximizar tu puntaje.</li></ul></div></html>",
            "/ecocatch/recursos/tutorial_recursos.png"
        ));

        // Paso 7: ¡A jugar!
        steps.add(crearStepPanel(
            "¡Listo para jugar!",
            "<html><div style='width:440px'>Ya sabes lo esencial.<br>Consulta tus <b>estadísticas</b> y <b>logros</b> al final de cada misión.<br><br>¡Diviértete y ayuda al planeta!</div></html>",
            null
        ));
    }

    private JPanel crearStepPanel(String titulo, String texto, String iconPath) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(12));

        if (iconPath != null) {
            java.net.URL imgURL = getClass().getResource(iconPath);
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                Image scaled = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                JLabel lblImg = new JLabel(new ImageIcon(scaled));
                lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(lblImg);
                panel.add(Box.createVerticalStrut(8));
            }
        }

        JLabel lblTexto = new JLabel(texto);
        lblTexto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTexto.setVerticalAlignment(SwingConstants.TOP);
        panel.add(lblTexto);

        return panel;
    }

    private void mostrarPaso(int idx) {
        if (idx < 0 || idx >= steps.size()) return;
        currentStep = idx;
        cardLayout.show(cardPanel, "step" + idx);
        btnPrev.setEnabled(idx > 0);
        btnNext.setEnabled(idx < steps.size() - 1);
        btnFinish.setVisible(idx == steps.size() - 1);
        btnNext.setVisible(idx < steps.size() - 1);
    }
}
