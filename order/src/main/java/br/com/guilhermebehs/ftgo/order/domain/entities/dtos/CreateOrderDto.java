package br.com.guilhermebehs.ftgo.order.domain.entities.dtos;

import br.com.guilhermebehs.ftgo.order.domain.entities.Address;
import br.com.guilhermebehs.ftgo.order.domain.entities.OrderItem;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateOrderDto {

    private String customerName;
    private Address customerAddress;
    private LocalDateTime deliveryDateForecast;
    private List<OrderItem> items;
    private String kitchen;
}
