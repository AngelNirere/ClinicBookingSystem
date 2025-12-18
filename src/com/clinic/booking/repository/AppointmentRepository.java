package com.clinic.booking.repository;

import com.clinic.booking.model.Appointment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentRepository implements Repository<Appointment> {
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public void save(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment booked: " + appointment.getId());
    }

    @Override
    public Optional<Appointment> findById(String id) {
        return appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }

    @Override
    public void delete(String id) {
        appointments.removeIf(a -> a.getId().equals(id));
        System.out.println("Appointment cancelled: " + id);
    }
    
    // Additional method specific to appointments
    public List<Appointment> findByDoctorId(String doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getDoctor().getId().equals(doctorId)) {
                result.add(a);
            }
        }
        return result;
    }
}
