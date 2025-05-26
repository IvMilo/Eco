/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Milo
 */

public class FallingElement extends Elemento {
    private int x, y;
    private final int speed;
    private final int size = 64; // tamaño base
    private final int sizeInorganico = 120; // nuevo tamaño para Inorgánico

    // Sprites estáticos para no recargar en cada instancia
    private static Image organicoImg, inorganicoImg, toxicoImg;

    static {
        try {
            organicoImg = ImageIO.read(FallingElement.class.getResource("/ecocatch/recursos/Organico.png"));
            inorganicoImg = ImageIO.read(FallingElement.class.getResource("/ecocatch/recursos/Inorganico.png"));
            toxicoImg = ImageIO.read(FallingElement.class.getResource("/ecocatch/recursos/Toxico.png"));
        } catch (IOException | IllegalArgumentException e) {
            // Manejo simple de error: los sprites pueden ser null
            organicoImg = null;
            inorganicoImg = null;
            toxicoImg = null;
        }
    }

    public FallingElement(String nombre, String tipo, int puntos, int x, int y, int speed) {
        super(nombre, tipo, puntos);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    // Método utilitario para obtener el tamaño según tipo
    private int getSizeByTipo() {
        return getTipo().equals("Inorgánico") ? sizeInorganico : size;
    }

    // Actualiza su posición vertical
    public void update() {
        y += speed;
    }

    // Dibuja el sprite correspondiente o un óvalo si no hay imagen
    public void draw(Graphics2D g2d) {
        Image sprite = null;
        int drawSize = getSizeByTipo();
        switch (getTipo()) {
            case "Orgánico" -> sprite = organicoImg;
            case "Inorgánico" -> sprite = inorganicoImg;
            case "Tóxico" -> sprite = toxicoImg;
        }
        if (sprite != null) {
            g2d.drawImage(sprite, x, y, drawSize, drawSize, null);
        } else {
            switch (getTipo()) {
                case "Orgánico" -> g2d.setColor(new java.awt.Color(0x4caf50));
                case "Inorgánico" -> g2d.setColor(new java.awt.Color(0x2196f3));
                case "Tóxico" -> g2d.setColor(new java.awt.Color(0xff5722));
            }
            g2d.fillOval(x, y, drawSize, drawSize);
        }
    }

    public Rectangle getBounds() {
        int drawSize = getSizeByTipo();
        return new Rectangle(x, y, drawSize, drawSize);
    }

    public boolean fueraDePantalla(int alturaPanel) {
        return y > alturaPanel;
    }
}

