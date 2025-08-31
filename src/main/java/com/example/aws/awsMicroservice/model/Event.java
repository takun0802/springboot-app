package com.example.aws.awsMicroservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;  // ユーザー識別子

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;  // 種別（仕事 or プライベート）

    @Column(nullable = false)
    private LocalDate date;  // 日付

    @Column(name = "day_of_week")
    private String dayOfWeek; // 曜日

    @Column(name = "event_time")
    private LocalDateTime time;  // 時間（日時）

    private String location;  // 場所

    private String person;  // 人

    @Column(columnDefinition = "TEXT")
    private String memo;  // メモ

    @Column(nullable = true)
    private Integer priority;  // 優先度（例：1=低、2=中、3=高）

    // --- getter/setter ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getPerson() { return person; }
    public void setPerson(String person) { this.person = person; }

    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }
}
