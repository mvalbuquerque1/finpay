package com.marcusvinicius.finpay.api;

import com.marcusvinicius.finpay.dto.PaymentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @PostMapping
    public ResponseEntity<HttpStatus> doPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
