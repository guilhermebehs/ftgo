package br.com.guilhermebehs.kitchen.domain.dtos;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductDto {

    @NotBlank
    private String description;

    @NotBlank
    private String kitchen;

    @NotNull
    private boolean isActive;

    @PositiveOrZero
    private Integer availableAmount;


}
