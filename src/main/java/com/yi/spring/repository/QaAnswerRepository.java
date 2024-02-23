package com.yi.spring.repository;

import com.yi.spring.entity.QA;
import com.yi.spring.entity.QaAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QaAnswerRepository extends JpaRepository<QaAnswer, Integer> {


    Optional<QaAnswer> findByQaNo(int qa_num);



}
