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


public class LoginPanel extends JPanel {
    private final GestorUsuarios gestorUsuarios;
    private final JTextField txtUsuario;
    private final JPasswordField txtPassword;
    private final JButton btnLogin, btnRegistro;
    private final JLabel lblMensaje;

    public LoginPanel(GestorUsuarios gestorUsuarios, Runnable onLoginExitoso, Runnable onRegistro) {
        this.gestorUsuarios = gestorUsuarios;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitulo = new JLabel("EcoCatch - Iniciar Sesión");
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

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        btnLogin = new JButton("Iniciar Sesión");
        btnRegistro = new JButton("Registrarse");
        JPanel pnlBtns = new JPanel();
        pnlBtns.add(btnLogin); pnlBtns.add(btnRegistro);
        add(pnlBtns, gbc);

        gbc.gridy++;
        lblMensaje = new JLabel(" ");
        add(lblMensaje, gbc);

        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String pass = new String(txtPassword.getPassword());
            if (gestorUsuarios.login(usuario, pass)) {
                lblMensaje.setText("¡Bienvenido, " + usuario + "!");
                onLoginExitoso.run();
            } else {
                lblMensaje.setText("Credenciales incorrectas.");
            }
        });

        btnRegistro.addActionListener(e -> onRegistro.run());
    }

    public void limpiarCampos() {
        txtUsuario.setText(""); txtPassword.setText(""); lblMensaje.setText(" ");
    }
}
