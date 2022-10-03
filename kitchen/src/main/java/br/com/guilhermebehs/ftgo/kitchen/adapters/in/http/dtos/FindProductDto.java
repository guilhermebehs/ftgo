package br.com.guilhermebehs.ftgo.kitchen.adapters.in.http.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FindProductDto {

    private String product;

    private String kitchen;

    private boolean isActive;

    private int availableAmount;

    private int bookedAmount;
}
