package com.yi.spring.repository;

import com.yi.spring.entity.Reservation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByRestNo_RestNo(Long rest_no);

    List<Reservation> findByRestNo_RestNoAndUserNo_UserNo(Long restNo, Long user_no);
    List<Reservation> findByUserNo_UserNo(Long user_no);

    @Query("SELECT r FROM Reservation r WHERE r.userNo.userNo = :userNo ORDER BY r.resTime DESC")
    List<Reservation> findLatestReservationByUserNo(@Param("userNo") Long userNo, Pageable pageable);

//    @Query("SELECT dr.restName, dr.restCategory, r.reservationTime, r.guestCount, dr.restAddr " +
//            "FROM Reservation r " +
//            "JOIN r.diningRest dr " +
//            "WHERE r.userNo = :userNo")
//    List<Object[]> findReservationDetailsByUserNo(Long userNo);


    /*
    Query("SELECT dr.restName, dr.restCategory, r.reservationTime, r.guestCount, dr.restAddr " +
            "FROM Reservation r " +
            "JOIN DiningRest dr ON r.restNo = dr.restNo " +
            "WHERE r.userNo = :userNo")
    */
    @Query("SELECT r "+
//            .restNo.restName, r.restNo.restCategory, r.resTime, r.res_guest_count, r.restNo.restAddr " +
            "FROM Reservation r " +
            "WHERE r.userNo.userNo = :userNo")
    List<Reservation> findReservationDetailsByUserNo(Long userNo);

    @Query(value = "select * from reservation r where DATE(r.res_time) = CURDATE() and r.rest_no = :restNo order by r.res_time asc", nativeQuery = true)
    List<Reservation> getTodayReservation(Long restNo);

    @Query(value = "select * from reservation r where DATE(r.res_time) > CURDATE() and r.rest_no = :restNo order by r.res_time asc", nativeQuery = true)
    List<Reservation> getWaitReservation(Long restNo);

    @Query(value="select * from reservation r where DATE(r.res_time) < CURDATE() and r.rest_no = :restNo order by r.res_time desc", nativeQuery = true)
    List<Reservation> getPastReservation(Long restNo);

    @Query(value = "SELECT r.res_no, res_time, user_no, res_guest_count, res_table_type, res_comment, res_status, " +
            " res_rejection_reason, res_time_new, rest_no , " +
            "CASE " +
            "    WHEN DATE(r.res_time) < CURDATE() THEN -1 " +
            "    WHEN DATE(r.res_time) = CURDATE() THEN 0 " +
            "    ELSE 1 " +
            "END AS datetype " +
            "FROM reservation r " +
            "WHERE r.rest_no = :restNo", nativeQuery = true)
    List<Reservation> getReservationWithDateType333(Long restNo);
    @Query(value = "SELECT r.* " +
//            ", CASE " +
//            "    WHEN DATE(r.res_time) < CURDATE() THEN -1 " +
//            "    WHEN DATE(r.res_time) = CURDATE() THEN 0 " +
//            "    ELSE 1 " +
//            "END AS datetype " +
            "FROM reservation r " +
            "WHERE r.rest_no = :restNo  order by r.res_time asc ", nativeQuery = true)
    List<Reservation> getReservationWithDateType(Long restNo);
    @Query(value = "SELECT r.*, " +
            "CASE " +
            "    WHEN DATE(r.res_time) < CURDATE() THEN -1 " +
            "    WHEN DATE(r.res_time) = CURDATE() THEN 0 " +
            "    ELSE 1 " +
            "END AS dateType " +
            "FROM reservation r " +
            "WHERE r.rest_no = :restNo", nativeQuery = true)
    List<Object[]>  getReservationWithDateType222(Long restNo);


//    List<Reservation> findByRestNo_RestNameAndRestNo_RestCategoryAndResTimeAndRes_guest_countAndRestNo_RestAddr(String restName, String restCategory, LocalDateTime resTime, String res_guest_count, String restAddr);

    @Modifying
    @Transactional
    @Query(value = "UPDATE reservation r INNER JOIN review rv ON r.res_no = rv.res_no SET r.res_status = 'REVIEW' WHERE r.res_no = rv.res_no", nativeQuery = true)
    int updateReservationStatusToReviewWithJoin();

}
