package com.ecommerce.payment_service.service.impl;

import com.ecommerce.payment_service.dto.PaymentRequest;
import com.ecommerce.payment_service.dto.PaymentResponse;
import com.ecommerce.payment_service.exception.PaymentNotFoundException;
import com.ecommerce.payment_service.mapper.PaymentMapper;
import com.ecommerce.payment_service.model.Payment;
import com.ecommerce.payment_service.model.enums.PaymentStatus;
import com.ecommerce.payment_service.repository.PaymentRepository;
import com.ecommerce.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        log.info("Processing payment for orderId: {}", paymentRequest.getOrderId());
        Payment payment = paymentMapper.toEntity(paymentRequest);
        payment.setStatus(PaymentStatus.COMPLETED); // Mocking payment completion
        payment = paymentRepository.save(payment);
        log.info("Payment processed successfully for orderId: {}", paymentRequest.getOrderId());
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentByOrderId(Long orderId) {
        log.info("Fetching payment for orderId: {}", orderId);
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found for orderId: " + orderId));
        return paymentMapper.toPaymentResponse(payment);
    }

    @Override
    public PaymentResponse getPaymentById(String paymentId) {
        log.info("Fetching payment for paymentId: {}", paymentId);
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found for paymentId: " + paymentId));
        return paymentMapper.toPaymentResponse(payment);
    }
}
