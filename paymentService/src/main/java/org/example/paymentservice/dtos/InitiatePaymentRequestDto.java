package org.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDto {
    private String orderId ;
    private String phoneNumber ;
    private String emailId ;
    private Long amount ;


}
