package com.example.aws.awsMicroservice.repository;

import com.example.aws.awsMicroservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDate(LocalDate date);

    List<Event> findByDayOfWeek(String dayOfWeek);

    List<Event> findByCategory(String category);

    List<Event> findByDayOfWeekAndCategory(String dayOfWeek, String category);
}
