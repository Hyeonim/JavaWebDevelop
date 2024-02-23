package com.yi.spring.service;

import com.yi.spring.entity.Notice;
import com.yi.spring.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository noticeRepository;


    @Override
    public Page<Notice> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);

        return this.noticeRepository.findByImportantNotice(false, pageable);
    }

    @Override
    public Page<Notice> findByAll(int page) {
        List<Notice> userQAs = noticeRepository.findByImportantNotice(false);
        int pageSize = 10;
        int start = page * pageSize;
        int end = Math.min((page + 1) * pageSize, userQAs.size());

        return new PageImpl<>(userQAs.subList(start, end), PageRequest.of(page, pageSize), userQAs.size());
    }

//    @Override
//    public Page<Notice> findAll(int page) {
//        Pageable pageable = PageRequest.of(page, 10);
//
//        return this.noticeRepository.findAll(pageable);
//    }

//    @Override
//    public Page<Notice> findByAll(int page) {
//        List<Notice> userQAs = noticeRepository.findAll();
//        int pageSize = 10;
//        int start = page * pageSize;
//        int end = Math.min((page + 1) * pageSize, userQAs.size());
//
//        return new PageImpl<>(userQAs.subList(start, end), PageRequest.of(page, pageSize), userQAs.size());
//    }





    @Override
    public Page<Notice> findBySubjectContaining(Boolean head,String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);

        return noticeRepository.findByImportantNoticeAndSubjectContaining(head,keyword, pageable);
    }

    @Override
    public Page<Notice> findBySubjectContaining(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10);

        return noticeRepository.findBySubjectContaining(keyword, pageable);
    }
}
