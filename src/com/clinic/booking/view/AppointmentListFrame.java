package com.clinic.booking.view;

import com.clinic.booking.model.Appointment;
import com.clinic.booking.repository.AppointmentRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppointmentListFrame extends JFrame {
    private AppointmentRepository appointmentRepo;
    private MainFrame mainFrame;
    private JTable appointmentTable;
    private DefaultTableModel tableModel;

    public AppointmentListFrame(AppointmentRepository appointmentRepo, MainFrame mainFrame) {
        this.appointmentRepo = appointmentRepo;
        this.mainFrame = mainFrame;

        setTitle("All Appointments");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setVisible(true);
            }
        });

        String[] columnNames = {"ID", "Patient", "Doctor", "Date & Time", "Reason"};
        tableModel = new DefaultTableModel(columnNames, 0);
        appointmentTable = new JTable(tableModel);
        
        loadAppointments();
        
        add(new JScrollPane(appointmentTable), BorderLayout.CENTER);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadAppointments());
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            mainFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadAppointments() {
        tableModel.setRowCount(0);
        List<Appointment> appointments = appointmentRepo.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        for (Appointment a : appointments) {
            Object[] row = {
                a.getId(),
                a.getPatient().getName(),
                a.getDoctor().getName(),
                a.getDateTime().format(formatter),
                a.getReason()
            };
            tableModel.addRow(row);
        }
    }
}
