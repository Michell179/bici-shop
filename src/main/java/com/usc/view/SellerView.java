package com.usc.view;

import com.usc.repository.SellerRepository;

import javax.swing.*;

public class SellerView extends JFrame {
    public SellerView(SellerRepository sellerRepository) {
        setTitle("Registro de Vendedor");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField();
        JButton saveButton = new JButton("Registrar");

        add(new JLabel("Nombre del Vendedor:"));
        add(nameField);
        add(saveButton);

        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                sellerRepository.addSeller(name);
                JOptionPane.showMessageDialog(this, "Vendedor '" + name + "' registrado.");
                nameField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
            }
        });
    }
}
