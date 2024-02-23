package com.yi.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no", nullable = false)
    private Integer userNo;

    @Column(name = "user_id", length = 30)
    private String userId;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "user_password", length = 50)
    private String userPassword;

    @Column(name = "user_email", length = 50)
    private String userEmail;

    @Column(name = "user_tel", length = 100)
    private String userTel;

    @Column(name = "user_auth", length = 100)
    private String userAuth;

    @Column(name = "user_start_date", length = 100)
    private String userStartDate= String.valueOf(LocalDateTime.now());

    @Lob
    @Column(name = "user_img" ,length = 10000)
    private byte[] userImg;

    @Column(name = "user_block")
    private boolean userBlock = false;


    @OneToMany(mappedBy = "userNo")
    @ToString.Exclude
    private Set<Dinning> diningRests = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userNo")
    @ToString.Exclude
    private Set<Review> reviews = new LinkedHashSet<>();

    public String getBase64Image() {
        if (userImg != null && userImg.length > 0) {
            return Base64.getEncoder().encodeToString(userImg);
        }
        return "";
    }

}