package com.clinic.booking.model;

public class Patient {
    private String id;
    private String name;
    private String phone;
    private String email;
    
    public Patient(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return "Patient ID: " + id + " - " + name + " | Phone: " + phone + " | Email: " + email;
    }
}
