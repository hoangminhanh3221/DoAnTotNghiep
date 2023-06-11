package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Transport;
import com.shop.repository.SizeRepository;
import com.shop.repository.TransportRepository;
import com.shop.service.TransportService;

@Service
public class TransportServiceImpl implements TransportService {

	private final TransportRepository transportRepository;

	public TransportServiceImpl(TransportRepository transportRepository) {
		this.transportRepository = transportRepository;
	}
	
	@Override
	public Page<Transport> findAllTransport(Pageable pageable) {
		return transportRepository.findAll(pageable);
	}

	@Override
	public List<Transport> findAllTransport() {
		return transportRepository.findAll();
	}

	@Override
	public Optional<Transport> findTransportById(Integer id) {
		return transportRepository.findById(id);
	}

	@Override
	public Transport createTransport(Transport transport) {
		return transportRepository.save(transport);
	}

	@Override
	public Transport updateTransport(Transport transport) {
		return transportRepository.save(transport);
	}

	@Override
	public void deleteTransport(Integer id) {
		transportRepository.deleteById(id);
	}

}
