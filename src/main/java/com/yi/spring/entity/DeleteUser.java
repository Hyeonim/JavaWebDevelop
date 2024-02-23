package com.yi.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "delete_user")
public class DeleteUser {
    @Id
    @Column(name = "user_no", nullable = false)
    private Integer userNo;

    @Column(name = "user_id", length = 30)
    private String userId;

    @Column(name = "user_auth", length = 100)
    private String userAuth;

    @Column(name = "user_start_date", length = 100)
    private String userStartDate;

    @Column(name = "user_block")
    private Boolean userBlock;

    public DeleteUser(Integer userNo, String userId, String userAuth, String userStartDate, boolean userBlock) {
        this.userNo = userNo;
        this.userId = userId;
        this.userAuth = userAuth;
        this.userStartDate = userStartDate;
        this.userBlock = userBlock;
    }

    public DeleteUser() {

    }
}