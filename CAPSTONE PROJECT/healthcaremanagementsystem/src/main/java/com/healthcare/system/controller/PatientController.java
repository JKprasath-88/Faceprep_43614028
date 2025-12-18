package com.healthcare.system.controller;

import com.healthcare.system.entity.Patient;
import com.healthcare.system.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Patient create(@RequestBody Patient p) {
        return repo.save(p);
    }

    @GetMapping
    public List<Patient> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Patient getOne(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient data) {
        return repo.findById(id)
                .map(p -> {
                    p.setName(data.getName());
                    p.setAge(data.getAge());
                    return repo.save(p);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
