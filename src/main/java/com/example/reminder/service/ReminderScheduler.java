package com.example.reminder.service;

import com.example.reminder.model.Reminder;
import com.example.reminder.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderScheduler {

    @Autowired
    private ReminderRepository repository;

    // Runs every 60 seconds
    @Scheduled(fixedRate = 60000)
    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime soon = now.plusMinutes(5); // upcoming 5 minutes

        List<Reminder> reminders = repository.findAll();
        for (Reminder reminder : reminders) {
            LocalDateTime reminderTime = reminder.getDateTime();
            if (reminderTime.isAfter(now) && reminderTime.isBefore(soon)) {
                System.out.println("🔔 Upcoming Reminder: " + reminder.getTitle()
                        + " at " + reminder.getDateTime());
            }
        }
    }
}
