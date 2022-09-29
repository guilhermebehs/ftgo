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
public class UpdateProductDto {

    @NotBlank(message = "invalid value for 'description'")
    private String description;

    @NotBlank(message = "invalid value for 'kitchen'")
    private String kitchen;

    @PositiveOrZero(message = "'amount' must be equal or greater than 0")
    @NotBlank(message = "invalid value for 'kitchen'")
    private Integer amount;
}
