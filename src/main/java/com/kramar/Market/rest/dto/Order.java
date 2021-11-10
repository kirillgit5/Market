package com.kramar.Market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kramar.Market.orders.Deliverly;
import com.kramar.Market.orders.OrderStatus;
import com.kramar.Market.orders.Payment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Null;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Schema(description = "")
@Validated
public class Order {
    @Null
    @Schema(description = "id", required = false)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "delivery need", required = true)
    @JsonProperty("delivery")
    private Deliverly deliverly;

    @NotNull
    @Schema(description = "payment status", required = true)
    @JsonProperty("payment")
    private Payment payment;

    @NotNull
    @Schema(description = "delivery address", required = true)
    @JsonProperty("deliveryAddress")
    private String deliverlyAddress;
}
