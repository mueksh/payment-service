package com.ecommerce.payment_service.model;

import com.ecommerce.payment_service.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    private String id;
    private String orderId;
    private String paymentMethod;
    private PaymentStatus status;
    private String transactionId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
