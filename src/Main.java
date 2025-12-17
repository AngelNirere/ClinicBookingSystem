package com.clinic.booking;

import com.clinic.booking.model.*;
import com.clinic.booking.repository.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Optional;

public class Main {
    private static DoctorRepository doctorRepo = new DoctorRepository();
    private static PatientRepository patientRepo = new PatientRepository();
    private static AppointmentRepository appointmentRepo = new AppointmentRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Clinic Booking System ===\n");
        
        // Add some sample data
        addSampleData();
        
        // Show menu
        showMenu();
        
        scanner.close();
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
    
    private static void showMenu() {
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. List All Doctors");
            System.out.println("2. Find Doctor by ID");
            System.out.println("3. List All Patients");
            System.out.println("4. Add New Patient");
            System.out.println("5. Book Appointment");
            System.out.println("6. List All Appointments");
            System.out.println("7. Exit");
            System.out.print("Choose option (1-7): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    listAllDoctors();
                    break;
                case "2":
                    findDoctorById();
                    break;
                case "3":
                    listAllPatients();
                    break;
                case "4":
                    addNewPatient();
                    break;
                case "5":
                    bookAppointment();
                    break;
                case "6":
                    listAllAppointments();
                    break;
                case "7":
                    running = false;
                    System.out.println("Thank you for using Clinic Booking System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void listAllDoctors() {
        System.out.println("\n=== All Doctors ===");
        for (Doctor d : doctorRepo.findAll()) {
            System.out.println("- " + d);
        }
    }
    
    private static void findDoctorById() {
        System.out.print("Enter Doctor ID: ");
        String id = scanner.nextLine();
        
        Optional<Doctor> doctor = doctorRepo.findById(id);
        if (doctor.isPresent()) {
            System.out.println("Found: " + doctor.get());
        } else {
            System.out.println("Doctor not found with ID: " + id);
        }
    }
    
    private static void listAllPatients() {
        System.out.println("\n=== All Patients ===");
        for (Patient p : patientRepo.findAll()) {
            System.out.println("- " + p);
        }
    }
    
    private static void addNewPatient() {
        System.out.println("\n=== Add New Patient ===");
        
        System.out.print("Patient ID: ");
        String id = scanner.nextLine();
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Patient patient = new Patient(id, name, phone, email);
        patientRepo.save(patient);
    }
    
    private static void bookAppointment() {
        System.out.println("\n=== Book Appointment ===");
        
        // List doctors first
        listAllDoctors();
        
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();
        
        Optional<Doctor> doctorOpt = doctorRepo.findById(doctorId);
        if (!doctorOpt.isPresent()) {
            System.out.println("Doctor not found!");
            return;
        }
        
        // List patients
        listAllPatients();
        
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        
        Optional<Patient> patientOpt = patientRepo.findById(patientId);
        if (!patientOpt.isPresent()) {
            System.out.println("Patient not found!");
            return;
        }
        
        System.out.print("Appointment ID: ");
        String apptId = scanner.nextLine();
        
        System.out.print("Reason: ");
        String reason = scanner.nextLine();
        
        // Use current date/time for simplicity
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0);
        
        Appointment appointment = new Appointment(
            apptId,
            patientOpt.get(),
            doctorOpt.get(),
            dateTime,
            reason
        );
        
        appointmentRepo.save(appointment);
        System.out.println("Appointment booked successfully for: " + dateTime.toLocalDate());
    }
    
    private static void listAllAppointments() {
        System.out.println("\n=== All Appointments ===");
        for (Appointment a : appointmentRepo.findAll()) {
            System.out.println("- " + a);
        }
    }
}