package com.usc.view;

import com.usc.repository.*;
import com.usc.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainView extends JFrame {

    private ProductService productService;
    private BuyerRepository buyerRepository;
    private SellerRepository sellerRepository;
    private OrderRepository orderRepository;

    private JTextField txtNumeroOrden;
    private JTextField txtFecha;
    private JComboBox<String> cbEstado;
    private JTable tblProductos;
    private DefaultTableModel tableModel;
    private JTextField txtTotal;
    private JButton btnAgregar, btnEliminar, btnProcesar, btnCancelar;


    public MainView(ProductService productService,
                    BuyerRepository buyerRepository,
                    SellerRepository sellerRepository,
                    OrderRepository orderRepository) {

        super("Menú Principal - Sistema de Ventas");
        this.productService = productService;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.orderRepository = orderRepository;

        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
        setLocationRelativeTo(null);


    }

    public void initComponents(){
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



        JButton viewStockButton = new JButton("Ver Stock");
        JButton registerSellerButton = new JButton("Registrar Seller");
        JButton registerBuyerButton = new JButton("Registrar Buyer");
        JButton createOrderButton = new JButton("Crear Orden de Venta");

        add(viewStockButton);
        add(registerSellerButton);
        add(registerBuyerButton);
        add(createOrderButton);

        viewStockButton.addActionListener(e -> {
            ProductView productView = new ProductView(productService);
            productView.setVisible(true);
        });

        registerSellerButton.addActionListener(e -> {
            SellerView sellerView = new SellerView(sellerRepository);
            sellerView.setVisible(true);
        });

        registerBuyerButton.addActionListener(e -> {
            BuyerView buyerView = new BuyerView(buyerRepository);
            buyerView.setVisible(true);
        });

        createOrderButton.addActionListener(e -> {
            OrderView orderView = new OrderView(productService, buyerRepository, sellerRepository, orderRepository);
            orderView.setVisible(true);
        });

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductRepository productRepo = new ProductRepository();
            BuyerRepository buyerRepo = new BuyerRepository();
            SellerRepository sellerRepo = new SellerRepository();
            OrderRepository orderRepo = new OrderRepository();

            ProductService productService = new ProductService(productRepo);

            new MainView(productService, buyerRepo, sellerRepo, orderRepo).setVisible(true);
        });
    }
}
