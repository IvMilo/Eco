/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;


import javax.swing.*;
import java.awt.*;
import ecocatch.modelo.MultiListaElementos;
import java.awt.event.ActionListener;

/**
 *
 * @author Milo
 */

public class ResultadoPanel extends JPanel {
    public static final String CMD_MENU = "MENU";
    public static final String CMD_REINTENTAR = "REINTENTAR";
    public static final String CMD_ESTADISTICAS = "ESTADISTICAS";
    public static final String CMD_SALIR = "SALIR";

    public final JButton btnMenu;
    public final JButton btnReintentar;
    public final JButton btnEstadisticas;
    public final JButton btnSalir;

    public ResultadoPanel(int puntaje, MultiListaElementos historial) {
        setLayout(new BorderLayout());
        setBackground(new Color(232, 255, 245));

        // Título grande
        JLabel lblTitulo = new JLabel("¡Juego Terminado!");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 46));
        lblTitulo.setForeground(new Color(36, 160, 90));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(32, 0, 12, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central con puntaje y logros
        JPanel panelCentro = new JPanel();
        panelCentro.setOpaque(false);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

        // Puntaje destacado
        JLabel lblPuntaje = new JLabel("Tu puntaje: " + puntaje);
        lblPuntaje.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblPuntaje.setForeground(new Color(44, 90, 110));
        lblPuntaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(lblPuntaje);

        panelCentro.add(Box.createVerticalStrut(18));

        // Logros (demo)
        JLabel lblLogros = new JLabel("🏅 Logros desbloqueados:");
        lblLogros.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblLogros.setForeground(new Color(34, 120, 74));
        lblLogros.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(lblLogros);

        // Aquí puedes generar dinámicamente los logros según historial
        JTextArea areaLogros = new JTextArea();
        areaLogros.setText(
               "- ¡Sigue mejorando!"
        );
        areaLogros.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        areaLogros.setOpaque(false);
        areaLogros.setEditable(false);
        areaLogros.setAlignmentX(Component.CENTER_ALIGNMENT);
        areaLogros.setBorder(BorderFactory.createEmptyBorder(6, 32, 6, 32));
        panelCentro.add(areaLogros);

        panelCentro.add(Box.createVerticalStrut(18));

        // Tabla resumen (puedes personalizarla)
        String[] cols = {"Tipo", "Cantidad"};
        String[][] data = {{"Orgánico", "7"}, {"Inorgánico", "8"}, {"Tóxico", "5"}};
        JTable tabla = new JTable(data, cols);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tabla.setEnabled(false);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setPreferredSize(new Dimension(300, 110));
        scrollTabla.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        panelCentro.add(scrollTabla);

        add(panelCentro, BorderLayout.CENTER);

        // Botones estilizados y funcionales
        JPanel panelSur = new JPanel();
        panelSur.setOpaque(false);
        panelSur.setLayout(new FlowLayout(FlowLayout.CENTER, 28, 24));

        btnMenu = createButton("Menú Principal", new Color(44, 181, 130), CMD_MENU);
        btnReintentar = createButton("Jugar de Nuevo", new Color(36, 160, 234), CMD_REINTENTAR);
        btnEstadisticas = createButton("Ver Estadísticas", new Color(120, 160, 90), CMD_ESTADISTICAS);
        btnSalir = createButton("Salir", new Color(200, 60, 90), CMD_SALIR);

        panelSur.add(btnMenu);
        panelSur.add(btnReintentar);
        panelSur.add(btnEstadisticas);
        panelSur.add(btnSalir);

        add(panelSur, BorderLayout.SOUTH);
    }

    private JButton createButton(String text, Color bg, String actionCommand) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 34, 10, 34));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setActionCommand(actionCommand);
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });
        return btn;
    }

    /**
     * Registra un único ActionListener para todos los botones.
     * Usa getActionCommand() en el listener para distinguir la acción.
     */
    public void setControladores(ActionListener listener) {
        btnMenu.addActionListener(listener);
        btnReintentar.addActionListener(listener);
        btnEstadisticas.addActionListener(listener);
        btnSalir.addActionListener(listener);
    }
}