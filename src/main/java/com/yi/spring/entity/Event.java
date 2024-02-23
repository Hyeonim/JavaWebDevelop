package com.yi.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Base64;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_no", nullable = false)
    private int eventNo;

    @Column(name = "event_title", length = 100)
    private String eventTitle;

    @Column(name = "event_content", length = 100)
    private String eventContent;

    @Column(name = "event_img")
    private byte[] eventImg;

    @Column(name = "event_start_time")
    private LocalDate eventStartTime;

    @Column(name = "event_end_time")
    private LocalDate eventEndTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_no")
    private Dinning restNo;

    @Column(name = "write_date")
    private String writeDate = String.valueOf(LocalDateTime.now());

    public String getBase64Image() {
        if (eventImg != null && eventImg.length > 0) {
            return Base64.getEncoder().encodeToString(eventImg);
        }
        return "";
    }
}