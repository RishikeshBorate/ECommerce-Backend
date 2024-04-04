package org.example.paymentservice.controllers;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.example.paymentservice.dtos.InitiatePaymentRequestDto;
import org.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payments")
public class PaymentController {

    private PaymentService paymentService ;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService ;
    }

    @PostMapping("/initiate")
    public String initialisePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws RazorpayException, StripeException {
        return paymentService.initiatePayment(
                requestDto.getOrderId() ,
                requestDto.getPhoneNumber() ,
                requestDto.getEmailId(),
                requestDto.getAmount()
        ) ;

    }
}
