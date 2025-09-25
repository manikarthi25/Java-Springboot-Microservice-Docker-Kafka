package com.service.kafka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.kafka.constant.ErrorConstant;
import com.service.kafka.constant.MessageConstant;
import com.service.kafka.exception.AppException;
import com.service.kafka.model.PurchaseOrder;
import com.service.kafka.service.PurchaseOrderService;

@Service
public class PurchaseOrderConsumer {

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@KafkaListener(topics = "${kafka.producer.topic.name}", containerFactory = "kafkaListenerContainerFactory")
	public void listener(@Payload String message) throws AppException {

		try {
			PurchaseOrder purchaseOrder = objectMapper.readValue(message, PurchaseOrder.class);
			purchaseOrderService.savePO(purchaseOrder);
		} catch (JsonProcessingException e) {
			throw new AppException(ErrorConstant.APP_ERROR_03, MessageConstant.PAYLOAD_HANDLING_ERROR);
		}
	}

}
