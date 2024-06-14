package org.example.paymentservice.paymentGateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class StripePaymentGateway implements PaymentGateway{

    @Value("${STRIPE_KEY_SECRET}")
    private String StripeKey ;
    @Override
    public String generatePaymentLink(String orderId,String phoneNumber , String emailId , Long amount) throws StripeException {

        Stripe.apiKey = StripeKey;
        /*
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice("price_1MoC3TLkdIwHu7ixcIbKelAC")
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();
          */

        Map<String , Object>priceData = new HashMap<>() ;
        priceData.put("unit_amount" , amount) ;
        priceData.put("currency" , "INR");

        Map<String ,Object> productData = new HashMap<>() ;
        productData.put("name" , "iPhone");

        priceData.put("product_data" , productData) ;

        Price price = Price.create(priceData) ;

        Map<String, Object> priceParam = new HashMap<>() ;
        priceParam.put("price" , price.getId()) ;
        priceParam.put("quantity",1) ;

        List<Object> lineItems = new ArrayList<>() ;
        lineItems.add(priceParam) ;

        Map<String , Object> afterPayment = new HashMap<>() ;
        afterPayment.put("type" , "redirect") ;

        Map<String , Object> redirect = new HashMap<>() ;
        redirect.put("url" , "https://scaler.com/");

        afterPayment.put("redirect" , redirect) ;

        Map<String , Object> params = new HashMap<>() ;
        params.put("line_items" , lineItems) ;
        params.put("after_completion" , afterPayment) ;

        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.getUrl();
    }
}
