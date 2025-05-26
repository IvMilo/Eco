/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Milo
 */

public class GamePanel extends JPanel
                       implements ActionListener, KeyListener {

    // --- constantes del juego ---
    private final int WIDTH  = 600;
    private final int HEIGHT = 800;
    private final int FPS    = 60;
    private final int PLAYER_SPEED = 8;

    // --- modelo ---
    private final Player jugador;
    private final ListaElementos elementos = new ListaElementos();
    private int score = 0;

    private final Random rand = new Random();
    private final Timer timer;
    private final int MAX_RECOGIDOS = 20;
    private int recogidos = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.white);
        setFocusable(true);
        addKeyListener(this);

        int yJugador = HEIGHT - 80;
        jugador = new Player(WIDTH / 2 - 30, yJugador, WIDTH);

        timer = new Timer(1000 / FPS, this);
        timer.start();
    }

    private void terminarJuego() {
        timer.stop();

        ReportePartida reporte = new ReportePartida(score, historial);
        reporte.guardarHistorialTXT();
        reporte.exportarCSV();
        reporte.generarLogros();

        JFrame topFrame = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);
        topFrame.getContentPane().removeAll();
        topFrame.add(new ResultadoPanel(score, historial));
        topFrame.revalidate();
        topFrame.repaint();
    }
  
    
    /* ---------------- lógica principal cada tick ---------------- */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        generarElementoAleatorio();
        actualizarElementos();
        detectarColisiones();
        repaint();                              // dispara paintComponent
    }

    /* ---------------- pintura de pantalla ---------------- */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // dibujar jugador
        jugador.draw(g2d);

        // dibujar elementos
        NodoElemento temp = elementos.getCabeza();
        while (temp != null) {
            temp.elemento.draw(g2d);
            temp = temp.siguiente;
        }

        // dibujar puntuación
        g2d.setColor(Color.black);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 22));
        g2d.drawString("Puntos: " + score, WIDTH - 150, 30);
    }

    /* ------------------------------------------------------------ */
    /* ------------------ generación de elementos ----------------- */
    private void generarElementoAleatorio() {
        // probabilidad ~ 3% por frame → ~1.8 elementos/seg
        if (rand.nextInt(100) > 97) {
            String[] tipos = {"Orgánico", "Inorgánico", "Tóxico"};
            int idx = rand.nextInt(tipos.length);
            String tipo = tipos[idx];

            int puntos = switch (tipo) {
                case "Orgánico"   -> 1;
                case "Inorgánico" -> 3;
                default /* Tóxico */ -> 5;
            };

            String nombre = switch (tipo) {
                case "Orgánico"   -> "Hoja";
                case "Inorgánico" -> "Plástico";
                default           -> "Batería";
            };

            int x = rand.nextInt(WIDTH - 32);
            int speed = 2 + rand.nextInt(4);   // 2-5 px por frame
            FallingElement fe = new FallingElement(nombre, tipo, puntos, x, -32, speed);
            elementos.agregar(fe);
        }
    }
    
    private final MultiListaElementos historial = new MultiListaElementos();

    /* -------------------- actualización -------------------- */
    private void actualizarElementos() {
        NodoElemento temp = elementos.getCabeza();
        while (temp != null) {
            temp.elemento.update();
            temp = temp.siguiente;
        }
    }

    /* --------------------- colisiones ---------------------- */
    private void detectarColisiones() {
        
        NodoElemento temp   = elementos.getCabeza();
        NodoElemento prev   = null;   // nodo anterior, para eliminar
        while (temp != null) {
            FallingElement fe = temp.elemento;
            // 1. ¿fuera de pantalla?
            if (fe.fueraDePantalla(HEIGHT)) {
                elementos.eliminar(temp, prev);
                temp = (prev == null) ? elementos.getCabeza() : prev.siguiente;
                continue;
            }

            // 2. ¿colisión con jugador?
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

            // avanzar punteros
            prev = temp;
            temp = temp.siguiente;
        }
    }
    
    /* ---------------------- teclado ------------------------ */
    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            jugador.move(-PLAYER_SPEED);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            jugador.move(PLAYER_SPEED);
    }
    @Override public void keyReleased(KeyEvent e) { }
    @Override public void keyTyped(KeyEvent e)    { }
}

