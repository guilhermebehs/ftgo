package br.com.guilhermebehs.ftgo.order.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Address {

    private String description;
    private String neighbourhood;
    private String complement;
    private String city;
    private String state;
    private String country;

}
