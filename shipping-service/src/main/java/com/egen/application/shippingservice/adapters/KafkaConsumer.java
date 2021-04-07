package com.egen.application.shippingservice.adapters;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component
public class KafkaConsumer {


    @KafkaListener(topics = "batch_orders_topic", groupId = "batch_orders_group")
    public void getshippingDetils(String details){
        log.debug("order details",details);
        log.info("saving shipping details from batch transactions -- TODO");

    }



}
