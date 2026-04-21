package com.ecommerce.payment_service.dto;

import com.ecommerce.payment_service.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String id;
    private Long orderId;
    private String paymentMethod;
    private PaymentStatus status;
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
