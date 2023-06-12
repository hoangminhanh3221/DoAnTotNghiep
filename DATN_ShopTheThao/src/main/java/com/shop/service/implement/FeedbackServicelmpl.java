package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Feedback;
import com.shop.repository.FeedbackRepository;
import com.shop.service.FeedbackService;

public class FeedbackServicelmpl implements FeedbackService{
	@Autowired
    private FeedbackRepository feedbackRepository;
	
    @Override
    public Page<Feedback> findAllFeedbacks(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }
    
    @Override
    public List<Feedback> findAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Optional<Feedback> findFeedbackById(Integer feedbackId) {
        return feedbackRepository.findById(feedbackId);
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }


	@Override
	public void deleteFeedback(Integer feedbackId) {
		// TODO Auto-generated method stub
		
	}
}