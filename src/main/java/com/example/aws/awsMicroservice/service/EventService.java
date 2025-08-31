package com.example.aws.awsMicroservice.service;

import com.example.aws.awsMicroservice.model.Event;
import com.example.aws.awsMicroservice.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event createEvent(Event event) {
        return repository.save(event);
    }

    public List<Event> filterByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<Event> filterByDayOfWeek(String dayOfWeek) {
        return repository.findByDayOfWeek(dayOfWeek);
    }

    public List<Event> filterByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<Event> filterByDayAndCategory(String dayOfWeek, String category) {
        return repository.findByDayOfWeekAndCategory(dayOfWeek, category);
    }
}
