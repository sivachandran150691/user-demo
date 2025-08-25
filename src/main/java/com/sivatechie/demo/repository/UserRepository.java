package com.sivatechie.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sivatechie.demo.model.User;



public interface UserRepository extends JpaRepository<User, Long> {
}
	