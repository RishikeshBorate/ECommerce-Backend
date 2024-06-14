package org.example.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.example.paymentservice.paymentGateways.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway ;

    public PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway ;
    }

    public String initiatePayment(String orderId,String phoneNumber , String emailId , Long amount) throws RazorpayException, StripeException {
        return paymentGateway.generatePaymentLink(orderId, phoneNumber, emailId, amount);

    }
}
