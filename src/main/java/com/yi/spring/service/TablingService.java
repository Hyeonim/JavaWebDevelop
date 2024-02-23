package com.yi.spring.service;

import com.yi.spring.entity.TablingDto;

import java.util.List;
import java.util.Optional;

public interface TablingService {

    List<TablingDto> getAlltabling();
    void addTabling(TablingDto dto);
    Optional<TablingDto> findByRestName(String restName);
    TablingDto findByRestNo(int restNo);
//    void updateByRestNo(TablingDto dto);

}
