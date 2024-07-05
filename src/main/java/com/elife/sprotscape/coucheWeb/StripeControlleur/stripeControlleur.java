package com.elife.sprotscape.coucheWeb.StripeControlleur;

import com.elife.sprotscape.Entities.checkout;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stripe")
@CrossOrigin(origins = "*")
public class stripeControlleur {
    @PostMapping("/payment")
    public Map<String, String> paymentWithCheckoutPage(@RequestBody checkout payment) throws StripeException {
        // We initilize stripe object with the api key
        Stripe.apiKey = "sk_test_51P0BS5JNaCnPHrEoPUWLgyH4kBlwXmdL489s08BjI95i4RDkiP6Sew5wAKSMrdvpxB6ThOIImwQ9zKjzg1UjFMZd00cTEmDjHk";
        // We create a  stripe session parameters
        SessionCreateParams params = SessionCreateParams.builder()
                // We will use the credit card payment method
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();
        // create a stripe session
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        // We get the sessionId and we putted inside the response data you can get more info from the session object
        responseData.put("id", session.getId().toString());
        // We can return only the sessionId as a String
        return responseData;
    }
}
