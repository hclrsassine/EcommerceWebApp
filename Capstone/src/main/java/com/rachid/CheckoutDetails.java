package com.rachid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDetails {
	private float amount;
    private int invoiceNumber;
    private String date;
    private String OrderDescription;
    private Long orderId;
}
