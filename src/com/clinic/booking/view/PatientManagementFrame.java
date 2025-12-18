package com.clinic.booking.view;

import com.clinic.booking.model.Patient;
import com.clinic.booking.repository.PatientRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientManagementFrame extends JFrame {
    private PatientRepository patientRepo;
    private MainFrame mainFrame;
    private JTable patientTable;
    private DefaultTableModel tableModel;

    public PatientManagementFrame(PatientRepository patientRepo, MainFrame mainFrame) {
        this.patientRepo = patientRepo;
        this.mainFrame = mainFrame;

        setTitle("Patient Management");
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
        String[] columnNames = {"ID", "Name", "Phone", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        patientTable = new JTable(tableModel);
        loadPatients();
        add(new JScrollPane(patientTable), BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();

        formPanel.add(new JLabel("Patient ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if (!id.isEmpty() && !name.isEmpty()) {
                Patient newPatient = new Patient(id, name, phone, email);
                patientRepo.save(newPatient);
                loadPatients();
                
                // Clear fields
                idField.setText("");
                nameField.setText("");
                phoneField.setText("");
                emailField.setText("");
                
                JOptionPane.showMessageDialog(this, "Patient added successfully!");
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

    private void loadPatients() {
        tableModel.setRowCount(0);
        List<Patient> patients = patientRepo.findAll();
        for (Patient p : patients) {
            Object[] row = {p.getId(), p.getName(), p.getPhone(), p.getEmail()};
            tableModel.addRow(row);
        }
    }
}
