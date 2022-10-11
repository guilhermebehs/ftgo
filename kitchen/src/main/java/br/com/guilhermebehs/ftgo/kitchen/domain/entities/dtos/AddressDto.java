package br.com.guilhermebehs.ftgo.kitchen.domain.entities.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressDto {

    private String description;

    private String neighbourhood;

    private String complement;

    private String city;

    private String state;

    private String country;

}
