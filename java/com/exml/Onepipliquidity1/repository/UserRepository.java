package com.exml.Onepipliquidity1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exml.Onepipliquidity1.model.User_model;



public interface UserRepository extends JpaRepository<User_model, Integer> {
	
	 Optional<User_model> findByEmail(String email);
	
	
//	  Optional<User_model> findByResetToken(String resetToken);
	
	 Optional<User_model> findByEmailIgnoreCase(String email);
	 
	 

}
