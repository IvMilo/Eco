/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 *
 * @author Milo
 */
public class Player {
    private int x;
    private final int y;
    private final int width = 60, height = 35;
    private final int panelWidth;

    public Player(int startX, int y, int panelWidth) {
        this.x = startX; this.y = y; this.panelWidth = panelWidth;
    }
    public void move(int dx) {
        x += dx;
        if (x < 0) x = 0;
        if (x + width > panelWidth) x = panelWidth - width;
    }
    public int getX() { return x; }
    public int getY() { return y; }

    public void draw(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cuerpo principal
        g2d.setColor(new Color(180, 220, 235));
        g2d.fillRoundRect(x + 6, y + 7, width - 12, height - 9, 14, 18);

        // Tapa superior
        g2d.setColor(new Color(36, 160, 234));
        g2d.fillRoundRect(x-2, y, width+4, 15, 20, 16);

        // Borde y detalles
        g2d.setColor(new Color(44, 90, 110));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(x + 6, y + 7, width - 12, height - 9, 14, 18);

        // Símbolo reciclaje simple (círculo y flecha)
        g2d.setColor(new Color(44, 90, 110, 200));
        g2d.drawArc(x + 24, y + 17, 14, 10, 30, 270);
        g2d.drawLine(x + 35, y + 18, x + 38, y + 14);
        g2d.drawLine(x + 34, y + 21, x + 38, y + 14);

        // Asas laterales
        g2d.setColor(new Color(120, 120, 120));
        g2d.drawLine(x + 8, y + 16, x + 8, y + height - 4);
        g2d.drawLine(x + width - 9, y + 16, x + width - 9, y + height - 4);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
