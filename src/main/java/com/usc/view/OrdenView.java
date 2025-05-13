package com.usc.view;

import com.usc.model.Comprador;
import com.usc.model.Vendedor;

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
    private JComboBox<Comprador> cbComprador;
    private JComboBox<Vendedor> cbVendedor;
    private JTable tblProductos;
    private DefaultTableModel tableModel;
    private JTextField txtTotal;
    private JButton btnAgregar, btnEliminar, btnProcesar, btnCancelar;

    public OrdenView(List<Comprador> compradores, List<Vendedor> vendedores, String numeroOrden) {
        super("Gestión de Orden");
        initComponents();
        populateCombos(compradores, vendedores);
        txtNumeroOrden.setText(numeroOrden);
        txtFecha.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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

        cbEstado = new JComboBox<>(new String[]{"PENDIENTE", "COMPLETADA", "CANCELADA"});
        cbComprador = new JComboBox<>();
        cbVendedor  = new JComboBox<>();

        // Tabla de productos
        tableModel = new DefaultTableModel(new Object[]{
            "Tipo", "Descripción", "Marca", "Precio", "Cantidad"
        }, 0);
        tblProductos = new JTable(tableModel);
        JScrollPane scrollProductos = new JScrollPane(tblProductos);

        // Total
        txtTotal = new JTextField(10);
        txtTotal.setEditable(false);

        // Botones
        btnAgregar   = new JButton("Agregar producto");
        btnEliminar  = new JButton("Eliminar producto");
        btnProcesar  = new JButton("Procesar orden");
        btnCancelar  = new JButton("Cancelar orden");

        // Vista panel de formulario
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y; panelForm.add(new JLabel("Número de Orden:"), gbc);
        gbc.gridx = 1;            panelForm.add(txtNumeroOrden, gbc);
        y++;
        gbc.gridx = 0; gbc.gridy = y; panelForm.add(new JLabel("Fecha:"), gbc);
        gbc.gridx = 1;            panelForm.add(txtFecha, gbc);
        y++;
        gbc.gridx = 0; gbc.gridy = y; panelForm.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;            panelForm.add(cbEstado, gbc);
        y++;
        gbc.gridx = 0; gbc.gridy = y; panelForm.add(new JLabel("Comprador:"), gbc);
        gbc.gridx = 1;            panelForm.add(cbComprador, gbc);
        y++;
        gbc.gridx = 0; gbc.gridy = y; panelForm.add(new JLabel("Vendedor:"), gbc);
        gbc.gridx = 1;            panelForm.add(cbVendedor, gbc);
        y++;
        gbc.gridx = 0; gbc.gridy = y; panelForm.add(new JLabel("Total:"), gbc);
        gbc.gridx = 1;            panelForm.add(txtTotal, gbc);

        // Panel de botones
        JPanel panelBtns = new JPanel();
        panelBtns.add(btnAgregar);
        panelBtns.add(btnEliminar);
        panelBtns.add(btnProcesar);
        panelBtns.add(btnCancelar);

        // Montaje en el Frame
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(6,6));
        cp.add(panelForm, BorderLayout.NORTH);
        cp.add(scrollProductos, BorderLayout.CENTER);
        cp.add(panelBtns, BorderLayout.SOUTH);
    }

    private void populateCombos(List<Comprador> compradores, List<Vendedor> vendedores) {
        compradores.forEach(cbComprador::addItem);
        vendedores.forEach(cbVendedor::addItem);
    }

}
