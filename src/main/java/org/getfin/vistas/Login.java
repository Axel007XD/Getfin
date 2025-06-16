/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.getfin.vistas;

import org.getfin.controlador.*;
import org.getfin.modelos.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author axel
 */
public class Login extends javax.swing.JFrame implements ActionListener, MouseListener {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        jLabel3.addMouseListener(this);
        this.setTitle("Login");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginController= new LoginController();
        // Crear un usuario de ejemplo
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("admin");
        usuario.setContrasena("admin123");
        usuario.setRol(Usuario.Rol.admin);
        usuarioController.guardaUsuario(usuario);
        usuarioList = usuarioController.getUsuario();



        ProductoController productoController= new ProductoController();

        Producto producto = new Producto();
        producto.setNombre("Leche");
        producto.setCategoria(Producto.CategoriaProducto.valueOf("animal"));
        productoController.guardarCliente(producto);

        HerramientaController herramientaController= new HerramientaController();
        // Crear una herramienta
        Herramienta herramienta = new Herramienta();
        herramienta.setNombre("Machete");
        herramienta.setDescripcion("Herramienta de corte");
        herramienta.setCantidad(3);
        herramienta.setFechaAdquisicion(LocalDate.now());
        herramientaController.guardarHerramienta(herramienta);

        AnimalController animalController = new AnimalController();
        // Crear un animal
        Animal animal = new Animal();
        animal.setChapa("VAC-001");
        animal.setFechaAdquisicion(LocalDate.of(2023, 3, 10));
        animal.setEstado(Animal.EstadoAnimal.valueOf("produccion"));
        animalController.guardarAnimal(animal);

        AnimalAgrupadoController animalAgrupadoController = new AnimalAgrupadoController();
        // Crear un animal agrupado (ej. colmena de abejas)
        AnimalAgrupado agrupado = new AnimalAgrupado();
        agrupado.setNombre("Colmena 1");
        agrupado.setCantidad(100);
        agrupado.setTipo(AnimalAgrupado.TipoAgrupado.valueOf("abejas"));
        agrupado.setFechaInicio(LocalDate.now());
        animalAgrupadoController.guardarCliente(agrupado);

        IngresoController ingresoController= new IngresoController();
        // Crear un ingreso
        Ingreso ingreso = new Ingreso();
        ingreso.setFecha(LocalDate.now());
        ingreso.setNumeroFactura("F001");
        ingreso.setDescripcion("Venta de leche");
        ingreso.setTipoIngreso(Ingreso.TipoIngreso.venta_agroindustriado);
        ingresoController.guardarIngreso(ingreso);

        DetalleIngresoController detalleIngresoController= new DetalleIngresoController();
        // Crear detalle del ingreso
        DetalleIngreso detalleIngreso = new DetalleIngreso();
        detalleIngreso.setIngreso(ingreso);
        detalleIngreso.setProducto(producto);
        detalleIngreso.setCantidad(new BigDecimal("10.00"));
        detalleIngreso.setUnidad("litros");
        detalleIngreso.setPrecioUnitario(new BigDecimal("0.80"));
        detalleIngreso.setIvaPorcentaje(BigDecimal.ZERO);
        detalleIngreso.setRetencionPorcentaje(BigDecimal.ZERO);
        detalleIngreso.setSubtotalBase(new BigDecimal("8.00"));
        detalleIngreso.setSubtotalIva(BigDecimal.ZERO);
        detalleIngreso.setSubtotalRetencion(BigDecimal.ZERO);
        detalleIngreso.setSubtotalFinal(new BigDecimal("8.00"));
        detalleIngresoController.guardarDetalleIngresoController(detalleIngreso);


        EgresoController egresoController= new EgresoController();
        // Crear un egreso
        Egreso egreso = new Egreso();
        egreso.setFecha(LocalDate.now());
        egreso.setDescripcion("Compra de machete nuevo");
        egresoController.guardarEgreso(egreso);


        DetalleEgresoController detalleEgresoController= new DetalleEgresoController();
        // Crear detalle del egreso
        DetalleEgreso detalleEgreso = new DetalleEgreso();
        detalleEgreso.setEgreso(egreso);
        detalleEgreso.setHerramienta(herramienta);
        detalleEgreso.setCantidad(1);
        detalleEgreso.setPrecioUnitario(15.00);
        detalleEgreso.setDescripcion("Compra para uso agrícola");
        detalleEgresoController.guardarDetalle(detalleEgreso);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtEmail = new java.awt.TextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jpContraseña = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        checkbox1 = new java.awt.Checkbox();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(83, 102, 119));

        setSize(new java.awt.Dimension(1600, 1080));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.darkGray, null));

        jPanel1.setBackground(new java.awt.Color(12, 55, 15));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(12, 55, 15));
        jPanel3.setPreferredSize(new java.awt.Dimension(564, 42));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/getfinRegister.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(217, 217, 217));

        txtEmail.setBackground(new java.awt.Color(217, 217, 217));
        txtEmail.setName(""); // NOI18N
        txtEmail.setText("Email");
        //txtEmail.setBorder();
        jLabel4.setFont(new java.awt.Font("Inter", Font.BOLD, 18)); // NOI18N
        jLabel4.setText("Email");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Perrfill 1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addGap(21, 21, 21)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(217, 217, 217));
        jPanel5.setPreferredSize(new java.awt.Dimension(517, 75));

        jpContraseña.setBackground(new java.awt.Color(217, 217, 217));
        jpContraseña.setText("contraseña");
        jpContraseña.setBorder(null);
        jLabel5.setFont(new java.awt.Font("Inter",java.awt.Font.BOLD, 18)); //
        jLabel5.setText("Password");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Contraseña 1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jpContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpContraseña, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18))
        );

        jLabel2.setText("Recordar Contraseña:");

        jPanel6.setBackground(new java.awt.Color(31, 126, 38));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Login");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(2, 2, 2)
                                .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jLabel1)))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(60, 60, 60)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setVisible(true);
        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify                     
    private java.awt.Checkbox checkbox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jpContraseña;
    private java.awt.TextField txtEmail;
    private LoginController loginController;
    private List<Usuario> usuarioList = new ArrayList<>();


    @Override
    public void mousePressed(MouseEvent mouseEvent) {

        String userName = txtEmail.getText();
        String userContraseña = new String(jpContraseña.getPassword());
        txtEmail.setText("");
        jpContraseña.setText("");

        boolean autenticado = false;

        if (usuarioList != null) {
            for (Usuario usuario : usuarioList) {
                if (usuario.getNombreUsuario().equals(userName) && usuario.getContrasena().equals(userContraseña)) {
                    autenticado = true;
                    break;
                }
            }
        }

        if (autenticado) {
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");

        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }


}
