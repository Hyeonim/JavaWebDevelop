package com.yi.spring.repository;

import com.yi.spring.entity.*;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface DinningRepository extends JpaRepository<Dinning, Long>, JpaSpecificationExecutor<Dinning> {

    List<Dinning> findAll();
    Optional<Dinning> deleteDinningByRestNo(int restNo);
    Optional<Dinning> findByRestNo(int restNo);
    Optional<Dinning> findByUserNo(User userNo);
    List<Dinning> findByRestNo(Long restNo);
    Page<Dinning> findAll(Pageable pageable);
    Page<Dinning> findByRestNameContainingIgnoreCase(String name, Pageable pageable);
    Optional<Dinning> findByUserNoAndRestStatusNot(User userNo, String restStatus);


    @Query("SELECT d FROM Dinning d WHERE d.restName LIKE %:keyword%")
    List<Dinning> findByRestNameContaining(@Param("keyword") String keyword);
    @Query(value = "SELECT * FROM rest_except_img_view d WHERE d.rest_name LIKE %:keyword%", nativeQuery = true)
    List<Object[]> findByRestNameContainingFromView(@Param("keyword") String keyword);
    @Query("select d from Dinning d where d.restImg is not null order by RAND() LIMIT :sLimit")
    List<Dinning> getRandomList(@Param("sLimit") String sLimit);
    @Query("SELECT d, COALESCE((SELECT COUNT(r) FROM Review r WHERE r.restNo.restNo = d.restNo), 0) AS totalReviews FROM Dinning d ORDER BY totalReviews DESC")
    List<Dinning> findAllWithTotalReviewsOrderByTotalReviewsDesc();

    @Query("SELECT d, COALESCE((SELECT COUNT(r) FROM Review r WHERE r.restNo.restNo = d.restNo), 0) AS totalReviews FROM Dinning d WHERE d.restName LIKE %:keyword% ORDER BY totalReviews DESC")
    List<Dinning> findAllWithTotalReviewsOrderByTotalReviewsDesc(@Param("keyword") String keyword);
//    @Query(value = "select d.rest_no, avg(r.rev_score) from dining_rest d left join review r on d.rest_no = r.rest_no group by d.rest_no order by avg(r.rev_score) desc", nativeQuery = true)
//    Map<Integer, Float> getRestScore();

    @Query("select d, COALESCE((SELECT avg(r.revScore) FROM Review r WHERE r.restNo.restNo = d.restNo), 0) AS restScore from Dinning d group by d.restNo order by restScore desc")
    List<Dinning> getRestScore();
    @Query("select d, COALESCE((SELECT avg(r.revScore) FROM Review r WHERE r.restNo.restNo = d.restNo), 0) AS restScore from Dinning d group by d.restNo order by restScore desc")
    List<Object[]> getRestScore2();
    @Query("SELECT d FROM Dinning d JOIN FETCH d.userNo u WHERE u.userAuth = '2'")
//    @Query("SELECT d FROM Dinning d inner join d.userNo u WHERE u.userAuth = '2'")
    List<Dinning> getAllByUserAuthIsOwner();


    // 폐점 관련
    Page<Dinning> findByRestNameAndRestStatus(String name, String status, Pageable pageable);

    Page<Dinning> findByRestStatus(String status, Pageable pageable);
}
