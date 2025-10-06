package com.marcusvinicius.finpay.api;

import com.marcusvinicius.finpay.dto.PaymentRequest;
import com.marcusvinicius.finpay.service.PaymentService;
import com.marcusvinicius.finpay.service.PaymentServiceImpl;
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

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<String> doPayment(@RequestBody PaymentRequest request) {
        return new ResponseEntity<>(paymentService.doPayment(), HttpStatus.CREATED);
    }
}
