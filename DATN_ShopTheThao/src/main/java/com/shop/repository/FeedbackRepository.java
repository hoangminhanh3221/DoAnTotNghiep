package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Feedback;

public interface FeedbackRepository  extends JpaRepository<Feedback, Integer>{

		
	@Query(value="select * from Feedback where ProductId =?", nativeQuery=true)
    List<Feedback> getlistFeedbackByPrdId(String id);
}
