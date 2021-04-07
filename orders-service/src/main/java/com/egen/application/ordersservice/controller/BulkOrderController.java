package com.egen.application.ordersservice.controller;


import com.egen.application.ordersservice.model.Order;
import com.egen.application.ordersservice.service.BatchOrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/orders/batch")
@AllArgsConstructor
public class BulkOrderController {


    private BatchOrdersService batchOrdersService;



    @PostMapping
    public ResponseEntity<?> bulkCreateOrders(@RequestBody List<Order> bulkOrders) {
        return new ResponseEntity<>(batchOrdersService.createBatchOrders(bulkOrders), HttpStatus.OK);
    }

    public ResponseEntity<?> bulkUpdateOrders(@RequestBody List<Order> bulkOrders) {
        return null;
    }

    public ResponseEntity<?> deleteAllinBulk(@RequestBody List<Order> bulkOrders) {
        return null;
    }
}
