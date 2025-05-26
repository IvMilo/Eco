/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private GamePanel gamePanel; // Ya existente

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
        gamePanel = new GamePanel(); // Asume que ya está implementado

        // Asignar listeners
        menuPanel.setControladores(this);
        instruccionesPanel.setControlador(this);

        // Añadir paneles al cardLayout
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(instruccionesPanel, "instrucciones");
        mainPanel.add(estadisticasPanel, "estadisticas");
        mainPanel.add(gamePanel, "jugar");

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
        cardLayout.show(mainPanel, "jugar");
        // Si necesitas reiniciar el juego, puedes agregar lógica aquí
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == menuPanel.btnJugar) {
            mostrarJuego();
        } else if (src == menuPanel.btnInstrucciones) {
            mostrarInstrucciones();
        } else if (src == menuPanel.btnEstadisticas) {
            mostrarEstadisticas();
        } else if (src == menuPanel.btnSalir) {
            System.exit(0);
        } else if (src == instruccionesPanel.btnVolver) {
            mostrarMenu();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame().setVisible(true));
    }
}
