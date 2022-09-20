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
public class CreateProductDto {

    @NotBlank(message = "invalid value for 'description'")
    private String description;

    @NotBlank(message = "invalid value for 'kitchen'")
    private String kitchen;

    @NotNull(message = "invalid value for 'is_active'")
    private Boolean isActive;

    @PositiveOrZero(message = "'available_amount' must be equal or greater than 0")
    @NotNull(message = "invalid value for 'available_amount'")
    private Integer availableAmount;


    @Override
    public String toString() {
        return "CreateProductDto{" +
                "description='" + description + '\'' +
                ", kitchen='" + kitchen + '\'' +
                ", isActive=" + isActive +
                ", availableAmount=" + availableAmount +
                '}';
    }
}
