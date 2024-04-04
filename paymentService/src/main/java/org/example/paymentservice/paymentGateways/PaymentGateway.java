package org.example.paymentservice.paymentGateways;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(String orderId,String phoneNumber , String emailId , Long amount) throws RazorpayException, StripeException;
}
