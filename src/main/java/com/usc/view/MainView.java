package com.usc.view;

import com.usc.repository.*;
import com.usc.service.ProductService;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private ProductService productService;
    private BuyerRepository buyerRepository;
    private SellerRepository sellerRepository;
    private OrderRepository orderRepository;

    public MainView(ProductService productService,
                    BuyerRepository buyerRepository,
                    SellerRepository sellerRepository,
                    OrderRepository orderRepository) {

        this.productService = productService;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.orderRepository = orderRepository;

        setTitle("Menú Principal - Sistema de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton viewStockButton = new JButton("Ver Stock");
        JButton registerSellerButton = new JButton("Registrar Vendedor");
        JButton registerBuyerButton = new JButton("Registrar Comprador");
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
