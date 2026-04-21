package com.ecommerce.payment_service.controller;

import com.ecommerce.payment_service.dto.PaymentRequest;
import com.ecommerce.payment_service.dto.PaymentResponse;
import com.ecommerce.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Processes a new payment request.
     */
    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        log.info("Received request to process payment for orderId: {}", paymentRequest.getOrderId());
        PaymentResponse response = paymentService.processPayment(paymentRequest);
        log.info("Payment processed successfully for orderId: {}. Status: {}", paymentRequest.getOrderId(), response.getStatus());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves payment details associated with a specific order ID.
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentByOrderId(@PathVariable Long orderId) {
        log.info("Received request to fetch payment for orderId: {}", orderId);
        PaymentResponse response = paymentService.getPaymentByOrderId(orderId);
        log.debug("Found payment details for orderId: {}", orderId);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves payment details by the unique payment transaction ID.
     */
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable String paymentId) {
        log.info("Received request to fetch payment for paymentId: {}", paymentId);
        PaymentResponse response = paymentService.getPaymentById(paymentId);
        log.debug("Found payment details for paymentId: {}", paymentId);
        return ResponseEntity.ok(response);
    }
}
