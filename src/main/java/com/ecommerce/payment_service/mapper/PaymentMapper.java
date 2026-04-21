package com.ecommerce.payment_service.mapper;

import com.ecommerce.payment_service.dto.PaymentRequest;
import com.ecommerce.payment_service.dto.PaymentResponse;
import com.ecommerce.payment_service.model.Payment;
import com.ecommerce.payment_service.model.enums.PaymentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentRequest paymentRequest) {
        return Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .amount(paymentRequest.getAmount())
                .status(PaymentStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .transactionId(UUID.randomUUID().toString())
                .build();
    }

    public PaymentResponse toPaymentResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .status(payment.getStatus())
                .paymentMethod(payment.getPaymentMethod())
                .transactionId(payment.getTransactionId())
                .amount(payment.getAmount())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
