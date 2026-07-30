package org.example.orderservic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    public void saveOrder(Order orderToSave) {
        //saving to database...
        //send to kafka
        log.info("Saving order {}", orderToSave);
    }
}
