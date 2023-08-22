package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Feedback;
import com.shop.repository.FeedbackRepository;
import com.shop.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

    private final FeedbackRepository feedbackRepository;
    
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
		this.feedbackRepository = feedbackRepository;
	}

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
		feedbackRepository.deleteById(feedbackId);
	}
	
	@Override
	public List<Feedback> findFeedBacksByPrdId(String id) {
		// TODO Auto-generated method stub
		return feedbackRepository.getlistFeedbackByPrdId(id);
	}
}