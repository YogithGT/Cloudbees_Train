package com.yogith.tickets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogith.tickets.dto.TrainAppRepository;
import com.yogith.tickets.entity.Receipt;

@Service
public class ReceiptService {

	@Autowired
	TrainAppRepository trainAppRepository;

	public Receipt getReceiptByUserId(Long userId) {
		return trainAppRepository.getReceiptByUserId(userId);
	}
}
