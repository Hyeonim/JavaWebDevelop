package com.yi.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.yi.spring.entity.TablingDto}
 */
@Entity
@Table(name = "dining_rest_my")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TablingDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restNo;


    private String restName;
    private String restAddr;
    private String restTel;
    private String restSeat;
    private String restTime;
    private String restOffDays;
    private String restParking;
    private String restMenu;
    private String restCategory;
    private BigDecimal restLatitude;
    private BigDecimal restLongitude;
    private byte[] restImg;
    private Float restScore;
    private String restDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    private String restStartDate;
    private String restStatus;

}