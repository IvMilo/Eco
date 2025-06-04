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


public class RegistroPanel extends JPanel {
    private final GestorUsuarios gestorUsuarios;
    private final JTextField txtUsuario;
    private final JPasswordField txtPassword, txtPassword2;
    private final JButton btnRegistrar, btnVolver;
    private final JLabel lblMensaje;

    public RegistroPanel(GestorUsuarios gestorUsuarios, Runnable onRegistroExitoso, Runnable onVolver) {
        this.gestorUsuarios = gestorUsuarios;

        setLayout(new GridBagLayout());
        setBackground(new Color(223, 245, 223)); // Verde pálido

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
        try {
            lblTitulo.setIcon(new ImageIcon(getClass().getResource("/ecocatch/recursos/Logo.png")));
            lblTitulo.setIconTextGap(16);
        } catch (Exception e) {
            // Si no hay icono, solo texto
        }
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.add(lblTitulo, gbc2);

        gbc2.gridy++; gbc2.gridwidth = 2;
        JLabel lblSub = new JLabel("Registrarse");
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

        // Repetir contraseña
        gbc2.gridx = 0; gbc2.gridy++;
        JLabel lblPass2 = new JLabel("Repetir contraseña:");
        lblPass2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        fondo.add(lblPass2, gbc2);
        gbc2.gridx = 1;
        txtPassword2 = new JPasswordField(16);
        txtPassword2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtPassword2.setBorder(new LineBorder(new Color(178, 223, 219), 2, true));
        fondo.add(txtPassword2, gbc2);

        // Botones
        gbc2.gridx = 0; gbc2.gridy++; gbc2.gridwidth = 2;
        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 18, 2));
        pnlBtns.setOpaque(false);
        btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBackground(new Color(56, 142, 60));
        btnRegistrar.setForeground(Color.white);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRegistrar.setFocusPainted(false);

        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(30, 136, 229));
        btnVolver.setForeground(Color.white);
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnVolver.setFocusPainted(false);

        pnlBtns.add(btnRegistrar);
        pnlBtns.add(btnVolver);
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
        btnRegistrar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String pass1 = new String(txtPassword.getPassword());
            String pass2 = new String(txtPassword2.getPassword());

            if (usuario.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                lblMensaje.setForeground(new Color(211, 47, 47));
                lblMensaje.setText("Por favor, completa todos los campos.");
                return;
            }
            if (!pass1.equals(pass2)) {
                lblMensaje.setForeground(new Color(211, 47, 47));
                lblMensaje.setText("Las contraseñas no coinciden.");
                return;
            }
            if (gestorUsuarios.registrarUsuario(usuario, pass1)) {
                lblMensaje.setForeground(new Color(56, 142, 60));
                lblMensaje.setText("¡Registro exitoso! Ahora puedes iniciar sesión.");
                onRegistroExitoso.run();
            } else {
                lblMensaje.setForeground(new Color(211, 47, 47));
                lblMensaje.setText("El usuario ya existe. Elige otro nombre.");
            }
        });

        btnVolver.addActionListener(e -> onVolver.run());
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
        txtPassword2.setText("");
        lblMensaje.setText(" ");
        lblMensaje.setForeground(new Color(211, 47, 47));
    }
}