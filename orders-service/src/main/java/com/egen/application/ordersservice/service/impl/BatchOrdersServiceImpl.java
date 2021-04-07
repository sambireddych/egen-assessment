package com.egen.application.ordersservice.service.impl;

import com.egen.application.ordersservice.batch.KafkaProducer;
import com.egen.application.ordersservice.model.Order;
import com.egen.application.ordersservice.model.OrderSummary;
import com.egen.application.ordersservice.repo.OrderManagerRepo;
import com.egen.application.ordersservice.service.BatchOrdersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BatchOrdersServiceImpl implements BatchOrdersService {

    private OrderManagerRepo repo;
    private KafkaProducer kafkaProducer;

    @Transactional
    @Override
    public List<OrderSummary> createBatchOrders(List<Order> orders) {

        List<Order> orderList = new ArrayList<>();

        orders.stream().forEach(order -> {
            orderList.add(order);
            if (orderList.size() % 5 == 0){
                log.debug("Batch process started sending in Kafka stream: ", orderList);
                kafkaProducer.writeOrders(repo.saveAll(orderList));

                orderList.clear();
            }
        });
        if(orderList.size() > 0){
            log.debug("Creation of Batch process started sending in Kafka stream: ", orderList);
            kafkaProducer.writeOrders(repo.saveAll(orderList));
        }
        return new ArrayList((Collection) new OrderSummary());

    }

    @Override
    public List<Order> updateBatchOrders(List<Order> orders) {
        List<Order> orderList = new ArrayList<>();
        orders.stream().forEach(order -> {
            try{
                if (repo.findById(order.getOrderId()) != null){
                    orderList.add(order);
                    if(orderList.size() % 5 == 0){
                        log.debug("Update of Batch process started sending in Kafka stream: ", orderList);

                        kafkaProducer.writeOrders(repo.saveAll(orderList));
                        orderList.clear();
                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        });
        if(orderList.size() > 0){
            log.debug("Update of Batch process started sending in Kafka stream: ", orderList);
            kafkaProducer.writeOrders(repo.saveAll(orderList));
        }
        return orderList;
    }

    @Override
    public void deleteAllinBatch(List<Order> orders) {
        repo.deleteInBatch(orders);
        log.debug("Deleted");
    }
}
