package br.com.guilhermebehs.ftgo.kitchen.adapters.out.mongo.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressCollection {

    private String description;
    private String neighbourhood;
    private String complement;
    private String city;
    private String state;
    private String country;

}
