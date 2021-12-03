package com.kramar.Market.rest.dto.cake;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CakeUIAdapter {
    private String name;
    private Long id;
    private BigDecimal price;
}
