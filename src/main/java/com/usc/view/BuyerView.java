package com.usc.view;

import com.usc.repository.BuyerRepository;

import javax.swing.*;

public class BuyerView extends JFrame {
    public BuyerView(BuyerRepository buyerRepository) {
        setTitle("Registro de Buyer");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField();
        JButton saveButton = new JButton("Registrar");

        add(new JLabel("Nombre del Buyer:"));
        add(nameField);
        add(saveButton);

        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                buyerRepository.addBuyer(name);
                JOptionPane.showMessageDialog(this, "Buyer '" + name + "' registrado.");
                nameField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
            }
        });
    }
}
