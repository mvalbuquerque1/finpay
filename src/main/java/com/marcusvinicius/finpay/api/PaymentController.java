package com.marcusvinicius.finpay.api;

import com.marcusvinicius.finpay.dto.PaymentRequest;
import com.marcusvinicius.finpay.dto.PaymentResponse;
import com.marcusvinicius.finpay.service.PaymentService;
import com.marcusvinicius.finpay.service.PaymentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> doPayment(@Valid @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.doPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
