package com.healthcare.system.controller;

import com.healthcare.system.entity.Doctor;
import com.healthcare.system.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository repo;

    public DoctorController(DoctorRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Doctor create(@RequestBody Doctor d) {
        return repo.save(d);
    }

    @GetMapping
    public List<Doctor> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Doctor getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor data) {
        return repo.findById(id)
                .map(d -> {
                    d.setName(data.getName());
                    d.setSpecialization(data.getSpecialization());
                    return repo.save(d);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
