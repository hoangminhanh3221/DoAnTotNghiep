package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Transport;

public interface TransportService {
	
	Page<Transport> findAllTransport(Pageable pageable);

	List<Transport> findAllTransport();

	Optional<Transport> findTransportById(Integer transportId);

	Transport createTransport(Transport transport);

	Transport updateTransport(Transport transport);

	void deleteTransport(Integer transportId);
	
}
