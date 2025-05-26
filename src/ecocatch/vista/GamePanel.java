/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 *
 * @author Milo
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Milo
 */

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final GameFrame parentFrame;
    private final int WIDTH = 900, HEIGHT = 700, FPS = 60, PLAYER_SPEED = 20;
    private final Player jugador;
    private final ListaElementos elementos = new ListaElementos();
    private int score = 0;
    private final Random rand = new Random();
    private final Timer timer;
    private final int MAX_RECOGIDOS = 5;
    private int recogidos = 0;
    private final MultiListaElementos historial = new MultiListaElementos();

    public void notificarFinDeJuego(int score, MultiListaElementos historial) {}

    public GamePanel(GameFrame parentFrame) {
        this.parentFrame = parentFrame;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(240, 255, 252));
        setFocusable(true);
        addKeyListener(this);

        int yJugador = HEIGHT - 120;
        jugador = new Player(WIDTH / 2 - 30, yJugador, WIDTH);

        timer = new Timer(1000 / FPS, this);
        timer.start();
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        generarElementoAleatorio();
        actualizarElementos();
        detectarColisiones();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        GradientPaint gp = new GradientPaint(0, 0, new Color(175, 205, 240), 0, HEIGHT, new Color(233, 255, 234));
        g2.setPaint(gp);
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        g2.setColor(new Color(80, 80, 80, 60));
        g2.fillOval(jugador.getX() + 10, jugador.getY() + 34, 40, 15);

        jugador.draw(g2);

        NodoElemento temp = elementos.getCabeza();
        while (temp != null) {
            temp.elemento.draw(g2);
            temp = temp.siguiente;
        }

        g2.setColor(new Color(255, 255, 255, 160));
        g2.fillRoundRect(WIDTH - 190, 15, 170, 50, 18, 18);
        g2.setColor(new Color(44, 90, 110));
        g2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        g2.drawString("Puntos: " + score, WIDTH - 170, 50);

        g2.dispose();
    }

    private void generarElementoAleatorio() {
        if (rand.nextInt(100) > 97) {
            String[] tipos = {"Orgánico", "Inorgánico", "Tóxico"};
            int idx = rand.nextInt(tipos.length);
            String tipo = tipos[idx];
            int puntos = switch (tipo) {
                case "Orgánico"   -> 1;
                case "Inorgánico" -> 3;
                default -> 5;
            };
            String nombre = switch (tipo) {
                case "Orgánico"   -> "Hoja";
                case "Inorgánico" -> "Plástico";
                default           -> "Batería";
            };
            int x = rand.nextInt(WIDTH - 32);
            int speed = 2 + rand.nextInt(4);
            FallingElement fe = new FallingElement(nombre, tipo, puntos, x, -32, speed);
            elementos.agregar(fe);
        }
    }

    private void actualizarElementos() {
        NodoElemento temp = elementos.getCabeza();
        while (temp != null) {
            temp.elemento.update();
            temp = temp.siguiente;
        }
    }

    private void detectarColisiones() {
        NodoElemento temp = elementos.getCabeza();
        NodoElemento prev = null;
        while (temp != null) {
            FallingElement fe = temp.elemento;
            if (fe.fueraDePantalla(HEIGHT)) {
                elementos.eliminar(temp, prev);
                temp = (prev == null) ? elementos.getCabeza() : prev.siguiente;
                continue;
            }
            if (fe.getBounds().intersects(jugador.getBounds())) {
                score += fe.getPuntos();
                historial.agregar(fe);
                recogidos++;
                elementos.eliminar(temp, prev);
                if (recogidos >= MAX_RECOGIDOS) {
                    terminarJuego();
                    return;
                }
            }
            prev = temp;
            temp = temp.siguiente;
        }
    }

    private void terminarJuego() {
        timer.stop();
        ReportePartida reporte = new ReportePartida(score, historial);
        reporte.guardarHistorialTXT();
        reporte.exportarCSV();
        reporte.generarLogros();

        notificarFinDeJuego(score, historial);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            jugador.move(-PLAYER_SPEED);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            jugador.move(PLAYER_SPEED);
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}