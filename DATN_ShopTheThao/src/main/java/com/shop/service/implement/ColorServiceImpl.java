package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Color;
import com.shop.repository.ColorRepository;
import com.shop.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{
	
	private final ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

	@Override
	public Page<Color> findAllColor(Pageable pageable) {
		return colorRepository.findAll(pageable);
	}

	@Override
	public List<Color> findAllColor() {
		return colorRepository.findAll();
	}

	@Override
	public Optional<Color> findColorById(String id) {
		return colorRepository.findById(id);
	}

	@Override
	public Color createColor(Color Color) {
		return colorRepository.save(Color);
	}

	@Override
	public Color updateColor(Color Color) {
		return colorRepository.save(Color);
	}

	@Override
	public void deleteColor(String id) {
		colorRepository.deleteById(id);

	}

	
    
}
