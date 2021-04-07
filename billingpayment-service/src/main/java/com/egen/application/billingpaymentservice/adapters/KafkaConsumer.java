package com.egen.application.billingpaymentservice.adapters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {


    @KafkaListener(topics = "batch_orders_topic", groupId = "batch_orders_group")
    public void getPaymentDetails(String details){
        log.debug("payment details",details);
        log.info("saving payment details from batch transactions -- TODO");

    }


    @KafkaListener(topics = "batch_orders_topic", groupId = "batch_orders_group")
    public void getbillingddressDetails(String details){
        log.debug("billing address details",details);
        log.info("saving billing address details from batch transactions -- TODO");

    }



}
