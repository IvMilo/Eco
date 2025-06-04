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

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final GameFrame parentFrame;
    private final int FPS = 60, PLAYER_SPEED = 20;
    private Player jugador;
    private final ListaElementos elementos = new ListaElementos();
    private int score = 0;
    private final Random rand = new Random();
    private final Timer timer;
    private final MultiListaElementos historial = new MultiListaElementos();

    // Nuevas integraciones:
    private final Mision mision;
    private final GestorRecursos gestorRecursos;
    private final PilaDecisiones pilaDecisiones;
    private final Usuario usuario;

    // Objetivos de la misión actual
    private int objetivoOrganicos = 0;
    private int objetivoInorganicos = 0;
    private int objetivoToxicos = 0;

    private boolean jugadorInicializado = false;

    public GamePanel(GameFrame parentFrame, Mision mision, GestorRecursos gestorRecursos, PilaDecisiones pilaDecisiones, Usuario usuario) {
        this.parentFrame = parentFrame;
        this.mision = mision;
        this.gestorRecursos = gestorRecursos;
        this.pilaDecisiones = pilaDecisiones;
        this.usuario = usuario;

        setPreferredSize(new Dimension(900, 700));
        setBackground(new Color(240, 255, 252));
        setFocusable(true);
        addKeyListener(this);

        // El jugador se inicializa en el primer paintComponent cuando las dimensiones ya son válidas
        jugador = null;
        initObjetivosMision();

        timer = new Timer(1000 / FPS, this);
        timer.start();
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    private void initObjetivosMision() {
        if (mision != null) {
            switch (mision.getId()) {
                case 1 -> objetivoOrganicos = 3;
                case 2 -> objetivoInorganicos = 3;
                case 3 -> objetivoToxicos = 2;
                // Agregar casos para futuras misiones
            }
        }
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
        int w = getWidth();
        int h = getHeight();

        // Inicializa el jugador con las dimensiones correctas la primera vez
        if (!jugadorInicializado || jugador == null) {
            int yJugador = h - 120;
            jugador = new Player(w / 2 - 30, yJugador, w); // X, Y, limite derecho
            jugadorInicializado = true;
        } else {
            jugador.setLimiteDerecho(w);
        }

        Graphics2D g2 = (Graphics2D) g.create();

        // Fondo
        GradientPaint gp = new GradientPaint(0, 0, new Color(175, 205, 240), 0, h, new Color(233, 255, 234));
        g2.setPaint(gp);
        g2.fillRect(0, 0, w, h);

        // Sombra jugador
        g2.setColor(new Color(80, 80, 80, 60));
        g2.fillOval(jugador.getX() + 10, jugador.getY() + 34, 40, 15);

        // Jugador
        jugador.draw(g2);

        // Elementos que caen
        NodoElemento temp = elementos.getCabeza();
        while (temp != null) {
            temp.elemento.draw(g2);
            temp = temp.siguiente;
        }

        // Marcadores UI
        g2.setColor(new Color(255, 255, 255, 160));
        g2.fillRoundRect(w - 190, 15, 170, 90, 18, 18);
        g2.setColor(new Color(44, 90, 110));
        g2.setFont(new Font("Segoe UI", Font.BOLD, 24));
        g2.drawString("Puntos: " + score, w - 170, 50);

        // Objetivos de misión (feedback)
        g2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        int y = 70;
        if (objetivoOrganicos > 0) {
            g2.drawString("Orgánicos: " + objetivoOrganicos, w - 170, y += 22);
        }
        if (objetivoInorganicos > 0) {
            g2.drawString("Inorgánicos: " + objetivoInorganicos, w - 170, y += 22);
        }
        if (objetivoToxicos > 0) {
            g2.drawString("Tóxicos: " + objetivoToxicos, w - 170, y += 22);
        }

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
            int x = rand.nextInt(Math.max(1, getWidth() - 32)); // dinámico
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
            if (fe.fueraDePantalla(getHeight())) {
                elementos.eliminar(temp, prev);
                temp = (prev == null) ? elementos.getCabeza() : prev.siguiente;
                continue;
            }
            if (fe.getBounds().intersects(jugador.getBounds())) {
                score += fe.getPuntos();
                historial.agregar(fe);

                // Objetivos de misión
                switch (fe.getTipo()) {
                    case "Orgánico"   -> { if (objetivoOrganicos > 0) objetivoOrganicos--; }
                    case "Inorgánico" -> { if (objetivoInorganicos > 0) objetivoInorganicos--; }
                    case "Tóxico"     -> { if (objetivoToxicos > 0) objetivoToxicos--; }
                }

                // Recursos: ejemplo simple (puedes ajustar la lógica)
                switch (fe.getTipo()) {
                    case "Orgánico"   -> gestorRecursos.modificarRecurso(Recurso.Tipo.AGUA, 5);
                    case "Inorgánico" -> gestorRecursos.modificarRecurso(Recurso.Tipo.ENERGIA, 10);
                    case "Tóxico"     -> gestorRecursos.modificarRecurso(Recurso.Tipo.DINERO, -8);
                }

                // Decisiones: ejemplo de decisión automática
                if (fe.getTipo().equals("Tóxico")) {
                    pilaDecisiones.agregarDecision(
                        new Decision("Atrapaste tóxico", "Baja dinero", "Impacto ambiental negativo")
                    );
                }

                elementos.eliminar(temp, prev);

                // ¿Se completó la misión?
                if (objetivoOrganicos <= 0 && objetivoInorganicos <= 0 && objetivoToxicos <= 0) {
                    terminarJuego(true);
                    return;
                }
            }
            prev = temp;
            temp = temp.siguiente;
        }
    }

    private void terminarJuego(boolean exitoMision) {
        timer.stop();
        ReportePartida reporte = new ReportePartida(score, historial);
        reporte.guardarHistorialTXT();
        reporte.exportarCSV();
        reporte.generarLogros();

        if (exitoMision && mision != null) {
            usuario.completarMision(mision.getId());
        }

        parentFrame.mostrarResultados(
            score, mision, exitoMision, gestorRecursos, pilaDecisiones, usuario
        );
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (jugador == null) return;
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            jugador.move(-PLAYER_SPEED);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            jugador.move(PLAYER_SPEED);
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}