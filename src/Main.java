package com.clinic.booking;

import com.clinic.booking.model.*;
import com.clinic.booking.repository.*;
import com.clinic.booking.view.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    private static DoctorRepository doctorRepo = new DoctorRepository();
    private static PatientRepository patientRepo = new PatientRepository();
    private static AppointmentRepository appointmentRepo = new AppointmentRepository();

    public static void main(String[] args) {
        // Add some sample data
        addSampleData();
        
        // Launch GUI
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(doctorRepo, patientRepo, appointmentRepo);
            mainFrame.setVisible(true);
        });
    }
    
    private static void addSampleData() {
        // Sample doctors
        Doctor doctor1 = new Doctor("D001", "John Smith", "Cardiologist");
        Doctor doctor2 = new Doctor("D002", "Sarah Lee", "Dermatologist");
        Doctor doctor3 = new Doctor("D003", "Mike Brown", "Pediatrician");
        
        doctorRepo.save(doctor1);
        doctorRepo.save(doctor2);
        doctorRepo.save(doctor3);
        
        // Sample patient
        Patient patient1 = new Patient("P001", "Alice Johnson", "555-1234", "alice@email.com");
        patientRepo.save(patient1);
    }
}