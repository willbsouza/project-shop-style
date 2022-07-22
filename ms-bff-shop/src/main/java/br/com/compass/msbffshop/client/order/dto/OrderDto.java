package br.com.compass.msbffshop.client.order.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.compass.msbffshop.client.catalog.dto.SkuDto;
import br.com.compass.msbffshop.client.customer.dto.AddressDto;
import br.com.compass.msbffshop.client.customer.dto.CustomerDto;
import br.com.compass.msbffshop.client.entity.Installment;
import br.com.compass.msbffshop.client.enums.Status;
import br.com.compass.msbffshop.client.payment.dto.PaymentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDto {
	
	private String id;	
	private CustomerDto customer;
	private List<SkuDto> cart;
	private PaymentDto payment;
	private Installment installment;
	private Double total;
	private AddressDto address;
	private LocalDate date;
	private Status status;
}
