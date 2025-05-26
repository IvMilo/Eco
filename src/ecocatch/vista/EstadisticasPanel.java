/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Milo
 */
public class EstadisticasPanel extends JPanel {
    public EstadisticasPanel() {
        setBackground(new Color(255, 255, 240));
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Estadísticas próximamente");
        lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbl, BorderLayout.CENTER);
    }
}
