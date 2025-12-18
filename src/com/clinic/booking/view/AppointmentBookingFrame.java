package com.clinic.booking.view;

import com.clinic.booking.model.Appointment;
import com.clinic.booking.model.Doctor;
import com.clinic.booking.model.Patient;
import com.clinic.booking.repository.AppointmentRepository;
import com.clinic.booking.repository.DoctorRepository;
import com.clinic.booking.repository.PatientRepository;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

public class AppointmentBookingFrame extends JFrame {
    private AppointmentRepository appointmentRepo;
    private PatientRepository patientRepo;
    private DoctorRepository doctorRepo;
    private MainFrame mainFrame;

    private JComboBox<PatientItem> patientComboBox;
    private JComboBox<DoctorItem> doctorComboBox;
    private JTextField dateTimeField;
    private JTextField reasonField;

    public AppointmentBookingFrame(AppointmentRepository appointmentRepo, PatientRepository patientRepo, DoctorRepository doctorRepo, MainFrame mainFrame) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.mainFrame = mainFrame;

        setTitle("Book Appointment");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setVisible(true);
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Patient Selection
        patientComboBox = new JComboBox<>();
        loadPatients();
        formPanel.add(new JLabel("Select Patient:"));
        formPanel.add(patientComboBox);

        // Doctor Selection
        doctorComboBox = new JComboBox<>();
        loadDoctors();
        formPanel.add(new JLabel("Select Doctor:"));
        formPanel.add(doctorComboBox);

        // Date Time
        dateTimeField = new JTextField(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        formPanel.add(new JLabel("Date & Time (yyyy-MM-dd HH:mm):"));
        formPanel.add(dateTimeField);

        // Reason
        reasonField = new JTextField();
        formPanel.add(new JLabel("Reason:"));
        formPanel.add(reasonField);

        // Buttons
        JButton bookButton = new JButton("Book");
        bookButton.addActionListener(e -> bookAppointment());
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
            mainFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadPatients() {
        List<Patient> patients = patientRepo.findAll();
        for (Patient p : patients) {
            patientComboBox.addItem(new PatientItem(p));
        }
    }

    private void loadDoctors() {
        List<Doctor> doctors = doctorRepo.findAll();
        for (Doctor d : doctors) {
            doctorComboBox.addItem(new DoctorItem(d));
        }
    }

    private void bookAppointment() {
        PatientItem selectedPatient = (PatientItem) patientComboBox.getSelectedItem();
        DoctorItem selectedDoctor = (DoctorItem) doctorComboBox.getSelectedItem();
        String dateTimeStr = dateTimeField.getText();
        String reason = reasonField.getText();

        if (selectedPatient == null || selectedDoctor == null || dateTimeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            
            String id = UUID.randomUUID().toString().substring(0, 8);
            Appointment appointment = new Appointment(id, selectedPatient.patient, selectedDoctor.doctor, dateTime, reason);
            
            appointmentRepo.save(appointment);
            JOptionPane.showMessageDialog(this, "Appointment booked successfully!");
            dispose();
            mainFrame.setVisible(true);

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper classes for ComboBox
    private static class PatientItem {
        Patient patient;
        PatientItem(Patient patient) { this.patient = patient; }
        @Override public String toString() { return patient.getName() + " (" + patient.getId() + ")"; }
    }

    private static class DoctorItem {
        Doctor doctor;
        DoctorItem(Doctor doctor) { this.doctor = doctor; }
        @Override public String toString() { return doctor.getName() + " - " + doctor.getSpecialization(); }
    }
}
