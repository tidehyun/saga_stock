package com.example.stock.service;

import com.example.stock.event.StockEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StockService {

    private ApplicationEventPublisher applicationEventPublisher;

    public StockService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public void addStock(String prodNo) {
        if (prodNo.contains("b1")) {
            log.info("Stock to Order FAIL , {}", prodNo);
            applicationEventPublisher.publishEvent(StockEvent.builder().prodNo(prodNo).build());
        } else {
            log.info("Stock to Order SUCCESS, {}", prodNo);
        }
    }
}
