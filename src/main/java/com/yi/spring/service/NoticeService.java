package com.yi.spring.service;

import com.yi.spring.entity.Notice;
import com.yi.spring.entity.QA;
import com.yi.spring.entity.User;
import org.springframework.data.domain.Page;

public interface NoticeService {


    Page<Notice> findAll(int page);


    Page<Notice> findByAll(int page);


//    Page<Notice> findBySubjectContaining(String keyword, int page);
   Page<Notice> findBySubjectContaining(Boolean head,String keyword, int page);
   Page<Notice> findBySubjectContaining(String keyword, int page);

}
