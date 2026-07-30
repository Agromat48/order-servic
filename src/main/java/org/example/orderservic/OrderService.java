package org.example.orderservic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderKafkaProducer kafkaProducer;

    public OrderService(OrderKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void saveOrder(Order orderToSave) {
        log.info("Saving order to database: {}", orderToSave);

        // ... здесь код сохранения в Базу Данных ...

        // Отправляем в Kafka через наш выделенный компонент
        kafkaProducer.sendOrderToKafka(orderToSave);
    }
}