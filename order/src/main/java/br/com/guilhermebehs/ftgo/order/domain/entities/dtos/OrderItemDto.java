package br.com.guilhermebehs.ftgo.order.domain.entities.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderItemDto {

    @NotBlank(message = "invalid value for 'description'")
    private String description;

    @NotNull(message = "invalid value for 'price'")
    @Positive(message = "invalid value for 'price'")
    private Double price;

    @NotNull(message = "invalid value for 'amount'")
    @Positive(message = "invalid value for 'amount'")
    private Integer amount;
}
