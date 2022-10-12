package br.com.guilhermebehs.ftgo.order.adapters.out.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AddressCollection {

    private String description;
    private String neighbourhood;
    private String complement;
    private String city;
    private String state;
    private String country;

}
