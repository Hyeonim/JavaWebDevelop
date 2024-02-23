package com.yi.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Base64;

@Getter
@Setter
@Entity
@ToString
@Table(name = "dining_rest")
public class Dinning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rest_no", nullable = false)
    private int restNo;

    @Column(name = "rest_name")
    private String restName;

    @Column(name = "rest_addr")
    private String restAddr;

    @Column(name = "rest_tel", length = 20)
    private String restTel;

    @Column(name = "rest_seat", length = 50)
    private String restSeat;

    @Column(name = "rest_time")
    private String restTime;

    private transient DinningTimeMan timeManager;
    public void updateDinningTime(){
        if ( null == restTime )
            return;

    }

    @Column(name = "rest_off_days")
    private String restOffDays;

    @Column(name = "rest_parking")
    private String restParking;

    @Lob
    @Column(name = "rest_menu")
    private String restMenu;

    @Column(name = "rest_category")
    private String restCategory;

    @Column(name = "rest_latitude")
    private Double restLatitude;

    @Column(name = "rest_longitude")
    private Double restLongitude;

//    @ToString.Exclude
    @Column(name = "rest_img")
    private String restImg;
    @Transient
    private ImageMan restImgMan = new ImageMan( restImg );

    @Transient
    private Double restScore;

    @Column(name = "rest_description", length = 100)
    private String restDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    @Column(name = "rest_start_date", length = 100)
    private String restStartDate;

    @Column(name = "rest_status", length = 100)
    private String restStatus;


    @Transient
    private int totalReviews;

    public Dinning(){}
    public Dinning( int restNo )
    {
        this.restNo = restNo;
    }

    // 이미지를 Base64 문자열로 변환하는 메서드
    public String getBase64Image() {
//        if (restImg != null && restImg.length > 0) {
//            return Base64.getEncoder().encodeToString(restImg);
//        }
        return restImg;
    }

    public DinningStatus getRestStatusEnum() {
        try {
            return DinningStatus.valueOf(restStatus);
        } catch (Exception e) {
            // 예외 처리: 디비에 저장된 값이 Enum에 존재하지 않을 경우
            return DinningStatus.NORMAL; // 또는 다른 기본값 설정
        }
    }
}