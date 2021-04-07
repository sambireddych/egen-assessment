package com.egen.application.billingpaymentservice.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "PaymentDetails")
public class Paymentetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private Long id;
	@Column(name = "order_id")
	private String orderId;
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "name_on_card")
	private String nameOnCard;
	@Column(name = "card_number")
	@NotEmpty
	@Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-9]|3[0-9]){8}$")
	private String cardNumber;
	@Column(name = "cvv")
	@Max(999)
	private Integer cvv;
	@Column(name = "expire_date")
	@NotEmpty
	@Pattern(regexp = "^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$")
	private String expires;
	@Column(name = "payment_method")
	private PaymentMethod cardType;

	@Column(name = "payment_confirmation_number")
	@PayConfirm
	private String paymentConfirmationNumber;

}
