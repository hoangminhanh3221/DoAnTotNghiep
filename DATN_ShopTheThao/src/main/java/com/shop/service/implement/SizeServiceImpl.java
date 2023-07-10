package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Size;
import com.shop.repository.SizeRepository;
import com.shop.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService {
	
	private final SizeRepository sizeRepository;

	public SizeServiceImpl(SizeRepository sizeRepository) {
		this.sizeRepository = sizeRepository;
	}

	@Override
	public Page<Size> findAllSize(Pageable pageable) {
		return sizeRepository.findAll(pageable);
	}

	@Override
	public List<Size> findAllSize() {
		return sizeRepository.findAll();
	}

	@Override
	public Optional<Size> findSizeById(String id) {
		return sizeRepository.findById(id);
	}

	@Override
	public Size createSize(Size size) {
		return sizeRepository.save(size);
	}

	@Override
	public Size updateSize(Size size) {
		return sizeRepository.save(size);
	}

	@Override
	public void deleteSize(String id) {
		sizeRepository.deleteById(id);

	}

}
