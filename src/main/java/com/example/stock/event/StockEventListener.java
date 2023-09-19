package com.example.stock.event;

import com.example.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockEventListener {
    private StockService stockService;

    private KafkaTemplate kafkaTemplate;

    private final static String CANCEL_ORDER_TOPIC_NAME = "order-rollback";

    public StockEventListener(StockService stockService, KafkaTemplate kafkaTemplate) {
        this.stockService = stockService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-create", groupId = "group-01")
    public void stockOrder(String orderNo) {
        log.info("[KAFKA] Get Order , orderNo : {}", orderNo);
        stockService.addStock(orderNo);
    }

    @EventListener
    public void cancelOrder(StockEvent stockEvent) {
        log.info("Send to Kafka for Order Cancel , msg : {} ", stockEvent.getProdNo());
        kafkaTemplate.send(CANCEL_ORDER_TOPIC_NAME, stockEvent.getProdNo());
    }
}
