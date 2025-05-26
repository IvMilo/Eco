/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Milo
 */
public class Player {
    private int x;
    private final int y;          // fijo en la parte baja
    private final int width  = 60;
    private final int height = 35;
    private final int panelWidth;

    public Player(int startX, int y, int panelWidth) {
        this.x = startX;
        this.y = y;
        this.panelWidth = panelWidth;
    }

    public void move(int dx) {
        x += dx;
        if (x < 0) x = 0;
        if (x + width > panelWidth) x = panelWidth - width;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(new Color(0x795548));      // marrón para la caneca
        g2d.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
