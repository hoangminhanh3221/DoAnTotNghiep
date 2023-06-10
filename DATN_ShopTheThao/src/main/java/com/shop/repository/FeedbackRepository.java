package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Feedback;
import com.shop.entity.Product;

public interface FeedbackRepository  extends JpaRepository<Feedback, Integer>{

}
