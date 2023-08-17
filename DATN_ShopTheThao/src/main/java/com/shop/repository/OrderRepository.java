package com.shop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	Page<Order> findAllByOrderStatus(String orderStatus, Pageable pageable);
	
	@Query(value="SELECT * FROM Orders WHERE YEAR(createDate) = :year", nativeQuery=true)
    List<Order> getOrderByYear(@Param("year") int year);
	
	@Query(value="SELECT * FROM Orders WHERE DATEPART(month, createDate) = :monthInput AND YEAR(createDate) = :year", nativeQuery=true)
    List<Order> getOrderByMonth(int monthInput, int year);
	
	@Query(value="SELECT DISTINCT YEAR(createDate) AS createDate FROM Orders", nativeQuery=true)
    List<Integer> getYear();

	@Query("SELECT o FROM Order o WHERE o.createDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersInDateRange(Date startDate, Date endDate);
	
	@Query("SELECT o FROM Order o WHERE o.account.username = :userName")
    List<Order> findOrdersByUserName(String userName);
}

