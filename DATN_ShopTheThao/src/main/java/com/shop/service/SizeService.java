package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Size;

public interface SizeService {

	Page<Size> findAllSize(Pageable pageable);

	List<Size> findAllSize();

	Optional<Size> findSizeById(String id);

	Size createSize(Size size);

	Size updateSize(Size size);

	void deleteSize(String id);

}
