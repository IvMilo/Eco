/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import javax.swing.*;
import java.awt.*;
import ecocatch.modelo.MultiListaElementos;

public class GameFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menuPanel;
    private InstruccionesPanel instruccionesPanel;
    private EstadisticasPanel estadisticasPanel;
    private GamePanel gamePanel;
    private ResultadoPanel resultadoPanel;

    public GameFrame() {
        setTitle("EcoCatch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuPanel = new MenuPanel();
        instruccionesPanel = new InstruccionesPanel();
        estadisticasPanel = new EstadisticasPanel();
        gamePanel = new GamePanelPersonalizado(this);

        menuPanel.setControladores(e -> {
            if (e.getSource() == menuPanel.btnJugar)      mostrarJuego();
            else if (e.getSource() == menuPanel.btnInstrucciones) mostrarInstrucciones();
            else if (e.getSource() == menuPanel.btnEstadisticas)  mostrarEstadisticas();
            else if (e.getSource() == menuPanel.btnSalir) System.exit(0);
        });
        instruccionesPanel.setControlador(e -> mostrarMenu());
        estadisticasPanel.setControlador(e -> mostrarMenu());

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(instruccionesPanel, "instrucciones");
        mainPanel.add(estadisticasPanel, "estadisticas");
        mainPanel.add(gamePanel, "jugar");

        add(mainPanel);
        mostrarMenu();
    }

    public void mostrarMenu() {
        cardLayout.show(mainPanel, "menu");
    }

    public void mostrarInstrucciones() {
        cardLayout.show(mainPanel, "instrucciones");
    }

    public void mostrarEstadisticas() {
        cardLayout.show(mainPanel, "estadisticas");
    }

    public void mostrarJuego() {
        mainPanel.remove(gamePanel);
        gamePanel = new GamePanelPersonalizado(this);
        mainPanel.add(gamePanel, "jugar");
        cardLayout.show(mainPanel, "jugar");
        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
    }

    public void mostrarResultadoFinal(int puntaje, MultiListaElementos historial) {
        if (resultadoPanel != null) {
            mainPanel.remove(resultadoPanel);
        }
        resultadoPanel = new ResultadoPanel(puntaje, historial, this);

        mainPanel.add(resultadoPanel, "resultado");
        mainPanel.revalidate();
        mainPanel.repaint();
        cardLayout.show(mainPanel, "resultado");
    }

    private class GamePanelPersonalizado extends GamePanel {
        private final GameFrame frame;
        public GamePanelPersonalizado(GameFrame frame) {
            super(frame);
            this.frame = frame;
        }
        public void notificarFinDeJuego(int score, MultiListaElementos historial) {
            frame.mostrarResultadoFinal(score, historial);
        }
    }
}