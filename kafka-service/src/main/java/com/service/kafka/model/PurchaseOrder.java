package com.service.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseOrder {

	private Integer poNumber;
	private String dcNumber;
	private String poStatus;

}
