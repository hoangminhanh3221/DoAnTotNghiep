package com.shop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.entity.Product;

public interface AccountRepository  extends JpaRepository<Account, String>{
	@Query("SELECT c FROM Customer c JOIN c.account a WHERE a.username = :username")
    Customer findCustomerByUsername(@Param("username") String username);

//    @Query("SELECT a FROM Address a JOIN a.customers c WHERE c.customerId = :customerId")
//    Address findAddressByCustomerId(@Param("customerId") Integer customerId);

}
