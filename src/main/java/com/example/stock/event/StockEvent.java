package com.example.stock.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class StockEvent {

    private String prodNo;
}
