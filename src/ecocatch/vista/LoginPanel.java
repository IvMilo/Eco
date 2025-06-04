/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.vista;

import ecocatch.modelo.GestorUsuarios;
import javax.swing.*;
import javax.swing.border.*;
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
        setBackground(new Color(223, 245, 223)); // verde pálido

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(8,10,8,10);

        // Fondo con borde y sombra
        JPanel fondo = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(200, 230, 201),
                                                     0, getHeight(), new Color(178, 235, 242));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
            }
        };
        fondo.setOpaque(false);
        fondo.setBorder(new CompoundBorder(
                new LineBorder(new Color(56, 142, 60), 3, true),
                new EmptyBorder(28, 28, 28, 28)
        ));

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(12,14,12,14);
        gbc2.gridx = 0; gbc2.gridy = 0; gbc2.gridwidth = 2;

        // Título con icono ecológico
        JLabel lblTitulo = new JLabel("EcoCatch");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(46, 125, 50));
        lblTitulo.setIcon(new ImageIcon(getClass().getResource("/ecocatch/recursos/leaf_icon.png")));
        lblTitulo.setIconTextGap(16);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.add(lblTitulo, gbc2);

        gbc2.gridy++; gbc2.gridwidth = 2;
        JLabel lblSub = new JLabel("Iniciar Sesión");
        lblSub.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblSub.setForeground(new Color(27, 94, 32));
        lblSub.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.add(lblSub, gbc2);

        // Usuario
        gbc2.gridy++; gbc2.gridwidth = 1;
        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        fondo.add(lblUser, gbc2);
        gbc2.gridx = 1;
        txtUsuario = new JTextField(16);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtUsuario.setBorder(new LineBorder(new Color(144, 202, 249), 2, true));
        fondo.add(txtUsuario, gbc2);

        // Contraseña
        gbc2.gridx = 0; gbc2.gridy++;
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        fondo.add(lblPass, gbc2);
        gbc2.gridx = 1;
        txtPassword = new JPasswordField(16);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtPassword.setBorder(new LineBorder(new Color(178, 223, 219), 2, true));
        fondo.add(txtPassword, gbc2);

        // Botones
        gbc2.gridx = 0; gbc2.gridy++; gbc2.gridwidth = 2;
        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 2));
        pnlBtns.setOpaque(false);
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBackground(new Color(56, 142, 60));
        btnLogin.setForeground(Color.white);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogin.setFocusPainted(false);

        btnRegistro = new JButton("Registrarse");
        btnRegistro.setBackground(new Color(30, 136, 229));
        btnRegistro.setForeground(Color.white);
        btnRegistro.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRegistro.setFocusPainted(false);

        pnlBtns.add(btnLogin);
        pnlBtns.add(btnRegistro);
        fondo.add(pnlBtns, gbc2);

        // Mensaje
        gbc2.gridy++; gbc2.gridwidth = 2;
        lblMensaje = new JLabel(" ", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblMensaje.setForeground(new Color(211, 47, 47));
        fondo.add(lblMensaje, gbc2);

        // Centrado en el panel principal
        add(fondo, gbc);

        // Lógica de botones
        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String pass = new String(txtPassword.getPassword());
            if (gestorUsuarios.login(usuario, pass)) {
                lblMensaje.setForeground(new Color(56, 142, 60));
                lblMensaje.setText("¡Bienvenido, " + usuario + "!");
                onLoginExitoso.run();
            } else {
                lblMensaje.setForeground(new Color(211, 47, 47));
                lblMensaje.setText("Credenciales incorrectas.");
            }
        });

        btnRegistro.addActionListener(e -> onRegistro.run());
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
        lblMensaje.setText(" ");
        lblMensaje.setForeground(new Color(211, 47, 47));
    }
}