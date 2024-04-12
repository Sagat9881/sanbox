package ru.apzakharov.abstract_crud.converters;

import org.springframework.core.convert.ConversionException;


public class CannotConvertException extends ConversionException {

    private static final long serialVersionUID = 1L;
    private String userMessage;

    public CannotConvertException(String message) {
        super(message);
        this.userMessage = message;
    }

    public CannotConvertException(String message, Throwable cause) {
        super(message, cause);
        this.userMessage = message;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
