/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Milo
 */
public class Player {
    private int x;
    private final int y;
    private final int width = 150, height = 150;
    private final int panelWidth;

    private static Image playerImg;

    static {
        try {
            playerImg = ImageIO.read(Player.class.getResource("/ecocatch/recursos/player.png"));
        } catch (IOException | IllegalArgumentException e) {
            playerImg = null;
        }
    }

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
        if (playerImg != null) {
            g2d.drawImage(playerImg, x, y, width, height, null);
        } else {
            g2d.setColor(java.awt.Color.RED);
            g2d.fillRect(x, y, width, height);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
