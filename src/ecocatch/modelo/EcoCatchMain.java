/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ecocatch.modelo;

import ecocatch.vista.GameFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Milo
 */

public class EcoCatchMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameFrame().setVisible(true);
        });
    }
}
