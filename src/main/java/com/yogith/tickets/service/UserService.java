package com.yogith.tickets.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogith.tickets.dto.TrainAppRepository;
import com.yogith.tickets.entity.User;

@Service
public class UserService {

	public com.yogith.tickets.dto.TrainAppRepository trainAppRepository;
	@Autowired
	TrainAppRepository  TrainAppRepository ;

	public Map<User, String> getUsersAndSeats() {
		return TrainAppRepository .getUsersAndSeats();
	}

	public boolean removeUser(Long userId) {
		return TrainAppRepository .removeUser(userId);
	}
}
