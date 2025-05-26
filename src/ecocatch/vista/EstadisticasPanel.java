/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Milo
 */
public class EstadisticasPanel extends JPanel {
    private JLabel lblTotalPartidas, lblPuntajeMax, lblPuntajePromedio, lblTotalRecolectados;
    private JButton btnVolver;

    public EstadisticasPanel() {
        setLayout(new BorderLayout(0, 20));
        setBackground(new Color(230, 250, 245));

        JLabel lblTitulo = new JLabel("Estadísticas de Juego", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(36, 120, 160));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setOpaque(false);

        lblTotalPartidas = new JLabel();
        lblPuntajeMax = new JLabel();
        lblPuntajePromedio = new JLabel();
        lblTotalRecolectados = new JLabel();

        Font f = new Font("Segoe UI", Font.PLAIN, 20);
        lblTotalPartidas.setFont(f);
        lblPuntajeMax.setFont(f);
        lblPuntajePromedio.setFont(f);
        lblTotalRecolectados.setFont(f);

        lblTotalPartidas.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPuntajeMax.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPuntajePromedio.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTotalRecolectados.setAlignmentX(Component.CENTER_ALIGNMENT);

        centro.add(lblTotalPartidas);
        centro.add(Box.createVerticalStrut(8));
        centro.add(lblPuntajeMax);
        centro.add(Box.createVerticalStrut(8));
        centro.add(lblPuntajePromedio);
        centro.add(Box.createVerticalStrut(8));
        centro.add(lblTotalRecolectados);
        add(centro, BorderLayout.CENTER);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnVolver.setBackground(new Color(34, 160, 114));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(8, 22, 8, 22));
        JPanel sur = new JPanel();
        sur.setOpaque(false);
        sur.add(btnVolver);
        add(sur, BorderLayout.SOUTH);

        cargarEstadisticas();
    }

    /**
     * Lee eco_reporte.csv y procesa la información usando solo nodos/listas propias.
     */
    private void cargarEstadisticas() {
        // Definir nodo y lista enlazada interna
        class NodoEstadistica {
            int puntaje;
            int totalRecolectados;
            NodoEstadistica siguiente;
            NodoEstadistica(int puntaje, int totalRecolectados) {
                this.puntaje = puntaje;
                this.totalRecolectados = totalRecolectados;
                this.siguiente = null;
            }
        }

        NodoEstadistica cabeza = null;
        NodoEstadistica ultimo = null;

        // Leer archivo y construir la lista enlazada
        try (BufferedReader br = new BufferedReader(new FileReader("eco_reporte.csv"))) {
            String linea = br.readLine(); // Leer cabecera
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                if (partes.length >= 4) {
                    int puntaje = Integer.parseInt(partes[1].trim());
                    int totalRecolectados = Integer.parseInt(partes[2].trim());
                    NodoEstadistica nodo = new NodoEstadistica(puntaje, totalRecolectados);
                    if (cabeza == null) {
                        cabeza = nodo;
                        ultimo = nodo;
                    } else {
                        ultimo.siguiente = nodo;
                        ultimo = nodo;
                    }
                }
            }
        } catch (IOException e) {
            lblTotalPartidas.setText("No hay partidas registradas.");
            lblPuntajeMax.setText("");
            lblPuntajePromedio.setText("");
            lblTotalRecolectados.setText("");
            return;
        } catch (NumberFormatException e) {
            lblTotalPartidas.setText("Error en datos de archivo.");
            lblPuntajeMax.setText("");
            lblPuntajePromedio.setText("");
            lblTotalRecolectados.setText("");
            return;
        }

        // Recorrer la lista y calcular resultados
        int totalPartidas = 0;
        int puntajeMax = Integer.MIN_VALUE;
        int sumaPuntajes = 0;
        int sumaRecolectados = 0;

        NodoEstadistica actual = cabeza;
        while (actual != null) {
            totalPartidas++;
            sumaPuntajes += actual.puntaje;
            sumaRecolectados += actual.totalRecolectados;
            if (actual.puntaje > puntajeMax) {
                puntajeMax = actual.puntaje;
            }
            actual = actual.siguiente;
        }

        if (totalPartidas == 0) {
            lblTotalPartidas.setText("No hay partidas registradas.");
            lblPuntajeMax.setText("");
            lblPuntajePromedio.setText("");
            lblTotalRecolectados.setText("");
        } else {
            double promedio = (double) sumaPuntajes / totalPartidas;
            lblTotalPartidas.setText("Total de partidas jugadas: " + totalPartidas);
            lblPuntajeMax.setText("Puntaje máximo: " + puntajeMax);
            lblPuntajePromedio.setText(String.format("Puntaje promedio: %.2f", promedio));
            lblTotalRecolectados.setText("Total de objetos recogidos: " + sumaRecolectados);
        }
    }

    // Permite al frame principal conectar el botón "Volver"
    public void setControlador(ActionListener l) {
        btnVolver.addActionListener(l);
    }
}