/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ecocatch.modelo.MultiListaElementos;
/**
 *
 * @author Milo
 */
public class GameFrame extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menuPanel;
    private InstruccionesPanel instruccionesPanel;
    private EstadisticasPanel estadisticasPanel;
    private GamePanel gamePanel;
    private ResultadoPanel resultadoPanel;

    // Guarda el historial de la última partida
    private int lastScore = 0;
    private MultiListaElementos lastHistorial = null;

    public GameFrame() {
        setTitle("EcoCatch");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Instanciar paneles
        menuPanel = new MenuPanel();
        instruccionesPanel = new InstruccionesPanel();
        estadisticasPanel = new EstadisticasPanel();
        gamePanel = new GamePanel(); // Nuevo juego al principio

        // Asignar listeners
        menuPanel.setControladores(this);
        instruccionesPanel.setControlador(this);
        estadisticasPanel.setControlador(this);

        // Añadir paneles al cardLayout
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(instruccionesPanel, "instrucciones");
        mainPanel.add(estadisticasPanel, "estadisticas");
        mainPanel.add(gamePanel, "jugar");
        // El resultadoPanel se agrega dinámicamente cuando sea necesario

        add(mainPanel);
        mostrarMenu();
    }

    // Métodos para cambiar de panel
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
        // Elimina el panel anterior de juego si existe (para reiniciar el estado)
        mainPanel.remove(gamePanel);
        gamePanel = new GamePanelPersonalizado(this); // Usamos un GamePanel que puede llamar a mostrarResultadoFinal
        mainPanel.add(gamePanel, "jugar");
        cardLayout.show(mainPanel, "jugar");
        SwingUtilities.invokeLater(() -> gamePanel.requestFocusInWindow());
    }

    public void mostrarResultadoFinal(int puntaje, MultiListaElementos historial) {
        // Guarda los datos para posible uso posterior (estadísticas, etc)
        lastScore = puntaje;
        lastHistorial = historial;

        if (resultadoPanel != null) {
            mainPanel.remove(resultadoPanel);
        }
        resultadoPanel = new ResultadoPanel(puntaje, historial);
        resultadoPanel.setControladores(this);
        mainPanel.add(resultadoPanel, "resultado");
        cardLayout.show(mainPanel, "resultado");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        // -- MENÚ PRINCIPAL
        if (src == menuPanel.btnJugar) {
            mostrarJuego();
        } else if (src == menuPanel.btnInstrucciones) {
            mostrarInstrucciones();
        } else if (src == menuPanel.btnEstadisticas) {
            mostrarEstadisticas();
        } else if (src == menuPanel.btnSalir) {
            System.exit(0);
        }
        // -- INSTRUCCIONES
        else if (src == instruccionesPanel.btnVolver) {
            mostrarMenu();
        }
        // -- ESTADÍSTICAS
        else if (src == estadisticasPanel.btnVolver) {
            mostrarMenu();
        }
        // -- RESULTADO FINAL (asegúrate de tener la ref al resultadoPanel actual)
        else if (resultadoPanel != null && src == resultadoPanel.btnMenu) {
            mostrarMenu();
        } else if (resultadoPanel != null && src == resultadoPanel.btnReintentar) {
            mostrarJuego();
        } else if (resultadoPanel != null && src == resultadoPanel.btnEstadisticas) {
            mostrarEstadisticas();
        } else if (resultadoPanel != null && src == resultadoPanel.btnSalir) {
            System.exit(0);
        }
    }

    // Usando una subclase para que GamePanel pueda notificar a GameFrame cuando termina el juego
    private class GamePanelPersonalizado extends GamePanel {
        private final GameFrame frame;
        public GamePanelPersonalizado(GameFrame frame) {
            super();
            this.frame = frame;
        }
        public void notificarFinDeJuego(int score, MultiListaElementos historial) {
            frame.mostrarResultadoFinal(score, historial);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame().setVisible(true));
    }
}
