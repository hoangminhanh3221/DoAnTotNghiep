package com.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.entity.Size;

public interface SizeRepository  extends JpaRepository<Size, String>{

}
