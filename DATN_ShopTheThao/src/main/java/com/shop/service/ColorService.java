package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Color;

public interface ColorService {
	Page<Color> findAllColor(Pageable pageable);
	
	List<Color> findAllColor();

	Optional<Color> findColorById(String id);
	
	Color createColor(Color Color);

	Color updateColor(Color Color);

	void deleteColor(String id);
}
