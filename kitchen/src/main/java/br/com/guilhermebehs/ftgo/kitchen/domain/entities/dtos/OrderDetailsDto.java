package br.com.guilhermebehs.ftgo.kitchen.domain.entities.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderDetailsDto {

    private String orderId;
    private AddressDto customerAddress;
    private List<OrderItemDto> items;
    private String kitchen;
}
