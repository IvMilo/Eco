/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Milo
 */
public class GameFrame extends JFrame {

    public GameFrame() {
        setTitle("EcoCatch - ¡Atrapa y cuida tu planeta!");
        setResizable(false);
        add(new GamePanel());
        pack();                       // ajusta al panel
        setLocationRelativeTo(null);  // centra
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
