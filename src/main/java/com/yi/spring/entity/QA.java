package com.yi.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "QA")
public class QA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qa_no", nullable = false)
    private Integer id;

    @Column(name = "qa_title", length = 100)
    private String qaTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    @Column(name = "qa_content", length = 100)
    private String qaContent;

    @Column(name = "qa_write_time")
    private LocalDateTime qaWriteTime;

    @Column(name = "qa_status")
    private boolean qaStatus = false;

}