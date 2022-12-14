package br.com.guilhermebehs.ftgo.kitchen.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {

    private String description;
    private String neighbourhood;
    private String complement;
    private String city;
    private String state;
    private String country;

}
