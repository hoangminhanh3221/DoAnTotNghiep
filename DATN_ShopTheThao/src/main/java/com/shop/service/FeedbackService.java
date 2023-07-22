package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Feedback;

public interface FeedbackService{
	List<Feedback> findAllFeedbacks();
	
	Page<Feedback> findAllFeedbacks(Pageable pageable);
	
	Optional<Feedback> findFeedbackById(Integer feedbackId);
	
	Feedback createFeedback(Feedback feedback);
	
	Feedback updateFeedback(Feedback feedback);
	
	void deleteFeedback(Integer feedbackId);
<<<<<<< HEAD
=======
	
	List<Feedback> getListFeedbackByPrdId(String id);
		
>>>>>>> update-entity
}