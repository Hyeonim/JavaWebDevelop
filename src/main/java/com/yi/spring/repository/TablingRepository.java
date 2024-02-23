package com.yi.spring.repository;

import com.yi.spring.entity.TablingDto;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TablingRepository extends JpaRepository<TablingDto, Integer> {

    Optional<TablingDto> findByRestName(String restName);
    TablingDto findByRestNo(int restNo);

//    @Query("UPDATE Tabling t SET t.restName = :restName, t.restTel = :restTel, t.restSeat = :restSeat, t.restTime = :restTime, t.restOffDays = :restOffDays, t.restParking = :restParking, t.restMenu = :restMenu, t.restAddr = :restAddr WHERE t.restNo = :restNo")
//    void updateByRestNo(TablingDto dto);

    @Query("SELECT d FROM Dinning d WHERE d.restName LIKE %:keyword% and d.restMenu")
    List<TablingDto> findByRestNameContaining(@Param("keyword") String keyword);




}
