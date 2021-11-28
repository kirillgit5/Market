package com.kramar.Market.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "User")
@Validated
public class User {
    @NotNull
    @Schema(description = "phoneNumber", required = true)
    private  String phoneNumber;

    @NotNull
    @Schema(description = "name", required = true)
    private  String name;
}
