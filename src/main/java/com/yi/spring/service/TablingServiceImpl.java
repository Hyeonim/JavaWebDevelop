package com.yi.spring.service;

import com.yi.spring.entity.TablingDto;
import com.yi.spring.repository.TablingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TablingServiceImpl implements TablingService {

    @Autowired
    private TablingRepository tablingRepository;

    @Override
    public List<TablingDto> getAlltabling() {
//        System.out.println(tablingRepository.findAll());
        return tablingRepository.findAll();
    }

    @Override
    public void addTabling(TablingDto dto) {
        tablingRepository.save(dto);
    }

    @Override
    public Optional<TablingDto> findByRestName(String restName) {
        return tablingRepository.findByRestName(restName);
    }

    @Override
    public TablingDto findByRestNo(int restNo) {
        return tablingRepository.findByRestNo(restNo);
    }

//    @Override
//    public void updateByRestNo(TablingDto dto) {
//
//        tablingRepository.updateByRestNo(dto);
//    }


}
