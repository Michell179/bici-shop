package com.usc.view;

import com.usc.model.Product;
import com.usc.repository.ProductRepository;
import com.usc.service.ProductService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductView extends JFrame {
    private ProductService service;
    private JComboBox<Product> productComboBox;
    private JTextField quantityField;
    private JButton sellButton;
    private JTextArea outputArea;

    public ProductView(ProductService service) {
        this.service = service;
        setTitle("Sistema de Ventas");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de selección
        JPanel topPanel = new JPanel();
        productComboBox = new JComboBox<>();
        updateProductList();
        quantityField = new JTextField(5);
        sellButton = new JButton("Vender");

        topPanel.add(new JLabel("Producto:"));
        topPanel.add(productComboBox);
        topPanel.add(new JLabel("Cantidad:"));
        topPanel.add(quantityField);
        topPanel.add(sellButton);

        // Área de salida
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Acción del botón
        sellButton.addActionListener(e -> {
            Product selected = (Product) productComboBox.getSelectedItem();
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                if (service.sellProduct(selected.getId(), quantity)) {
                    outputArea.append("Venta exitosa: " + quantity + " x " + selected.getName() + "\n");
                } else {
                    outputArea.append("Error: stock insuficiente o entrada inválida\n");
                }
                updateProductList();
            } catch (NumberFormatException ex) {
                outputArea.append("Error: cantidad inválida\n");
            }
        });
    }

    private void updateProductList() {
        productComboBox.removeAllItems();
        for (Product p : service.getAvailableProducts()) {
            productComboBox.addItem(p);
        }
    }
}
