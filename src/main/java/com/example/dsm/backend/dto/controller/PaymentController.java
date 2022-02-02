package com.example.dsm.backend.dto.controller;

import com.example.dsm.backend.dto.AmountDTO;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
@RestController
@RequestMapping("/stripe")
@CrossOrigin(origins = "*")
public class PaymentController {

    static class CreatePaymentResponse {
        private String clientSecret;
        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }
    private static Gson gson = new Gson();

    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> payment(@RequestBody AmountDTO amount) throws StripeException {
        // This is your test secret API key.
        Stripe.apiKey = "sk_test_51KM3QZG6zQNEntYYWbH8uBAab5Lad1TuCGgonvzUU83r5bhPkhvJS38WRPcSbQfhnO2bI7WlXIiO6gjE3QGRgmGF00BTBt1qbm";
        System.out.println(amount);
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount.getTotalAmount())
                        .setCurrency("pen")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();
        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
        return new ResponseEntity<String>(gson.toJson(paymentResponse), HttpStatus.OK);
    }

    @GetMapping("/prueba")
    @ResponseBody
    public String prueba(){
        return "Esto es una prueba";
    }
}
