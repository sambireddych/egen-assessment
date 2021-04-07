package com.egen.application.ordersservice.batch;


import com.egen.application.ordersservice.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private static final String TOPIC = "batch_orders_topic";
    private ObjectMapper objectMapper;

    private KafkaTemplate<String, String> kafkaTemplate;

    public void writeOrders(List<Order> orders){
        try {
            this.kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(orders));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
