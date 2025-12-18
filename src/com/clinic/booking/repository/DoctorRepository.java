package com.clinic.booking.repository;

import com.clinic.booking.model.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorRepository implements Repository<Doctor> {
    private List<Doctor> doctors = new ArrayList<>();
    
    @Override
    public void save(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor saved: " + doctor.getName());
    }
    
    @Override
    public Optional<Doctor> findById(String id) {
        return doctors.stream()
            .filter(d -> d.getId().equals(id))
            .findFirst();
    }
    
    @Override
    public List<Doctor> findAll() {
        return new ArrayList<>(doctors);
    }
    
    @Override
    public void delete(String id) {
        doctors.removeIf(d -> d.getId().equals(id));
        System.out.println("Doctor deleted: " + id);
    }
}
