package com.clinic.booking.repository;

import com.clinic.booking.model.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientRepository implements Repository<Patient> {
    private List<Patient> patients = new ArrayList<>();

    @Override
    public void save(Patient patient) {
        patients.add(patient);
        System.out.println("Patient saved: " + patient.getName());
    }

    @Override
    public Optional<Patient> findById(String id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }

    @Override
    public void delete(String id) {
        patients.removeIf(p -> p.getId().equals(id));
        System.out.println("Patient deleted: " + id);
    }
}