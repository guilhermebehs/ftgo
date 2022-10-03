package br.com.guilhermebehs.ftgo.kitchen.adapters.in.http.errors;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse {

    private List<String> errors = new ArrayList<>();

}