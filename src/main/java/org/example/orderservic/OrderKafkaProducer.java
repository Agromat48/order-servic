package org.example.orderservic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper; // Или com.fasterxml.jackson.databind.ObjectMapper

@Service
public class OrderKafkaProducer {

    // Обрати внимание: типы дженериков теперь <String, String>
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(OrderKafkaProducer.class);

    public OrderKafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrderToKafka(Order order) {
        try {
            // 1. Превращаем Java-объект в JSON-строку
            String orderJson = objectMapper.writeValueAsString(order);

            // 2. Отправляем в Kafka: (Топик, Ключ, Значение)
            // Ключ (order.orderID()) важен для правильного распределения по партициям
            kafkaTemplate.send("orders", order.orderID(), orderJson);

            log.info("Order successfully sent to Kafka: id = {}", order.orderID());
        } catch (Exception e) {
            log.error("Failed to serialize or send order to Kafka", e);
            // Здесь можно решить: пробросить исключение дальше или обработать
            throw new RuntimeException("Ошибка отправки в Kafka", e);
        }
    }
}