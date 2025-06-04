/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.Decision;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Milo
 */


public class EcoDecisionDialog {
    public static int showDecisionDialog(Component parent, String titulo, Decision[] opciones) {
        String[] labels = new String[opciones.length];
        for (int i = 0; i < opciones.length; i++)
            labels[i] = "<html><b>" + opciones[i].getDescripcion() + "</b><br><i>" + opciones[i].getEfectoCorto() + "</i></html>";

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(233, 255, 234));
        JLabel ecoLabel = new JLabel("Decide con conciencia ecológica:", SwingConstants.CENTER);
        ecoLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
        ecoLabel.setForeground(new Color(20, 120, 20));
        ecoLabel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        panel.add(ecoLabel, BorderLayout.NORTH);

        int choice = JOptionPane.showOptionDialog(
            parent,
            panel,
            titulo,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            labels,
            labels[0]
        );
        return choice;
    }
}
