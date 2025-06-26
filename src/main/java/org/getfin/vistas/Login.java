package org.getfin.vistas;

import org.getfin.controlador.UsuarioController;
import org.getfin.modelos.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Pantalla de Login limpia y centrada.
 */
public class Login extends JFrame implements MouseListener {
    private java.awt.TextField txtEmail;
    private JPasswordField jpContraseña;
    private JLabel jLabelLogin;
    private UsuarioController usuarioController;

    public Login() {
        initComponents();
        this.setTitle("Login");
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Centrar pantalla
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usuarioController = UsuarioController.getInstance();
        ensureAdminUser();

        jLabelLogin.addMouseListener(this);
        setupPlaceholder(txtEmail, "Email");
        setupPasswordPlaceholder(jpContraseña, "contraseña");
    }

    private void initComponents() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(600, 500)); // Tamaño adecuado
        this.setContentPane(contentPane);

        // Encabezado con imagen
        JLabel headerLabel = new JLabel();
        headerLabel.setIcon(new ImageIcon(getClass().getResource("/getfinRegister.png")));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(headerLabel, BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // Email
        JPanel emailPanel = new JPanel(new BorderLayout(10, 10));
        emailPanel.setBackground(new Color(217, 217, 217));
        JLabel emailLabel = new JLabel("Email");
        JLabel emailIcon = new JLabel(new ImageIcon(getClass().getResource("/Perrfill 1.png")));
        txtEmail = new java.awt.TextField();
        emailPanel.add(emailLabel, BorderLayout.NORTH);
        JPanel emailFieldPanel = new JPanel(new BorderLayout());
        emailFieldPanel.setOpaque(false);
        emailFieldPanel.add(txtEmail, BorderLayout.CENTER);
        emailFieldPanel.add(emailIcon, BorderLayout.EAST);
        emailPanel.add(emailFieldPanel, BorderLayout.CENTER);
        centerPanel.add(emailPanel);
        centerPanel.add(Box.createVerticalStrut(15));

        // Contraseña
        JPanel passPanel = new JPanel(new BorderLayout(10, 10));
        passPanel.setBackground(new Color(217, 217, 217));
        JLabel passLabel = new JLabel("Password");
        JLabel passIcon = new JLabel(new ImageIcon(getClass().getResource("/Contraseña 1.png")));
        jpContraseña = new JPasswordField();
        jpContraseña.setBorder(null);
        JPanel passFieldPanel = new JPanel(new BorderLayout());
        passFieldPanel.setOpaque(false);
        passFieldPanel.add(jpContraseña, BorderLayout.CENTER);
        passFieldPanel.add(passIcon, BorderLayout.EAST);
        passPanel.add(passLabel, BorderLayout.NORTH);
        passPanel.add(passFieldPanel, BorderLayout.CENTER);
        centerPanel.add(passPanel);
        centerPanel.add(Box.createVerticalStrut(15));

        // Checkbox
        /*Checkbox checkbox = new Checkbox();
        centerPanel.add(checkbox);
        centerPanel.add(Box.createVerticalStrut(20));
        */
        // Panel login botón
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(31, 126, 38));
        jLabelLogin = new JLabel("Login");
        jLabelLogin.setForeground(Color.WHITE);
        jLabelLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabelLogin.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(jLabelLogin);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        this.pack(); // ajustar al tamaño preferido
    }

    private void ensureAdminUser() {
        Usuario admin = usuarioController.getUsuarioPorNombre("admin");
        if (admin != null) {
            System.out.println("Usuario 'admin' ya existe.");
            return;
        }
        Usuario nuevoAdmin = new Usuario();
        nuevoAdmin.setNombreUsuario("admin");
        nuevoAdmin.setContrasena(hashPassword("1234"));
        nuevoAdmin.setRol(Usuario.Rol.admin);
        usuarioController.guardaUsuario(nuevoAdmin);
        System.out.println("Usuario 'admin' creado.");
    }

    private String hashPassword(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    private void setupPlaceholder(java.awt.TextField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void setupPasswordPlaceholder(JPasswordField field, String placeholder) {
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setEchoChar((char) 0); // Mostrar como texto
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                String pwd = new String(field.getPassword());
                if (pwd.equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                    field.setEchoChar('•'); // Punto en lugar de '*'
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                    field.setEchoChar((char) 0);
                }
            }
        });
    }

    @Override
    public void mousePressed(MouseEvent e) {
        String userName = txtEmail.getText().trim();
        String userPass = new String(jpContraseña.getPassword());

        Usuario usuario = usuarioController.getUsuarioPorNombre(userName);
        if (usuario != null && BCrypt.checkpw(userPass, usuario.getContrasena())) {

            dispose();
            new VentanaPrincipal().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrecta.");
        }
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
