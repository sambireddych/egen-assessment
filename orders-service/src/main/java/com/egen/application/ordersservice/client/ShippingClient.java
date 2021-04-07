package com.egen.application.ordersservice.client;




import com.egen.application.ordersservice.client.models.ShippingModel;
import com.egen.application.ordersservice.model.Address;

import java.math.BigDecimal;

public interface ShippingClient {

    BigDecimal getTax(String zip);

    ShippingModel save(Address model, String orderId, String customerId) throws Exception;

}
