/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.*;
import javax.swing.*;
import java.awt.*;

/**
 * GameFrame principal con integración completa de usuarios, misiones, recursos, decisiones y resultados.
 * Modular, escalable y compatible con la jugabilidad original.
 */
public class GameFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Gestores y entidades principales
    private GestorUsuarios gestorUsuarios;
    private GestorMisiones gestorMisiones;
    private GestorRecursos gestorRecursos;
    private PilaDecisiones pilaDecisiones;
    private Usuario usuarioActual;

    // Paneles principales
    private LoginPanel loginPanel;
    private RegistroPanel registroPanel;
    private MisionesPanel misionesPanel;
    private RecursosPanel recursosPanel;
    private DecisionesPanel decisionesPanel;
    private GamePanel gamePanel;
    private ResultadoPanel resultadoPanel;

    public GameFrame() {
        setTitle("EcoCatch - Simulación Cambio Climático");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Inicialización de modelo
        gestorUsuarios = new GestorUsuarios();
        gestorMisiones = new GestorMisiones();
        gestorRecursos = new GestorRecursos();
        pilaDecisiones = new PilaDecisiones();

        // Layout principal
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Paneles de autenticación
        loginPanel = new LoginPanel(gestorUsuarios, this::mostrarMisiones, this::mostrarRegistro);
        registroPanel = new RegistroPanel(gestorUsuarios, this::mostrarLogin, this::mostrarLogin);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(registroPanel, "registro");

        add(mainPanel);
        mostrarLogin();
    }

    public void mostrarLogin() {
        cardLayout.show(mainPanel, "login");
        loginPanel.limpiarCampos();
    }

    public void mostrarRegistro() {
        cardLayout.show(mainPanel, "registro");
    }

    public void mostrarMisiones() {
        usuarioActual = gestorUsuarios.getUsuarioActual();
        misionesPanel = new MisionesPanel(gestorMisiones, usuarioActual, this::iniciarMision);
        mainPanel.add(misionesPanel, "misiones");
        cardLayout.show(mainPanel, "misiones");
    }

    /**
     * Inicia la misión seleccionada (crea panel de juego e integra recursos y decisiones).
     */
    public void iniciarMision() {
        int idMision = misionesPanel.getMisionSeleccionada();
        recursosPanel = new RecursosPanel(gestorRecursos);
        decisionesPanel = new DecisionesPanel(pilaDecisiones, recursosPanel::actualizar);

        // Panel del catch game con integración de modelos ampliados
        gamePanel = new GamePanel(this, gestorMisiones.getMision(idMision), gestorRecursos, pilaDecisiones, usuarioActual);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(recursosPanel, BorderLayout.NORTH);
        wrapper.add(gamePanel, BorderLayout.CENTER);
        wrapper.add(decisionesPanel, BorderLayout.SOUTH);

        mainPanel.add(wrapper, "juego");
        cardLayout.show(mainPanel, "juego");
    }

    /**
     * Muestra el panel de resultados tras terminar una partida/misión.
     */
    public void mostrarResultados(int score, Mision mision, boolean exitoMision,
                                  GestorRecursos gestorRecursos, PilaDecisiones pilaDecisiones, Usuario usuario) {
        resultadoPanel = new ResultadoPanel(
            score, mision, exitoMision, gestorRecursos, pilaDecisiones, usuario,
            this::mostrarMisiones, // acción "volver"
            () -> iniciarMisionDesdeResultado(mision.getId()) // acción "reintentar"
        );
        mainPanel.add(resultadoPanel, "resultado");
        cardLayout.show(mainPanel, "resultado");
    }

    /**
     * Permite reintentar una misión directamente desde el panel de resultados.
     */
    private void iniciarMisionDesdeResultado(int idMision) {
        // Puedes resetear recursos/decisiones si así lo requiere el diseño
        recursosPanel = new RecursosPanel(gestorRecursos);
        decisionesPanel = new DecisionesPanel(pilaDecisiones, recursosPanel::actualizar);
        gamePanel = new GamePanel(this, gestorMisiones.getMision(idMision), gestorRecursos, pilaDecisiones, usuarioActual);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(recursosPanel, BorderLayout.NORTH);
        wrapper.add(gamePanel, BorderLayout.CENTER);
        wrapper.add(decisionesPanel, BorderLayout.SOUTH);

        mainPanel.add(wrapper, "juego");
        cardLayout.show(mainPanel, "juego");
    }
}

