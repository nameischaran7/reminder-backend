package com.example.reminder.controller;

import com.example.reminder.model.Reminder;
import com.example.reminder.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderRepository repository;

    // CREATE
    @PostMapping
    public Reminder addReminder(@RequestBody Reminder reminder) {
        return repository.save(reminder);
    }

    // READ ALL
    @GetMapping
    public List<Reminder> getAllReminders() {
        return repository.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Reminder getReminderById(@PathVariable Long id) {
        Optional<Reminder> reminder = repository.findById(id);
        return reminder.orElseThrow(() -> new RuntimeException("Reminder not found with ID: " + id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public Reminder updateReminder(@PathVariable Long id, @RequestBody Reminder newData) {
        return repository.findById(id).map(reminder -> {
            reminder.setTitle(newData.getTitle());
            reminder.setDescription(newData.getDescription());
            reminder.setDateTime(newData.getDateTime());
            return repository.save(reminder);
        }).orElseThrow(() -> new RuntimeException("Reminder not found with ID: " + id));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteReminder(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Reminder not found with ID: " + id);
        }
        repository.deleteById(id);
        return "Reminder deleted successfully with ID: " + id;
    }
}
