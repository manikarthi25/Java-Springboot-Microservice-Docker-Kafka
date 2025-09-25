package com.service.kafka.service;

import com.service.kafka.model.PurchaseOrder;

public interface PurchaseOrderService {
	
	public PurchaseOrder savePO(PurchaseOrder purchaseOrder);

}
