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

public class FallingElement extends Elemento {
    private int x, y;
    private final int speed;
    private final int size = 32;          // píxeles del “sprite”

    public FallingElement(String nombre, String tipo, int puntos,
                          int x, int y, int speed) {
        super(nombre, tipo, puntos);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    /* Actualiza su posición vertical */
    public void update() {
        y += speed;
    }

    /* Dibuja un círculo simplificado; podrás cambiarlo por imagen */
    public void draw(Graphics2D g2d) {
        switch (getTipo()) {
            case "Orgánico"   -> g2d.setColor(new Color(0x4caf50)); // verde
            case "Inorgánico" -> g2d.setColor(new Color(0x2196f3)); // azul
            case "Tóxico"     -> g2d.setColor(new Color(0xff5722)); // naranja-rojo
        }
        g2d.fillOval(x, y, size, size);
    }

    public Rectangle getBounds() {            // para colisiones
        return new Rectangle(x, y, size, size);
    }

    public boolean fueraDePantalla(int alturaPanel) {
        return y > alturaPanel;
    }
}

