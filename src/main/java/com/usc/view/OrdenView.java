package com.usc.view;

import com.usc.model.Buyer;
import com.usc.model.Seller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrdenView extends JFrame {
    private JTextField txtNumeroOrden;
    private JTextField txtFecha;
    private JComboBox<String> cbEstado;
    private JTable tblProductos;
    private DefaultTableModel tableModel;
    private JTextField txtTotal;
    private JButton btnAgregar, btnEliminar, btnProcesar, btnCancelar;

    public OrdenView(List<Buyer> Buyeres, List<Seller> Selleres, String numeroOrden) {
        super("Orden");
        initComponents();
        txtNumeroOrden.setText(numeroOrden);
        txtFecha.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Campos de cabecera
        txtNumeroOrden = new JTextField(15);
        txtNumeroOrden.setEditable(false);
        txtFecha = new JTextField(15);
        txtFecha.setEditable(false);

        cbEstado = new JComboBox<>(new String[] { "PENDIENTE", "COMPLETADA", "CANCELADA" }); //Lista de estados

        // Tabla de productos
        tableModel = new DefaultTableModel(new Object[] {
                "Tipo", "Descripción", "Marca", "Precio", "Cantidad"
        }, 0);
        tblProductos = new JTable(tableModel);
        JScrollPane scrollProductos = new JScrollPane(tblProductos);

        // Total
        txtTotal = new JTextField(10);
        txtTotal.setEditable(false);

        // Botones
        btnAgregar = new JButton("Agregar producto");
        btnEliminar = new JButton("Eliminar producto");
        btnProcesar = new JButton("Procesar orden");
        btnCancelar = new JButton("Cancelar orden");

        // Colores botones
        btnAgregar.setBackground(new Color(0x4CAF50));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setOpaque(true);
        btnAgregar.setBorderPainted(false);
        btnEliminar.setBackground(new Color(0xF44336));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setOpaque(true);
        btnEliminar.setBorderPainted(false);
        btnProcesar.setBackground(new Color(0x2196F3));
        btnProcesar.setForeground(Color.WHITE);
        btnProcesar.setOpaque(true);
        btnProcesar.setBorderPainted(false);
        btnCancelar.setBackground(new Color(0xFF9800));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setOpaque(true);
        btnCancelar.setBorderPainted(false);

        // Vista panel de formulario
        JPanel panelForm = new JPanel(new GridBagLayout()); //Crea un panel con un layout de cuadricula
        panelForm.setBorder(BorderFactory.createTitledBorder("Detalles de la Orden"));
        panelForm.setPreferredSize(new Dimension(400, 200)); // Establece el tamaño preferido del panel
        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;
        gbc.gridx = 0; //En que columna voy a ubicar el componente
        gbc.gridy = y; //En que fila voy a ubicar el componente
        panelForm.add(new JLabel("NUMERO DE ORDEN:"), gbc);
        gbc.gridx = 1;
        panelForm.add(txtNumeroOrden, gbc);
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panelForm.add(new JLabel("FECHA:"), gbc);
        gbc.gridx = 1;
        panelForm.add(txtFecha, gbc);
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panelForm.add(new JLabel("ESTADO:"), gbc);
        gbc.gridx = 1;
        panelForm.add(cbEstado, gbc);
        y++;
        gbc.gridx = 0;
        gbc.gridy = y;
        panelForm.add(new JLabel("TOTAL"), gbc);
        gbc.gridx = 1;
        panelForm.add(txtTotal, gbc);

        // Panel de botones
        JPanel panelBtns = new JPanel();
        panelBtns.add(btnAgregar);
        panelBtns.add(btnEliminar);
        panelBtns.add(btnProcesar);
        panelBtns.add(btnCancelar);

        // Montaje en el Frame
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(6, 6));
        cp.add(panelForm, BorderLayout.NORTH);
        cp.add(scrollProductos, BorderLayout.CENTER);
        cp.add(panelBtns, BorderLayout.SOUTH);
    }

 

}
