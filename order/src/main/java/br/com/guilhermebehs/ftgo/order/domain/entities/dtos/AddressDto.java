package br.com.guilhermebehs.ftgo.order.domain.entities.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressDto {

    @NotBlank(message = "invalid value for 'description'")
    private String description;

    @NotBlank(message = "invalid value for 'neighbourhood'")
    private String neighbourhood;

    private String complement;

    @NotBlank(message = "invalid value for 'city'")
    private String city;

    @NotBlank(message = "invalid value for 'state'")
    private String state;

    @NotBlank(message = "invalid value for 'country'")
    private String country;

}
