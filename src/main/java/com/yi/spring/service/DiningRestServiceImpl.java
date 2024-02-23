package com.yi.spring.service;

import com.yi.spring.entity.Dinning;
import com.yi.spring.entity.DinningStatus;
import com.yi.spring.entity.User;
import com.yi.spring.repository.DinningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningRestServiceImpl implements DiningRestService{
    @Autowired
    private DinningRepository dinningRepository;



    @Override
    public List<Dinning> getAllRestaurants() {
        return dinningRepository.findAll();
    }

    @Override
    public Dinning getRestByRestNo(long restNo) {
        Optional<Dinning> optionalDiningRest = dinningRepository.findById(restNo);
        return optionalDiningRest.orElse(null);
    }

    @Override
    public void createRestaurant(Dinning dinning) {
        dinningRepository.save(dinning);
    }

    @Override
    public Dinning updateRestaurant(Dinning dinning) {
//        DiningRest existingDiningRest = diningRestRepository.findById(diningRest.getRest_no()).orElse(null);
//        assert existingDiningRest != null;
//        existingDiningRest.setRest_name(diningRest.getRest_name());
        return dinningRepository.save(dinning);
    }

    @Override
    public void deleteRestaurant(long restNo) {
        dinningRepository.deleteById(restNo);
    }

    @Override
    public Dinning deleteApply(long restNo) {
        Dinning dinning = dinningRepository.findById(restNo).get();
        dinning.setRestStatus(String.valueOf(DinningStatus.WAIT));
        dinningRepository.save(dinning);
        return dinning;
    }

    @Override
    public Dinning getByUserNo(User userNo) {
        Optional<Dinning> optionalDiningRest = dinningRepository.findByUserNoAndRestStatusNot(userNo, "CLOSED");
        return optionalDiningRest.orElse(null);
    }




}
