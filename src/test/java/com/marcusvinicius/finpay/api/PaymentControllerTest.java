package com.marcusvinicius.finpay.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcusvinicius.finpay.dto.PaymentRequest;
import com.marcusvinicius.finpay.dto.PaymentResponse;
import com.marcusvinicius.finpay.service.PaymentService;
import com.marcusvinicius.finpay.util.PaymentMethod;
import com.marcusvinicius.finpay.util.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PaymentService paymentService;

    private PaymentRequest request;
    private PaymentResponse response;

    @BeforeEach
    void setup() {
        request = new PaymentRequest(UUID.randomUUID(), new BigDecimal("100.00"), "R$", PaymentMethod.CREDIT_CARD);
        response = new PaymentResponse(UUID.randomUUID(), PaymentStatus.PENDING);
    }

    @Test
    void shouldReturnCreatedWithPaymentResponse() throws Exception {
        Mockito.when(paymentService.doPayment(request)).thenReturn(response);

        mockMvc.perform(post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.paymentId").value(response.getPaymentId().toString()))
                .andExpect(jsonPath("$.status").value(response.getStatus().name()));
    }
}