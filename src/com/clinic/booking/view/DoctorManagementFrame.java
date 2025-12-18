package com.clinic.booking.view;

import com.clinic.booking.model.Doctor;
import com.clinic.booking.repository.DoctorRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoctorManagementFrame extends JFrame {
    private DoctorRepository doctorRepo;
    private MainFrame mainFrame;
    private JTable doctorTable;
    private DefaultTableModel tableModel;

    public DoctorManagementFrame(DoctorRepository doctorRepo, MainFrame mainFrame) {
        this.doctorRepo = doctorRepo;
        this.mainFrame = mainFrame;

        setTitle("Doctor Management");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setVisible(true);
            }
        });

        // Table
        String[] columnNames = {"ID", "Name", "Specialization"};
        tableModel = new DefaultTableModel(columnNames, 0);
        doctorTable = new JTable(tableModel);
        loadDoctors();
        add(new JScrollPane(doctorTable), BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField specField = new JTextField();

        formPanel.add(new JLabel("Doctor ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Specialization:"));
        formPanel.add(specField);

        JButton addButton = new JButton("Add Doctor");
        addButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String spec = specField.getText();

            if (!id.isEmpty() && !name.isEmpty()) {
                Doctor newDoctor = new Doctor(id, name, spec);
                doctorRepo.save(newDoctor);
                loadDoctors();
                
                // Clear fields
                idField.setText("");
                nameField.setText("");
                specField.setText("");
                
                JOptionPane.showMessageDialog(this, "Doctor added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "ID and Name are required.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            mainFrame.setVisible(true);
        });

        formPanel.add(backButton);
        formPanel.add(addButton);

        add(formPanel, BorderLayout.SOUTH);
    }

    private void loadDoctors() {
        tableModel.setRowCount(0);
        List<Doctor> doctors = doctorRepo.findAll();
        for (Doctor d : doctors) {
            Object[] row = {d.getId(), d.getName(), d.getSpecialization()};
            tableModel.addRow(row);
        }
    }
}
