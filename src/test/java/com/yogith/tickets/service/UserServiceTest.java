package com.yogith.tickets.service;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.yogith.tickets.dto.TrainAppRepository;
import com.yogith.tickets.entity.User;

public class UserServiceTest {

	@Mock
    private TrainAppRepository trainAppRepository;

    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService();
        userService.trainAppRepository = trainAppRepository;
    }
	
    @Test
    public void testGetUsersAndSeats() {
        Map<User, String> expectedMap = new HashMap<>();
        expectedMap.put(new User("John", "Doe", "john.doe@example.com"), "A1");
        expectedMap.put(new User("Jane", "Doe", "jane.doe@example.com"), "B2");

        Mockito.when(trainAppRepository.getUsersAndSeats()).thenReturn(expectedMap);

        Map<User, String> result = userService.getUsersAndSeats();

        assertEquals(expectedMap.size(), result.size());
        assertEquals(expectedMap, result);

        Mockito.verify(trainAppRepository,  Mockito.times(1)).getUsersAndSeats();
    }

    @Test
    public void testRemoveUser() {
        long userId = 123456L;

        Mockito.when(trainAppRepository.removeUser(userId)).thenReturn(true);

        boolean result = userService.removeUser(userId);

        assertEquals(true, result);

        Mockito.verify(trainAppRepository,  Mockito.times(1)).removeUser(userId);
    }

}