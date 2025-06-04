/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.GestorUsuarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Milo
 */


public class RegistroPanel extends JPanel {
    private final GestorUsuarios gestorUsuarios;
    private final JTextField txtUsuario;
    private final JPasswordField txtPassword, txtConfirmar;
    private final JButton btnRegistrar, btnVolver;
    private final JLabel lblMensaje;

    public RegistroPanel(GestorUsuarios gestorUsuarios, Runnable onRegistroExitoso, Runnable onVolver) {
        this.gestorUsuarios = gestorUsuarios;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitulo = new JLabel("EcoCatch - Registro");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.insets = new Insets(8,8,8,8);
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("Usuario:"), gbc);
        txtUsuario = new JTextField(15);
        gbc.gridx = 1;
        add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Contraseña:"), gbc);
        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Confirmar:"), gbc);
        txtConfirmar = new JPasswordField(15);
        gbc.gridx = 1;
        add(txtConfirmar, gbc);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        btnRegistrar = new JButton("Registrar");
        btnVolver = new JButton("Volver");
        JPanel pnlBtns = new JPanel();
        pnlBtns.add(btnRegistrar); pnlBtns.add(btnVolver);
        add(pnlBtns, gbc);

        gbc.gridy++;
        lblMensaje = new JLabel(" ");
        add(lblMensaje, gbc);

        btnRegistrar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String pass1 = new String(txtPassword.getPassword());
            String pass2 = new String(txtConfirmar.getPassword());
            if (usuario.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                lblMensaje.setText("Campos vacíos.");
            } else if (!pass1.equals(pass2)) {
                lblMensaje.setText("Las contraseñas no coinciden.");
            } else if (gestorUsuarios.registrarUsuario(usuario, pass1)) {
                lblMensaje.setText("¡Registro exitoso!");
                onRegistroExitoso.run();
            } else {
                lblMensaje.setText("Usuario ya existe.");
            }
        });

        btnVolver.addActionListener(e -> onVolver.run());
    }
}