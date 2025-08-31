package com.example.aws.awsMicroservice.controller;

import com.example.aws.awsMicroservice.model.Event;
import com.example.aws.awsMicroservice.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000") // React開発環境からのアクセス許可（必要に応じて変更）
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return service.createEvent(event);
    }

    @GetMapping("/filter")
    public List<Event> filterEvents(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String dayOfWeek,
            @RequestParam(required = false) String category
    ) {
        if (date != null) {
            return service.filterByDate(LocalDate.parse(date));
        } else if (dayOfWeek != null && category != null) {
            return service.filterByDayAndCategory(dayOfWeek, category);
        } else if (dayOfWeek != null) {
            return service.filterByDayOfWeek(dayOfWeek);
        } else if (category != null) {
            return service.filterByCategory(category);
        } else {
            return service.getAllEvents();
        }
    }
}
