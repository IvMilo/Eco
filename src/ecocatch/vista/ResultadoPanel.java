/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.MultiListaElementos;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Milo
 */


public class ResultadoPanel extends JPanel {
    private final int score;
    private final MultiListaElementos historial;

    public ResultadoPanel(int score, MultiListaElementos historial) {
        this.score = score;
        this.historial = historial;
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 28));
        g2d.drawString("RESULTADOS FINALES", 160, 80);

        g2d.setFont(new Font("SansSerif", Font.PLAIN, 20));

        int y = 150;

        int org = historial.contarPorTipo("Orgánico");
        int ino = historial.contarPorTipo("Inorgánico");
        int tox = historial.contarPorTipo("Tóxico");
        int total = org + ino + tox;

        g2d.drawString("Elementos recogidos: " + total, 150, y);
        y += 40;
        g2d.drawString("- Orgánicos: " + org + " (" + porcentaje(org, total) + "%)", 150, y);
        y += 30;
        g2d.drawString("- Inorgánicos: " + ino + " (" + porcentaje(ino, total) + "%)", 150, y);
        y += 30;
        g2d.drawString("- Tóxicos: " + tox + " (" + porcentaje(tox, total) + "%)", 150, y);
        y += 50;
        g2d.setFont(new Font("SansSerif", Font.BOLD, 22));
        g2d.drawString("Puntaje Total: " + score + " puntos", 150, y);
    }

    private int porcentaje(int cantidad, int total) {
        return total == 0 ? 0 : (int) (((double) cantidad / total) * 100);
    }
}
