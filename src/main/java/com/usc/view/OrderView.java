package com.usc.view;

import com.usc.model.*;
import com.usc.repository.*;
import com.usc.service.ProductService;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {
    public OrderView(ProductService productService,
                     BuyerRepository buyerRepository,
                     SellerRepository sellerRepository,
                     OrderRepository orderRepository) {

        setTitle("Crear Orden de Venta");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JComboBox<Product> productComboBox = new JComboBox<>();
        JComboBox<Buyer> buyerComboBox = new JComboBox<>();
        JComboBox<Seller> sellerComboBox = new JComboBox<>();
        JTextField quantityField = new JTextField(5);
        JButton sellButton = new JButton("Confirmar Venta");
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Cargar datos
        for (Product p : productService.getAvailableProducts()) {
            productComboBox.addItem(p);
        }
        for (Buyer b : buyerRepository.getAllBuyers()) {
            buyerComboBox.addItem(b);
        }
        for (Seller s : sellerRepository.getAllSellers()) {
            sellerComboBox.addItem(s);
        }

        // Panel superior
        JPanel topPanel = new JPanel(new GridLayout(5, 2));
        topPanel.add(new JLabel("Producto:"));
        topPanel.add(productComboBox);
        topPanel.add(new JLabel("Cantidad:"));
        topPanel.add(quantityField);
        topPanel.add(new JLabel("Comprador:"));
        topPanel.add(buyerComboBox);
        topPanel.add(new JLabel("Vendedor:"));
        topPanel.add(sellerComboBox);
        topPanel.add(new JLabel(""));
        topPanel.add(sellButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Acción de venta
        sellButton.addActionListener(e -> {
            try {
                Product product = (Product) productComboBox.getSelectedItem();
                Buyer buyer = (Buyer) buyerComboBox.getSelectedItem();
                Seller seller = (Seller) sellerComboBox.getSelectedItem();
                int quantity = Integer.parseInt(quantityField.getText());

                if (product == null || buyer == null || seller == null) {
                    resultArea.append("Debe seleccionar producto, comprador y vendedor.\n");
                    return;
                }

                if (quantity <= 0) {
                    resultArea.append("Cantidad inválida.\n");
                    return;
                }

                if (productService.sellProduct(product.getId(), quantity)) {
                    Order order = new Order(product, buyer, seller, quantity);
                    orderRepository.addOrder(order);
                    resultArea.append("Orden registrada:\n" + order + "\n\n");

                    // Recargar productos (por si el stock cambió)
                    productComboBox.removeAllItems();
                    for (Product p : productService.getAvailableProducts()) {
                        productComboBox.addItem(p);
                    }

                } else {
                    resultArea.append("Error: stock insuficiente.\n");
                }

            } catch (NumberFormatException ex) {
                resultArea.append("Cantidad inválida.\n");
            }
        });
    }
}
