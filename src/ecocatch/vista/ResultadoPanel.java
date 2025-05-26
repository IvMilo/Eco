/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;


import ecocatch.modelo.FallingElement;
import ecocatch.modelo.MultiListaElementos;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Milo
 */

public class ResultadoPanel extends JPanel {
    public final JButton btnMenu;
    public final JButton btnReintentar;
    public final JButton btnEstadisticas;
    public final JButton btnSalir;

    public ResultadoPanel(int puntaje, MultiListaElementos historial, GameFrame frame) {
        setLayout(new BorderLayout(0, 10));
        setBackground(new Color(232, 255, 245));

        JLabel lblTitulo = new JLabel("¡Juego Terminado!", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(36, 160, 90));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(32, 0, 8, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setOpaque(false);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        JLabel lblPuntaje = new JLabel("Tu puntaje: " + puntaje, SwingConstants.CENTER);
        lblPuntaje.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblPuntaje.setForeground(new Color(34, 85, 119));
        lblPuntaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(lblPuntaje);
        centro.add(Box.createVerticalStrut(12));

        JTextArea areaLogros = new JTextArea(calcularLogros(puntaje, historial));
        areaLogros.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        areaLogros.setForeground(new Color(44, 115, 54));
        areaLogros.setOpaque(false);
        areaLogros.setEditable(false);
        areaLogros.setAlignmentX(Component.CENTER_ALIGNMENT);
        areaLogros.setBorder(BorderFactory.createTitledBorder("Logros desbloqueados"));
        centro.add(areaLogros);
        centro.add(Box.createVerticalStrut(8));

        JTable tabla = new JTable(resumenPorTipo(historial), new String[] {"Tipo", "Cantidad"});
        tabla.setEnabled(false);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(340, 90));
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Resumen de objetos recogidos"));
        centro.add(scrollTabla);

        add(centro, BorderLayout.CENTER);

        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER, 24, 18));
        sur.setOpaque(false);

        btnMenu = crearBoton("Menú Principal", new Color(44, 181, 130));
        btnReintentar = crearBoton("Jugar de Nuevo", new Color(36, 160, 234));
        btnEstadisticas = crearBoton("Ver Estadísticas", new Color(120, 160, 90));
        btnSalir = crearBoton("Salir", new Color(200, 60, 90));

        // Los listeners llaman directamente a métodos de GameFrame
        btnMenu.addActionListener(e -> frame.mostrarMenu());
        btnReintentar.addActionListener(e -> frame.mostrarJuego());
        btnEstadisticas.addActionListener(e -> frame.mostrarEstadisticas());
        btnSalir.addActionListener(e -> System.exit(0));

        sur.add(btnMenu);
        sur.add(btnReintentar);
        sur.add(btnEstadisticas);
        sur.add(btnSalir);

        add(sur, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 22, 8, 22));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { btn.setBackground(color.darker()); }
            public void mouseExited(java.awt.event.MouseEvent evt) { btn.setBackground(color); }
        });
        return btn;
    }

    private String calcularLogros(int puntaje, MultiListaElementos historial) {
        int total = 0, organicos = 0, inorganicos = 0, toxicos = 0;
        if (historial != null) {
            for (FallingElement el : historial) {
                total++;
                String tipo = el.getTipo();
                if ("Orgánico".equalsIgnoreCase(tipo)) organicos++;
                else if ("Inorgánico".equalsIgnoreCase(tipo)) inorganicos++;
                else if ("Tóxico".equalsIgnoreCase(tipo)) toxicos++;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (total >= 20) sb.append("• ¡Recolector Master! (20+ objetos)\n");
        if (organicos >= 10) sb.append("• Guardián Verde (10+ orgánicos)\n");
        if (toxicos >= 5) sb.append("• Manejo Tóxico (5+ tóxicos)\n");
        if (puntaje >= 50) sb.append("• ¡Puntaje épico! (50+ puntos)\n");
        if (sb.length() == 0) sb.append("Aún sin logros especiales. ¡Sigue intentando!");
        return sb.toString();
    }

    private String[][] resumenPorTipo(MultiListaElementos historial) {
        Map<String, Integer> mapa = new HashMap<>();
        if (historial != null) {
            for (FallingElement el : historial) {
                String tipo = el.getTipo();
                mapa.put(tipo, mapa.getOrDefault(tipo, 0) + 1);
            }
        }
        String[] tipos = {"Orgánico", "Inorgánico", "Tóxico"};
        String[][] data = new String[tipos.length][2];
        for (int i = 0; i < tipos.length; i++) {
            data[i][0] = tipos[i];
            data[i][1] = String.valueOf(mapa.getOrDefault(tipos[i], 0));
        }
        return data;
    }
}