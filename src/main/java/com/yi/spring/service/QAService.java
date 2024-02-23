package com.yi.spring.service;

import com.yi.spring.entity.QA;
import com.yi.spring.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QAService {

    Page<QA> findAll(int page);

    Page<QA> findByUserNoPaged(User user, int page);


    Page<QA> findByUserNoPaged(int page);


    int countByUserNo(User userNo);



}
