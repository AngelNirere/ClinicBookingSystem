package com.clinic.booking.view;

import com.clinic.booking.repository.AppointmentRepository;
import com.clinic.booking.repository.DoctorRepository;
import com.clinic.booking.repository.PatientRepository;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private DoctorRepository doctorRepo;
    private PatientRepository patientRepo;
    private AppointmentRepository appointmentRepo;

    public MainFrame(DoctorRepository doctorRepo, PatientRepository patientRepo, AppointmentRepository appointmentRepo) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
        this.appointmentRepo = appointmentRepo;

        setTitle("Clinic Booking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Clinic Booking System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton patientBtn = new JButton("Manage Patients");
        JButton doctorBtn = new JButton("Manage Doctors");
        JButton appointmentBtn = new JButton("Book Appointment");
        JButton viewAppointmentsBtn = new JButton("View Appointments");

        patientBtn.addActionListener(e -> openPatientManagement());
        doctorBtn.addActionListener(e -> openDoctorManagement());
        appointmentBtn.addActionListener(e -> openAppointmentBooking());
        viewAppointmentsBtn.addActionListener(e -> openViewAppointments());

        buttonPanel.add(patientBtn);
        buttonPanel.add(doctorBtn);
        buttonPanel.add(appointmentBtn);
        buttonPanel.add(viewAppointmentsBtn);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void openPatientManagement() {
        this.setVisible(false);
        new PatientManagementFrame(patientRepo, this).setVisible(true);
    }

    private void openDoctorManagement() {
        this.setVisible(false);
        new DoctorManagementFrame(doctorRepo, this).setVisible(true);
    }

    private void openAppointmentBooking() {
        this.setVisible(false);
        new AppointmentBookingFrame(appointmentRepo, patientRepo, doctorRepo, this).setVisible(true);
    }

    private void openViewAppointments() {
        this.setVisible(false);
        new AppointmentListFrame(appointmentRepo, this).setVisible(true);
    }
}
