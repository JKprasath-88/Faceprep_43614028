package com.healthcare.system.controller;

import com.healthcare.system.entity.Appointment;
import com.healthcare.system.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository repo;

    public AppointmentController(AppointmentRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Appointment create(@RequestBody Appointment a) {
        return repo.save(a);
    }

    @GetMapping
    public List<Appointment> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Appointment getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
