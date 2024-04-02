package com.yogith.tickets.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.yogith.tickets.dto.TrainAppRepository;
import com.yogith.tickets.entity.Receipt;

public class ReceiptServiceTest {

	@Mock
	private TrainAppRepository trainAppRepository;

	private ReceiptService receiptService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		receiptService = new ReceiptService();
		receiptService.trainAppRepository = trainAppRepository;
	}

	@Test
	public void testGetReceiptByUserId() {
		long userId = 123456L;
		Receipt expectedReceipt = new Receipt(null); 

		Mockito.when(trainAppRepository.getReceiptByUserId(userId)).thenReturn(expectedReceipt);

		Receipt result = receiptService.getReceiptByUserId(userId);

		assertEquals(expectedReceipt, result);

		Mockito.verify(trainAppRepository, Mockito.times(1)).getReceiptByUserId(userId);
	}

}