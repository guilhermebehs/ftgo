package br.com.guilhermebehs.ftgo.kitchen.adapters.in.http.errors;

import br.com.guilhermebehs.ftgo.kitchen.domain.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            validationErrorResponse.getErrors().add(violation.getMessage());
        }
        return validationErrorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getErrors().add(fieldError.getDefaultMessage());
        }
        return error;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onIllegalArgumentException(IllegalArgumentException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(e.getMessage());
        return error;
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ValidationErrorResponse onIllegalStateException(IllegalStateException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(e.getMessage());
        return error;
    }


    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ValidationErrorResponse onProductNotFoundException(ProductNotFoundException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(e.getMessage());
        return error;
    }

}