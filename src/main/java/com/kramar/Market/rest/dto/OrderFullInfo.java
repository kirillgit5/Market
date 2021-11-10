package com.kramar.Market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "")
@Validated
public class OrderFullInfo {
    @NotNull
    @Schema(description = "userId", required = true)
    @JsonProperty("userId")
    private String userId;

    @NotNull
    @Schema(description = "order", required = true)
    @JsonProperty("order")
    private Order order;

    @NotNull
    @Schema(description = "purchases", required = true)
    @JsonProperty("purchases")
    private List<Purchase> purchases;
}
