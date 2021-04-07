//package com.egen.application.billingpaymentservice.events;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//
//public class PaymentEventsListener {
////    private final OrderService orderService;
//
//    @KafkaListener(topics = ORDER_PAYMENT_CONFIRM_TOPIC)
//    public void confirmPayment(@Payload Object paymentConfirmationEvent) {
//        PaymentConfirmationEvent event = (PaymentConfirmationEvent) paymentConfirmationEvent;
////        log.info("received payment confirmation event for order {}", event.getOrderId());
//        orderService.updateStatus(event.getOrderId(), PAYED_PREPARING_FOR_SHIPMENT)
//                .doOnNext(orderService::dispatch)
//                .subscribe();
//    }
//
//    @KafkaListener(topics = ORDER_PAYMENT_REJECT_TOPIC)
//    public void rejectPayment(@Payload Object paymentRejectionEvent) {
//        PaymentRejectionEvent event = (PaymentRejectionEvent) paymentRejectionEvent;
////        log.info("received payment rejection event for order {}: {}", event.getOrderId(), event.getMessage());
//        orderService.updateStatus(event.getOrderId(), CANCELLED_PAYMENT_REJECTED)
//                .doOnNext(orderService::releaseStock)
//                .subscribe();
//    }
//}
