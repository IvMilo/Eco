/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author Milo
 */

public class Player {
    private int x, y;
    private int limiteDerecho; // Límite derecho del panel (para no salirse)
    private final int ancho = 100;
    private final int alto = 120;

    private static BufferedImage playerImg;

    static {
        try {
            playerImg = ImageIO.read(Player.class.getResource("/ecocatch/recursos/player.png"));
        } catch (IOException | IllegalArgumentException e) {
            playerImg = null;
        }
    }

    public Player(int x, int y, int limiteDerecho) {
        this.x = x;
        this.y = y;
        this.limiteDerecho = limiteDerecho;
    }

    public void setLimiteDerecho(int limiteDerecho) {
        this.limiteDerecho = limiteDerecho;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    /**
     * Mueve el jugador horizontalmente, respetando los límites.
     */
    public void move(int delta) {
        x += delta;
        if (x < 0) x = 0;
        if (x > limiteDerecho - ancho) x = limiteDerecho - ancho;
    }

    /**
     * Dibuja al jugador.
     */
    public void draw(Graphics2D g2) {
        if (playerImg != null) {
            g2.drawImage(playerImg, x, y, ancho, alto, null);
        } else {
            // Fallback: dibujar un rectángulo si no hay imagen
            g2.setColor(new Color(120, 150, 90));
            g2.fillRoundRect(x, y, ancho, alto, 18, 18);
            g2.setColor(new Color(60, 100, 70));
            g2.fillRect(x + 15, y + 10, 30, 20);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(x, y, ancho, alto, 18, 18);
        }
    }

    /**
     * Devuelve los límites para colisiones.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}