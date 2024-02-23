package com.yi.spring.service;

import com.yi.spring.entity.QA;
import com.yi.spring.entity.User;
import com.yi.spring.repository.QARepository;
import com.yi.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QAServiceImpl implements QAService {

    @Autowired
    QARepository qaRepository;

    @Override
    public Page<QA> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.qaRepository.findAll(pageable);
    }

    @Override
    public Page<QA> findByUserNoPaged(User user, int page) {
        List<QA> userQAs = qaRepository.findByUserNo(user);
        int pageSize = 10;
        int start = page * pageSize;
        int end = Math.min((page + 1) * pageSize, userQAs.size());

        return new PageImpl<>(userQAs.subList(start, end), PageRequest.of(page, pageSize), userQAs.size());
    }

    @Override
    public Page<QA> findByUserNoPaged(int page) {
        List<QA> userQAs = qaRepository.findAll();
        int pageSize = 10;
        int start = page * pageSize;
        int end = Math.min((page + 1) * pageSize, userQAs.size());

        return new PageImpl<>(userQAs.subList(start, end), PageRequest.of(page, pageSize), userQAs.size());
    }

    @Override
    public int countByUserNo(User userNo) {
        return qaRepository.countByUserNo(userNo);
    }
}
