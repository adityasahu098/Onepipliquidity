package com.exml.Onepipliquidity1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exml.Onepipliquidity1.model.Bonus;
import com.exml.Onepipliquidity1.model.User_model;

public interface BonusRepository extends JpaRepository<Bonus, Long>{
	  List<Bonus> findByUser(User_model user);
}
