package com.yi.spring.repository;

import com.yi.spring.entity.DeleteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteUserRepository extends JpaRepository<DeleteUser, Integer> {
}
