package com.clinic.booking.model;

import java.time.LocalDateTime;

public class Appointment {
    private String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private String reason;
    
    public Appointment(String id, Patient patient, Doctor doctor, 
                      LocalDateTime dateTime, String reason) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.reason = reason;
    }
    
    // Getters
    public String getId() { return id; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getReason() { return reason; }
    
    @Override
    public String toString() {
        return "Appt ID: " + id + 
               " | Patient ID: " + patient.getId() + " (" + patient.getName() + ")" +
               " | Doctor ID: " + doctor.getId() + " (" + doctor.getName() + ")" +
               " | Time: " + dateTime.toLocalDate() + " " + dateTime.toLocalTime() +
               " | Reason: " + reason;
    }
}
